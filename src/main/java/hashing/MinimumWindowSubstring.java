package hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution with 2 methods
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "abobecodebanc";
        String t =  "abc";
        System.out.println(naiveMethodSolution(s, t));
    }

    /**
     *
     * @param s - s is the string given
     * @param t - t is the substring
     * @return the minimum of t
     * Time complexity -- O(n^2 * k) -- due to the containsAll method
     * Space complexity --
     */
    static  String naiveMethodSolution(String s, String t) {
        int n = s.length();
        String result = "";

        //build need HashMap
        HashMap<Character, Integer> need = new HashMap<>();

        for(char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        // trying every possible starting position

        //outer loop for starting point
        for(int i = 0; i < n; i++) {
            Map<Character, Integer> have = new HashMap<>();

            //inner loop extending right
            for(int j = i; j < n; j++) {
                char c = s.charAt(j);
                 //update have
                have.put(c, have.getOrDefault(c, 0) + 1);

                if(containsAll(have, need)) {
                    String window = s.substring(i , j + 1);

                    if(result.isEmpty() || result.length() > window.length()) {
                        result = window;
                    }
                    break;

                }

            }

        }

        return result;



    }


    /**
     *
     * Time complexity --
     */
    static String slidingWindowSolution(String s, String t) {

    }

    private static boolean containsAll(Map<Character, Integer> have,
                                Map<Character, Integer> need) {
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            if (have.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
