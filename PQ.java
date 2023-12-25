
public class PQ {
    protected City[] heap;
    protected int size;
    public static final int def_cap = 4;
    public static final int auto_grow = 4;

    public PQ() {
        this.heap = new City[def_cap + 1];
        this.size = 0;
    }

    protected void swim(int i) {
        if (i == 0)
            return;

        while ((i > 1) & (i / 2 < i)) {
            if (heap[i].compareTo(heap[i / 2]) == -1) {
                City temp = heap[i / 2];
                heap[i / 2] = heap[i];
                heap[i] = temp;
            }
            i /= 2;
        }
    }

    protected boolean isEmpty() {
        return (heap[1] == null);
    }

    protected int size() {
        return this.size;
    }

    protected void insert(City c) {
        double lim = Math.ceil(0.75 * size);
        if (heap[(int) lim] != null) {
            resize();
        }
        size++;
        heap[size] = c;
        swim(size);

    }

    protected void resize() {
        City[] newHeap = new City[heap.length + auto_grow];
        System.arraycopy(heap, 1, newHeap, 1, size);

        heap = newHeap;
    }

    protected void exchange(int i, int j) {
        City temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    protected City remove(int id) {
        int i = 1;
        boolean stop = false;
        while (i < heap.length & stop == false) {
            if (heap[i].getID() == id) {
                stop = true;
                while (i < heap.length & heap[i + 1] != null) {
                    exchange(i, ++i);
                }
            }
            i++;
        }
        i--;
        City temp = null;
        if (stop == false)
            return temp;
        temp = heap[i];
        heap[i] = null;
        size--;
        swim(size);
        return temp;
    }

    protected City min() {
        return heap[1];
    }

    protected City getMin() {
        City temp = heap[1];
        remove(heap[1].getID());
        return temp;
    }
}
