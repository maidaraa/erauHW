import java.util.*;

public class LinkedHashHW4 {

    public static void main(String[] args){
        
        Set<String> set1 = new LinkedHashSet<>();
        Set<String> set2 = new LinkedHashSet<>();
       
        set1.add("George");
        set1.add("Jim");
        set1.add("John");
        set1.add("Blake");
        set1.add("Michael");

        set2.add("George");
        set2.add("Katie");
        set2.add("Kevin");
        set2.add("Michelle");
        set2.add("Ryan");

        Set<String> union = new LinkedHashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union of the sets: " + union);

        Set<String> intersection = new LinkedHashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection of the sets: " + intersection);

        Set<String> difference = new LinkedHashSet<>(union);
        difference.removeAll(intersection);
        System.out.println("Difference between sets 1 and 2: " + difference);
    }

}