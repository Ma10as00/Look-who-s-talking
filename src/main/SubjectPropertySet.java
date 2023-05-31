package main;
import java.util.HashSet;

public class SubjectPropertySet extends HashSet<Property>{
    
    public SubjectPropertySet(NoPeople n, Person p){
        super();
        add(n); add(p);
    }
}
