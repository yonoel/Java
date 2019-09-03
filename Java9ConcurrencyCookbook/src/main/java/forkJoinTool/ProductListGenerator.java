package forkJoinTool;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {
    public List<Product> generate(int size){
        List<Product> ret = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ret.add(new Product("Product"+i,10));
        }
        return ret;
    }
}
