package main.properties;
import java.util.HashSet;

/**
 * Set of all properties we need to translate the subjects from English to Māori.
 * @author Mathias Øgaard
 */
public class SubjectPropertySet extends HashSet<Property>{
    
    public SubjectPropertySet(NoPeople n, Person p){
        super();
        add(n); add(p);
    }
}
