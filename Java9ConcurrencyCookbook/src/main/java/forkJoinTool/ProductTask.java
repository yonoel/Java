package forkJoinTool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ProductTask extends RecursiveAction {
    public static void main(String[] args) {
        ProductListGenerator listGenerator = new ProductListGenerator();
        List<Product> products = listGenerator.generate(10000);
        ProductTask task = new ProductTask(products, 0, products.size(), 0.2);
        ForkJoinPool pool = new ForkJoinPool();

        pool.execute(task);
        do {
            System.out.printf("Main:thread count %d %n", pool.getActiveThreadCount());
            System.out.printf("Main:thread steal %d %n", pool.getStealCount());
            System.out.printf("Main:parallelism  %d %n", pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        pool.shutdown();

        if (task.isCompletedNormally()) {
            System.out.printf("Main: the processor jas complete normally %n");
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getPrice() != 12) {
                System.out.printf("Product %s: %f%n", product.getName(), product.getPrice());
            }
        }

        System.out.println("Main:end all");
    }

    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public ProductTask(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    public ProductTask() {
        super();
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrices();
        } else {
            int mid = (last + first) / 2;
            System.out.printf("task:pending tasks:%s %n", getQueuedTaskCount());
            ProductTask task1 = new ProductTask(products, first, mid + 1, increment);
            ProductTask task2 = new ProductTask(products, mid + 1, last, increment);
            invokeAll(task1, task2);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }
}
