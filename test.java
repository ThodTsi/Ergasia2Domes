import java.text.DecimalFormat;

public class test {
    public static void main(String[] args) {
        PQ pq = new PQ();

        pq.insert(new City(17, "Vienna", 1975000, 2200));
        pq.insert(new City(38, "Amsterdam", 921402, 1050));
        pq.insert(new City(65, "Paris", 2102650, 3504));
        pq.insert(new City(47, "Athens", 698567, 830));
        pq.insert(new City(12, "Thessaloniki", 319045, 413));
        pq.insert(new City(20, "Korinthos", 100000, 1000));
        pq.insert(new City(10, "Kira Vrisi", 3000, 100));
        pq.insert(new City(77, "Lianokladi", 5000, 1000));
        // System.out.println(pq.getMin().getName());
        System.out.println(pq.min().getName());
        System.out.println(pq.size());
        System.out.println(pq.getMin().getName());

    }
}
