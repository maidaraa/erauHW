import java.util.*;
import java.io.*;

public class Nonduplicate {

    public static void main(String[] args) throws Exception {

        File textFile = new File("HW4TextFile.txt");
        BufferedReader br = new BufferedReader(new FileReader(textFile));
        TreeSet<String> wordSet = new TreeSet<>();
        String fileWords;

        System.out.println("File contains the following words: ");
        while ((fileWords = br.readLine()) != null) {

            System.out.print(fileWords + ", ");
            String[] words = fileWords.split("\\s+");

            for (int i = 0; i < words.length; i++) {
                wordSet.add(words[i]);

            }
        }
        System.out.print("\n");
        System.out.println("Displaying all nonduplicate words from file: ");

        Iterator<String> iterator = wordSet.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
