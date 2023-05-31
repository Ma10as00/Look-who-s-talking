package main;
import java.util.HashMap;
import java.util.Map;

public class MaoriWriter {

    private Map<VerbPropertySet, String> verbDictionary;        // Abstractioin --> Māori
    private Map<SubjectPropertySet, String> subjDictionary;     // Abstractioin --> Māori
    private Map<Tense, String> tenseMarkers;    //Only used for building verbDictionary

    private VerbPropertySet verb;       //Māori
    private SubjectPropertySet subject; //Māori

    public MaoriWriter(VerbPropertySet verb, SubjectPropertySet subject){
        this.verb = verb;
        this.subject = subject;

        //Build tenseMarkers
        tenseMarkers = new HashMap<>();
        tenseMarkers.put(Tense.PAST,"I");
        tenseMarkers.put(Tense.PRESENT,"Kei te");
        tenseMarkers.put(Tense.FUTURE,"Ka");

        //Build verbDictionary
        verbDictionary = new HashMap<>();
        for(Meaning m : Meaning.values()){
            for(Tense t : Tense.values()){
                String translatedVerb = switch (m) {
                    case GO -> "haere";
                    case MAKE -> "hanga";
                    case SEE -> "kite";
                    case WANT -> "hiahia";
                    case CALL -> "karanga";
                    case ASK -> "p\u0101tai";   //pātai
                    case READ -> "p\u0101nui";  //pānui
                    case LEARN -> "ako";
                    default -> null;
                };
                if(translatedVerb != null){
                    VerbPropertySet vps = new VerbPropertySet(m, t);
                    verbDictionary.put(vps, tenseMarkers.get(t) + " " + translatedVerb);
                }
            }
        }

        //Build subjDictionary
        subjDictionary = new HashMap<>();
        subjDictionary.put(new SubjectPropertySet(NoPeople.SINGULAR,Person.FIRST_EXCL), "au");
        subjDictionary.put(new SubjectPropertySet(NoPeople.SINGULAR,Person.SECOND), "koe");
        subjDictionary.put(new SubjectPropertySet(NoPeople.SINGULAR,Person.THIRD), "ia");
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.FIRST_INCL), "t\u0101ua");       //tāua
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.FIRST_EXCL), "m\u0101ua");       //māua
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.SECOND), "k\u014Drua");          //kōrua
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.THIRD), "r\u0101ua");            //rāua
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.FIRST_INCL), "t\u0101tou");    //tātou
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.FIRST_EXCL), "m\u0101tou");    //mātou
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.SECOND), "koutou");
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.THIRD), "r\u0101tou");         //rātou
    }

    public String getTranslation(){
        if(subject == null || verb == null){
            return "INVALID";
        }else{
            return verbDictionary.get(verb) + " " + subjDictionary.get(subject);
        }
    }

    public void write(){
        System.out.println(getTranslation());
    }
}
