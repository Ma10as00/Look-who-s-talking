package main;
import java.util.HashMap;
import java.util.Map;

import main.properties.Meaning;
import main.properties.NoPeople;
import main.properties.Person;
import main.properties.SubjectPropertySet;
import main.properties.Tense;
import main.properties.VerbPropertySet;

/**
 * Class to read English sentences and translate it into a set of properties, specifically a {@link VerbPropertySet} and a {@link SubjectPropertySet}.
 * @author Mathias Øgaard
 */
public class EnglishReader {

    private Map<String,VerbPropertySet> verbDictionary;     //English --> Abstraction
    private Map<String,SubjectPropertySet> subjDictionary;  //English --> Abstraction
        
    private VerbPropertySet verb;       //Abstraction
    private SubjectPropertySet subject; //Abstraction

    /**
     * Builds up the dictionaries for this reader to be able to translate English into a set of abstract properties 
     */
    public EnglishReader(){
        //Build verb dictionary
        verbDictionary = new HashMap<>();
        //Past tense:
        for(Meaning m : Meaning.values()){
            String str = switch (m) { //Special cases for the irregular verbs
                case GO -> "went";
                case MAKE -> "made";
                case SEE -> "saw";
                case READ -> "read";
                default -> m.toString().toLowerCase() + "ed";   //For all the regular verbs
            };
            VerbPropertySet vps = new VerbPropertySet(m, Tense.PAST);
            verbDictionary.put(str, vps);
        }
        //Present tense:
        for(Meaning m : Meaning.values()){
            String str = switch (m) {    //Special cases for the irregular verbs
                case MAKE -> "making";
                default -> m.toString().toLowerCase() + "ing";  //For regular verbs
            };
            VerbPropertySet vps = new VerbPropertySet(m, Tense.PRESENT);
            verbDictionary.put(str, vps);
        }
        //Future tense:
        for(Meaning m : Meaning.values()){  //No irregular verbs
            String str = m.toString().toLowerCase();
            VerbPropertySet vps = new VerbPropertySet(m, Tense.FUTURE);
            verbDictionary.put(str, vps);
        }

        //Build subject sictionary
        subjDictionary = new HashMap<>();
        subjDictionary.put("i", new SubjectPropertySet(NoPeople.SINGULAR,Person.FIRST_EXCL));
        subjDictionary.put("you (1 incl)", new SubjectPropertySet(NoPeople.SINGULAR,Person.SECOND));
        subjDictionary.put("he", new SubjectPropertySet(NoPeople.SINGULAR,Person.THIRD));
        subjDictionary.put("she", new SubjectPropertySet(NoPeople.SINGULAR,Person.THIRD));
        subjDictionary.put("we (2 incl)", new SubjectPropertySet(NoPeople.DUAL,Person.FIRST_INCL));
        subjDictionary.put("we (2 excl)", new SubjectPropertySet(NoPeople.DUAL,Person.FIRST_EXCL));
        subjDictionary.put("you (2 incl)", new SubjectPropertySet(NoPeople.DUAL,Person.SECOND));
        subjDictionary.put("they (2 excl)", new SubjectPropertySet(NoPeople.DUAL,Person.THIRD));
        subjDictionary.put("we (3 incl)", new SubjectPropertySet(NoPeople.PLURAL,Person.FIRST_INCL));
        subjDictionary.put("we (3 excl)", new SubjectPropertySet(NoPeople.PLURAL,Person.FIRST_EXCL));
        subjDictionary.put("you (3 incl)", new SubjectPropertySet(NoPeople.PLURAL,Person.SECOND));
        subjDictionary.put("they (3 excl)", new SubjectPropertySet(NoPeople.PLURAL,Person.THIRD));
        
    }

    public SubjectPropertySet getSubject() {
        return subject;
    }

    public VerbPropertySet getVerb() {
        return verb;
    }
    
    /**
     * Reads an english sentence, and puts all the found properties into the {@link EnglishReader#verb} and the {@link EnglishReader#subject}.
     * @param s - String to read
     */
    public void read(String s){
        s.toLowerCase();
        String[] words = s.split(" ");

        //Find subject
        String subjectString;
        //Check if first three words are on the form: "Pronoun (num excl/incl)"
        if(words.length >= 3){
            if(words[1].startsWith("(") && words[2].endsWith(")")){ //include parethesis in subject-term
                int number = Integer.parseInt(words[1].substring(1));
                if(number > 3){
                    subjectString = words[0] + " (" + 3 + " " + words[2];   //Treat all numbers above 3 like 3
                }else{
                    subjectString = words[0] + " " + words[1] + " " + words[2];
                }
            }else{
                subjectString = words[0];  //Sentence had no parenthesis
            }
        }else{
            subjectString = words[0];  //Sentence had no parenthesis
        }
        subject = subjDictionary.get(subjectString.toLowerCase());

        //Find verb
        String lastWord = words[words.length - 1];
        verb = verbDictionary.get(lastWord);
    }
}
