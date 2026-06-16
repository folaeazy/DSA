package hashing;

import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        String[] str = {"bat", "tab", "ant", "tan", "mat", "tame"};
        //System.out.println(GroupAnagrams.naiveSolution(str));
        //System.out.println(GroupAnagrams.hashMapMethod(str));
        System.out.println(GroupAnagrams.groupAnagrams(str));


    }


    /**
     *
     * counting frequency of str - no sorting
     * Time complexity - O(n . K)
     */
    public static List<List<String>> groupAnagrams(String[] str) {
        Map<String, List<String>> map = new HashMap<>();



        for (String s : str) {
            // count frequency
            int[] count = new int[26];

            for (char c  : s.toCharArray()) {
                count[c - 'a']++ ; // i.e [E.G b = 98(ASCII) ] -- 98-97 = count[1]++
            }

            // build string from the frequency array
            // # as a delimiter

            StringBuilder builder = new StringBuilder();
            for(int freq : count) {
                builder.append(freq).append('#');
            }
            String key = builder.toString();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);

        }

        return new ArrayList<>(map.values());
    }

    /**
     *
     * Sort all strings once -
     * create a key with its canonical form and add list of values as the original string
     * Time complexity - O(n . k log k)
     */
    public static List<List<String>> hashMapMethod(String[] str) {

        Map<String, List<String>> map = new HashMap<>();

        for(String s : str) {
            // convert to array of char for sorting
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            /**
             * if key exist insert the original string s
             * if key doesn't exist create an empty list return and insert string s
             */
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return  new ArrayList<>(map.values());
    }

    /**
     *
     * Time complexity O(n^2 . K log K )
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
