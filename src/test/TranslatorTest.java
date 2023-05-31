package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import main.*;

public class TranslatorTest {

    InputStream input = getClass().getResourceAsStream("testInput.txt");
    InputStream output = getClass().getResourceAsStream("testOutput.txt");
    
    @Test
    public void SuccessfullTranlation() throws FileNotFoundException{
        EnglishReader reader = new EnglishReader();
        Scanner in = new Scanner(input);
        Scanner out = new Scanner(output);

        while(in.hasNext()){
            reader.read(in.nextLine());
            MaoriWriter writer = new MaoriWriter(reader.getVerb(), reader.getSubject());
            assertEquals(out.nextLine(), writer.getTranslation());
        }

        in.close();
        out.close();
    }
}
