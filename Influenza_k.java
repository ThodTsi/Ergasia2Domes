import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Influenza_k {

    public static void main(String args[]) {

        try{
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
            int k = Integer.parseInt(args[0]);
            System.out.println(k);
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
            int n = 0; // metraei se poio line eimaste kai xrhsimopoietai ws deikths ston pinaka
            boolean readName = false; //bohthaei sto na jeroyme pote exei teleiwsei na diavazei to onoma
            boolean readPop = false; //epeidh sto telos kathe line diavazei ena whitespace prin to telos

            while (n < c.length) {
                ch = reader.read();
                System.out.println((char)ch);
                if ((char) ch == '\n' || ch == -1){
                    if(ch==-1){
                        endIndex ++; //mono gia thn teleytaia grammh
                    }
                    System.out.println(startIndex);
                    inf_cases = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex-1));
                    n++;
                    endIndex = startIndex = 0;        //allazei line, jana arxikopoihsh
                    currentLine.setLength(0); //"katharizoyme to line"
                    whiteSpace =-1; //-1 giati diavazei ena parapanw whiteSpace otan allazei line

                    City city = new City(id, name, population, inf_cases);
                    c[n] = city;  
                    heapify(c, n);   
                    if(ch ==-1){ //diavaze -1 kai meta jana -1, opote to valame gia na
                        break;   //mas petaei apo to loop otan teleiwnei to text, dhladh sto prwto -1
                    }
                }else{
                    endIndex++;
                    currentLine.append((char) ch);
                }

                System.out.println(startIndex);
                if(Character.isWhitespace((char) ch)){
                    
                    whiteSpace ++;
                    System.out.println("Whitespace " + whiteSpace);
                    if (whiteSpace < 0  ){
                        continue;
                    }else if(readName == true){ 
                        population = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex -1));
                        startIndex = endIndex;
                        System.out.println(population);
                        whiteSpace = -2;
                    }else{
                        id = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex -1)); //-1 gia to space
                        startIndex = endIndex;
                    }
                }else if(whiteSpace >= 1 && Character.isDigit((char)ch) && !readName){ //an exoyme diavasei MONO(==1) to id kai an o xarakthras einai arithmos kai an den exoyme balei hdh to onoma
                    name = currentLine.toString().trim().substring(startIndex, endIndex-2); //-2 giati twra exoyme diabasei kai to space kai ton prwto arithmo
                    startIndex = endIndex-1;
                    readName = true;
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
