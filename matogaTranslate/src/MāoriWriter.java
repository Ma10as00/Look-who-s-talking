import java.util.HashMap;
import java.util.Map;

public class MāoriWriter {
    private VerbPropertySet verb;
    private SubjectPropertySet subject;

    private Map<VerbPropertySet, String> verbDictionary;
    private Map<SubjectPropertySet, String> subjDictionary;

    private Map<Tense, String> tenseMarkers;

    public MāoriWriter(VerbPropertySet verb, SubjectPropertySet subject){
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
                VerbPropertySet vps = new VerbPropertySet(m, t);
                verbDictionary.put(vps, tenseMarkers.get(t) + " " + m.toString());
            }
        }

        //Build subjDictionary
        subjDictionary = new HashMap<>();
        subjDictionary.put(new SubjectPropertySet(NoPeople.SINGULAR,Person.FIRST_EXCL), "ahau");
        subjDictionary.put(new SubjectPropertySet(NoPeople.SINGULAR,Person.SECOND), "koe");
        subjDictionary.put(new SubjectPropertySet(NoPeople.SINGULAR,Person.THIRD), "ia");
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.FIRST_INCL), "tāua");
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.FIRST_EXCL), "māua");
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.SECOND), "kōrua");
        subjDictionary.put(new SubjectPropertySet(NoPeople.DUAL,Person.THIRD), "rāua");
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.FIRST_INCL), "tātou");
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.FIRST_EXCL), "mātou");
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.SECOND), "koutou");
        subjDictionary.put(new SubjectPropertySet(NoPeople.PLURAL,Person.THIRD), "rātou");
    }

    public String produceString(){
        return verbDictionary.get(verb) + " " + subjDictionary.get(subject);
    }

    public void write(){
        System.out.println(produceString());
    }
}
