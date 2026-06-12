package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Listing {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("foo");
        List<String> list2 = list1;

        List<String> list3 = new ArrayList<>(list2); // [foo]

        list1.clear();
        list2.add("bar"); //  [bar]
        list3.add("baz"); // [foo, baz]





        boolean res = list2.equals(list1);

        System.out.println(list1); // [bar]
        System.out.println(list2); // [bar]
        System.out.println(list3); // [foo, baz]
    }


}
