package Collections;

public class MaxPQ<Key extends Comparable> {
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>(20);
    }

    private Key[] pq;

    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    //由下至上的堆有序化（上浮）的实现
    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    //由上至下的堆的有序化（下浮）的实现
    public void sink(int k) {
        while (2 * k < N) {
            int j = 2 * k;
            if (j <= N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];//从根节点得到最大的元素
        exch(1, N--);//将其和最后一个节点交换
        pq[N++] = null;//防止对象游离
        sink(1);//恢复堆的有序性
        return max;
    }

    public Key[] getPq() {
        return pq;
    }

    public void setPq(Key[] pq) {
        this.pq = pq;
    }
}