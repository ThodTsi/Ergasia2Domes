import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test extends Influenza_k {
    public static void main(String[] args) {
        PQ pq = new PQ();
        pq.insert(new City(17, "Vienna", 1975000, 2200));
        pq.insert(new City(38, "Amsterdam", 921402, 1050));
        pq.insert(new City(65, "Paris", 2102650, 3504));
        pq.insert(new City(47, "Athens", 698567, 830));
        pq.insert(new City(12, "Thessaloniki", 319045, 413));
        for (int i = 1; i <= pq.size(); i++) {
            System.out.println(pq.heap[i].toString());
        }
        System.out.println(pq.remove(65).getName());
        System.out.println(pq.remove(47).getName());
        System.out.println(pq.size());
        for (int i = 0; i <= pq.heap.length - 1; i++) {
            if (pq.heap[i] != null) {
                System.out.println(pq.heap[i].toString());
            }

        }
        System.out.println
        System.out.println(pq.remove(65).getName());
        // System.out.println(pq.remove(65).getName());
        // System.out.println(pq.remove(65).getName());

        /*
         * protected City remove(int id) {
         * int i = 1;
         * boolean stop = false;
         * while (i < heap.length & stop == false) {
         * if (heap[i].getID() == id) {
         * stop = true;
         * while (i < heap.length & heap[i + 1] != null) {
         * exchange(i, ++i);
         * }
         * }
         * i++;
         * }
         * i--;
         * City temp = null;
         * if (stop == false)
         * return temp;
         * temp = heap[i];
         * heap[i] = null;
         * size--;
         * swim(size);
         * return temp;
         * }
         */

    }
}
