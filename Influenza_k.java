import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Influenza_k {

    public static void main(String args[]) {

        try{
            Scanner in = new Scanner(System.in);
            //System.out.print("Give file name: ");
            //String filename = in.nextLine();
            int numberOfLines = 0; // gia to megethos tou pinaka
            BufferedReader reader = new BufferedReader(new FileReader("inf.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                numberOfLines++;
            }
            
            City[] city = new City[numberOfLines + 1];
            readFile("inf.txt", city);

            for(City x: city){
                if (x != null){
                    System.out.println(x.toString());
                    System.out.println(x.calculateDensity());
                }

            }
            System.out.println();
            
            City[] citySort = heapSort(city);
            
            for(City x: citySort){
                if (x!= null){
                    System.out.println(x.toString());
                }
            }
            
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
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder currentLine = new StringBuilder(); //gia na mporoym na kratame kai to kathe line pera apo kathe character
            int ch ; // character se int giati o BufferedReader diavazei se ASCII
            int startIndex = 0, endIndex = 0; //start kai end gia na "kopsoyme" to line se kommatia kai na paroyme tis plhrofories
            int whiteSpace = 0; //gia na xwristei to line opote briskei keno
            int n = 0; // metraei se poio line eimaste kai xrhsimopoietai ws deikths ston pinaka gia ayto kai -1

            while ((ch = reader.read()) != -1) {
                if ((char) ch == '\n'){
                    inf_cases = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex-1));
                    n++;
                    endIndex = startIndex = 0;         //allazei line, jana arxikopoihsh
                    currentLine.setLength(0); //"katharizoyme to line"
                    whiteSpace =-1; //-1 giati diavazei ena parapanw whiteSpace otan allazei line

                    City city = new City(id, name, population, inf_cases);
                    c[n] = city;  
                    heapify(c, n);                  
                }else{
                    endIndex++;
                    currentLine.append((char) ch);
                }

                if (Character.isWhitespace((char) ch)){
                    whiteSpace ++;
                    if (whiteSpace == 1){
                        id = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex-1));
                        startIndex = endIndex;
                    }else if(whiteSpace == 2){
                        name = currentLine.toString().trim().substring(startIndex, endIndex-1);
                        startIndex = endIndex;
                    }else if(whiteSpace == 3){
                        population = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex-1));
                        startIndex = endIndex;
                    }
                    
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }

    public static void swim(City[] c, int k) {
        while ((k > 1) && (k / 2 < k)) {
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
        City [] temp = new City[c.length];
        for (int i = c.length -1 ; i > 0; i--) {
            temp[i] = c[1];
            c[1] = c[i];
            heapify(c, i);
        }
        return temp;
    }

}
