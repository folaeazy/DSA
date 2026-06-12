package hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupAnagrams {

    public static void main(String[] args) {
        String[] str = {"bat", "tab", "ant", "tan", "mat", "tame"};
        System.out.println(GroupAnagrams.naiveSolution(str));

    }

    /**
     *
     * Time complexity O(n^2 . K )
     */
    public static List<List<String>> naiveSolution(String[] str) {
        int n = str.length;
        List<List<String>> result = new ArrayList<>();
        boolean[] grouped = new boolean[n]; // track if a string has been grouped

        //outer loop
        for(int i = 0; i < n ; i++) {
            //confirm if string has been grouped
            if(grouped[i]) continue;

            ArrayList<String> group = new ArrayList<>(); // for each group

            group.add(str[i]);
            grouped[i] = true;

            // inner loop
            for(int j = i + 1; j < n; j++) {
                if(!grouped[j] && isAnagram(str[i], str[j])) {
                    group.add(str[j]);
                    grouped[j] = true;

                }
            }
            result.add(group);

        }
        return result;

    }


    // sort both strings and compare — anagrams produce identical sorted strings
    private static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        char[] ca = a.toCharArray(); Arrays.sort(ca);
        char[] cb = b.toCharArray(); Arrays.sort(cb);
        return Arrays.equals(ca, cb);
    }
}
