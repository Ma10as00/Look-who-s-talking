package main.properties;
import java.util.HashSet;

/**
 * Set of all properties we need to translate the verbs from English to Māori.
 * @author Mathias Øgaard
 */
public class VerbPropertySet extends HashSet<Property>{
    
    public VerbPropertySet(Meaning m, Tense t){
        super();
        add(m); add(t);
    }

    //For english:
    public VerbPropertySet(Meaning m, Tense t, NoPeople n, Person p){
        super();
        add(m); add(t); add(n); add(p);
    }
}
