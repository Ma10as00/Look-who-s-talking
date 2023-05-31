import java.io.File;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        // File test = new File("matogaTranslate/src/testInput.txt");
        Scanner scanner = new Scanner(System.in);

        EnglishReader reader = new EnglishReader();

        while(scanner.hasNext()){
            // System.out.print("Sentence to translate: ");
            String input = scanner.nextLine();
            // System.out.println(input);
            reader.read(input);
            VerbPropertySet verb = reader.getVerb();
            SubjectPropertySet subject = reader.getSubject();

            MaoriWriter writer = new MaoriWriter(verb, subject);
            // System.out.print("Translation: ");
            writer.write();
            // System.out.println();
        }
        scanner.close();
    }
}
