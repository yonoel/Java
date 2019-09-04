package customConcurrent;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MySpliterator implements Spliterator<Item> {
    public static void main(String[] args) {
        Item[][] items = new Item[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                items[i][j] = new Item();
                items[i][j].setRow(i);
                items[i][j].setColumn(j);
                items[i][j].setName("Item " + i + " " + j);
            }
        }

        MySpliterator spliterator = new MySpliterator(items, 0, 10);

        StreamSupport.stream(spliterator, true).forEach(item ->
                System.out.printf("%s %s %n", Thread.currentThread().getName(), item.getName()));
    }

    private Item[][] items;
    private int start, end, current;

    public MySpliterator(Item[][] items, int start, int end) {
        this.items = items;
        this.start = start;
        this.end = end;
        this.current = start;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Item> action) {
        System.out.printf("mySpliterator.tryAdvance start %d %d %d %n", start, end, current);
        if (current < end) {
            for (int i = 0; i < items[current].length; i++) {
                action.accept(items[current][i]);
            }
            current++;
            System.out.println("mySpliterator.tryAdvance end true");
            return true;
        }
        System.out.println("mySpliterator.tryAdvance end false");
        return false;
    }

    // 并行流调用，由此分为两个子集
    @Override
    public Spliterator<Item> trySplit() {
        System.out.println("mySpliterator.trySplit start ");
        if (end - start <= 2) {
            System.out.println("mySpliterator.trySplit end ");
            return null;
        }
        int mid = start + (end - start) / 2;
        int newStart = mid;
        int newEnd = end;

        System.out.printf("mySpliterator.trySplit end %d %d %d %d %d %d %n"
                , start, mid, end, newStart, newEnd, current);
        return new MySpliterator(items, newStart, newEnd);
    }

    // 处理元素完后的剩余元素
    @Override
    public void forEachRemaining(Consumer<? super Item> action) {
        System.out.println("mySpliterator.forEachRemaining start ");
        boolean ret;
        do {
            ret = tryAdvance(action);
        } while (ret);
        System.out.println("mySpliterator.forEachRemaining end ");


    }

    // 返回处理的元素数量
    @Override
    public long estimateSize() {
        return end - current;
    }

    @Override
    public int characteristics() {
        return ORDERED | SIZED | SUBSIZED;
    }
}
