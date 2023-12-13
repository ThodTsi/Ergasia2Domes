import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Influenza_k {

    public static void swim(City[] c, int k) {
        while ((k > 1) & (k / 2 < k)) {
            if (c[k].compareTo(c[k / 2]) == 1) {
                City temp = c[k / 2];
                c[k / 2] = c[k];
                c[k] = temp;
            }
            k /= 2;
        }

    }

    public static void heapify(City[] c, int k) {
        for (int i = k; i > 0; i--) {
            swim(c, i);
        }
    }

    public static City[] heapSort(City[] c) {
        City[] temp = new City[c.length];
        for (int i = c.length - 1; i > 0; i--) {
            temp[i] = c[1];
            c[1] = c[i];
            heapify(c, i);
        }
        return temp;
    }

    public static void main(String args[]) {
        
        /*  File file = new File(filename);
        FileWriter writer = new FileWriter("inf.txt");
        City city1 = new City(17, "Vienna", 1975000, 2200);
        City city2 = new City(38, "Amsterdam", 921402, 1050);
        City city3 = new City(65, "Paris", 2102650, 3504);
        City city4 = new City(47, "Athens", 698567, 830);
        City city5 = new City(12, "Thessaloniki", 319045, 413);
        writer.write(city1.getID() + " " + city1.getName() + " " + city1.getPopulation() + " "
                    + city1.getInfluenzaCases() + "\n");
            writer.write(city2.getID() + " " + city2.getName() + " " + city2.getPopulation() + " "
                    + city2.getInfluenzaCases() + "\n");
            writer.write(city3.getID() + " " + city3.getName() + " " + city3.getPopulation() + " "
                    + city3.getInfluenzaCases() + "\n");
            writer.write(city4.getID() + " " + city4.getName() + " " + city4.getPopulation() + " "
                    + city4.getInfluenzaCases() + "\n");
            writer.write(city5.getID() + " " + city5.getName() + " " + city5.getPopulation() + " "
                    + city5.getInfluenzaCases() + "\n");
            writer.close();
            City[] city = new City[6];
            city[1] = city1;
            city[2] = city2;
            city[3] = city3;
            city[4] = city4;
            city[5] = city5;
        */
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Give file name: ");
            String filename = in.nextLine();
            int numberOfLines = 0; // gia to megethos tou pinaka
            BufferedReader reader = new BufferedReader(new FileReader("inf.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                numberOfLines++;
            }
            
            City[] city = new City[numberOfLines];
            
            readFile(filename, city);
            
            heapify(city, city.length - 1);
            City[] citySort = heapSort(city);

            System.out.print("Enter number of least infected cities per 50.000 citizens to be displayed: ");
            int k = in.nextInt();
            if (k >= 1 & k <= citySort.length) {
                System.out.println("The top " + k + " cities are:");
                for (int i = 1; i <= k; i++) {
                    System.out.println(citySort[i].getName());
                }
            }else{
                System.out.println("This number is not valid");
            }
        }catch (IOException e) {
                e.printStackTrace();
                System.out.println("error oppening the file...");
        }

    }

    public static void readFile(String filePath, City [] c){

        try{
            int id = 0, population = 0 , inf_cases = 0;
            String name = "";
            BufferedReader reader = new BufferedReader(new FileReader("inf.txt"));
            StringBuilder currentLine = new StringBuilder(); //gia na mporoym na kratame kai to kathe line pera apo kathe character
            int ch ; // character se int giati o BufferedReader diavazei se ASCII
            int startIndex = 0, endIndex = 0; //start kai end gia na "kopsoyme" to line se kommatia kai na paroyme tis plhrofories
            int whiteSpace = 0; //gia na xwristei to line opote briskei keno
            int n = -1; // metraei se poio line eimaste

            while ((ch = reader.read()) != -1) {
                if ((char) ch == '\n'){
                    inf_cases = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex-1));
                    //System.out.println(inf_cases);
                
                    n++;
                    endIndex = startIndex = 0;
                    currentLine.setLength(0);
                    City city = new City(id, name, population, inf_cases);
                    c[n] = city;
                    whiteSpace =-1;
                }else{
                    endIndex++;
                    currentLine.append((char) ch);
                }

                if (Character.isWhitespace((char) ch)){
                    whiteSpace ++;
                    if (whiteSpace == 1){
                        id = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex-1));
                        startIndex = endIndex;
                        //System.out.println(id);
                    }else if(whiteSpace == 2){
                        name = currentLine.toString().trim().substring(startIndex, endIndex-1);
                        startIndex = endIndex;
                        //System.out.println(name);
                    }else if(whiteSpace == 3){
                        population = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex-1));
                        startIndex = endIndex;
                        //System.out.println(population);
                    }
                    
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }

}
