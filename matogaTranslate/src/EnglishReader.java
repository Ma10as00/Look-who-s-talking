import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnglishReader {
    
    private VerbPropertySet verb = null;
    private SubjectPropertySet subject = null;

    private Map<String,VerbPropertySet> verbDictionary;
    private Map<String,SubjectPropertySet> subjDictionary;

    public EnglishReader(){
        verbDictionary = new HashMap<>();

        //Past tense:
        for(Meaning m : Meaning.values()){
            String str;
            switch (m) { //Special cases for the irregular verbs
                case GO:
                    str = "went";
                    break;
                case MAKE:
                    str = "made";
                    break;
                case SEE:
                    str = "saw";
                    break;
                case READ:
                    str = "read";
                default: //For all regular verbs
                    str = m.toString() + "ed";
                    break;
            }
            VerbPropertySet vps = new VerbPropertySet(m, Tense.PAST);
            verbDictionary.put(str, vps);
        }
        //Present tense:
        for(Meaning m : Meaning.values()){
            String str;
            switch (m) {    //Special cases for the irregular verbs
                case MAKE:
                    str = "making";
                    break;
                default:    //For all regular verbs
                    str = m.toString() + "ing";
                    break;
            }
            VerbPropertySet vps = new VerbPropertySet(m, Tense.PRESENT);
            verbDictionary.put(str, vps);
        }
        //Future tense:
        for(Meaning m : Meaning.values()){
            String str = "will " + m.toString();
            VerbPropertySet vps = new VerbPropertySet(m, Tense.FUTURE);
            verbDictionary.put(str, vps);
        }

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
     * Reads an english sentence, and puts all the found properties into the {@link EnglishReader#verbDictionary} and the {@link EnglishReader#subjDictionary}.
     * @param s - String to read
     */
    public void read(String s){ //TODO This doesn't work yet
        s.toLowerCase();
        List<String> words = Arrays.asList(s.split(" "));

        //Find subject
        String subjectString;
        String secondWord = words.get(1);
        //If second word is a parenthesis "(...)", it is part of the subject.
        if(secondWord.startsWith("(") && secondWord.endsWith(")")){
            subjectString = words.get(0) + " " + secondWord;
        }else{
            subjectString = words.get(0); //subject has no parenthesis
        }
        subject = subjDictionary.get(subjectString);
        if(subject == null){ //Couldn't find subject in dictionary
            //TODO Find subject if number is >3, e.g. "we (7 excl)"
        }

        //Find verb
        for(String key : verbDictionary.keySet()){
            if(s.contains(key)){
                verb = verbDictionary.get(key);
                break; //Assuming there is only one verb in the sentence
            }
        }
    }
}
