import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test extends Influenza_k {
    public static void main(String[] args) {
        try {
            PQ pq = new PQ();
            BufferedReader reader = new BufferedReader(new FileReader("inf.txt"));
            String line;
            int numberOfLines = 0;
            while ((line = reader.readLine()) != null) {
                numberOfLines++;
            }
            City[] city = new City[numberOfLines + 1];
            readFile("inf.txt", city);
            for (City c : city) {
                if (c != null) {
                    pq.insert(c);
                }
            }
            System.out.println(pq.getMin().getName());
            System.out.println(pq.min().getName());
            System.out.println(pq.size());
            System.out.println(pq.getMin().getName());
            System.out.println(pq.remove(65));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error oppening the file...");
        }

    }
}
