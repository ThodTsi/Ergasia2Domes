import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dynamic_Median {

    public static void main(String args[]) {
        try {
            int numberOfLines = 0; // gia to megethos tou pinaka
            PQ pq = new PQ();
            PQmed pqmed = new PQmed();
            BufferedReader reader = new BufferedReader(new FileReader("inf.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                numberOfLines++;
            }
            System.out.println(numberOfLines);
            readFile("inf.txt", pq, pqmed, numberOfLines);
            int items = 0;
            for (int i = 1; i <= pq.size(); i++) {
                System.out.println(pq.heap[i].getName());
                pqmed.insert(pq.getMin().calculateDensity());
            }
            for (int i = 0; i <= pqmed.med.length; i++) {
                System.out.println(pqmed.med[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error oppening the file...");
        }
    }

    public static void readFile(String filePath, PQ pq, PQmed pqmed, int numLines) {

        try {

            int id = 0, population = 0, inf_cases = 0;
            String name = "";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder currentLine = new StringBuilder(); // gia na mporoym na kratame kai to kathe line pera apo
                                                             // kathe character
            int ch; // character se int giati o BufferedReader diavazei se ASCII
            int startIndex = 0, endIndex = 0; // start kai end gia na "kopsoyme" to line se kommatia kai na paroyme tis
                                              // plhrofories
            int whiteSpace = 0; // gia na xwristei to line opote briskei keno
            int n = 0; // metraei se poio line eimaste kai xrhsimopoietai ws deikths ston pinaka
            boolean readName = false; // bohthaei sto na jeroyme pote exei teleiwsei na diavazei to onoma

            while (n <= numLines) {
                ch = reader.read();
                if ((char) ch == '\n' || ch == -1) {
                    if (ch == -1) {
                        endIndex++; // mono gia thn teleytaia grammh
                    }
                    inf_cases = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                    n++;
                    endIndex = startIndex = 0; // allazei line, jana arxikopoihsh
                    currentLine.setLength(0); // "katharizoyme to line"
                    whiteSpace = -1; // -1 giati diavazei ena parapanw whiteSpace otan allazei line
                    readName = false;
                    if ((id < 1 || id > 999) || (population < 0 || population > 10000000) || (name.length() > 50))
                        throw new Exception();
                    City city = new City(id, name, population, inf_cases);
                    pq.insert(city);
                    if (ch == -1) {
                        // diavaze -1 kai meta jana -1, opote to valame gia na
                        break; // mas petaei apo to loop otan teleiwnei to text, dhladh sto prwto -1
                    }
                } else {
                    endIndex++;
                    currentLine.append((char) ch);
                }

                if (Character.isWhitespace((char) ch)) {

                    whiteSpace++;
                    if (whiteSpace == 1) {
                        id = Integer.parseInt(currentLine.toString().trim().substring(startIndex, endIndex - 1)); // -1
                                                                                                                  // gia
                                                                                                                  // to
                                                                                                                  // space
                        startIndex = endIndex;
                    } else if (readName == true && whiteSpace >= 1) { // gia na apofygoyme na mpei sto telos toy line
                        population = Integer
                                .parseInt(currentLine.toString().trim().substring(startIndex, endIndex - 1));
                        startIndex = endIndex;
                        whiteSpace = -2; // gia na mhn jana mpei sthn dhmioyrgia toy name kai epeidh sto telos kathe
                                         // line diavazei 2 space parapanw gia auto kai to -3
                    }
                } else if (whiteSpace >= 1 && Character.isDigit((char) ch) && !readName) { // an exoyme diavasei
                                                                                           // MONO(==1) to id kai an o
                                                                                           // xarakthras einai arithmos
                                                                                           // kai an den exoyme balei
                                                                                           // hdh to onoma
                    name = currentLine.toString().trim().substring(startIndex, endIndex - 2); // -2 giati twra exoyme
                                                                                              // diabasei kai to space
                                                                                              // kai ton prwto arithmo
                    startIndex = endIndex - 1;
                    readName = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        } catch (Exception exc) {
            System.out.println("Wrong input");
            System.exit(0);
        }

    }
}
