import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        EnglishReader reader = new EnglishReader();
        System.out.print("Sentence to translate: ");
        String input = scanner.nextLine(); scanner.close();
        reader.read(input);
        VerbPropertySet verb = reader.getVerb();
        SubjectPropertySet subject = reader.getSubject();

        MāoriWriter writer = new MāoriWriter(verb, subject);
        writer.write();
    }
}
