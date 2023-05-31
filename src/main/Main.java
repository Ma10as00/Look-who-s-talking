package main;
import java.util.Scanner;

/**
 * Program translating simple sentences (pronoun + verb) from English to Māori.
 * <p>
 * When using ambiguous pronouns, a specification needs to follow, e.g. 
 * <p>
 * "We (2 excl)", meaning it is refering to two people, excluding the listener.
 * <p>
 * The program can only translate the eight verbs defined in {@link main.properties.Meaning}.
 * @author Mathias Øgaard
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        EnglishReader reader = new EnglishReader();
        while(scanner.hasNext()){
            //Read English
            String input = scanner.nextLine();
            reader.read(input);
            //Write Māori
            MaoriWriter writer = new MaoriWriter(reader.getVerb(), reader.getSubject());
            writer.write();
        }
        scanner.close();
    }
}
