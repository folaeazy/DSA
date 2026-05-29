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
        System.out.println(slidingWindowSolution(s, t));
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
        if(s.isEmpty() || t.isEmpty()) return "";
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
     * Time complexity -- 0(n)
     */
    static String slidingWindowSolution(String s, String t) {
        if(s.isEmpty() || t.isEmpty())
            return  "";

        Map<Character, Integer> need = new HashMap<>();
        // build the need map
        for(char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int required = need.size(); // the number of unique characters required
        int formed = 0;  // used to track the number of required satisfied

        // have -- hashmap
        Map<Character, Integer> have = new HashMap<>();
        int[] result = {-1, 0, 0}; // result initialized

        int L = 0; // left pointer

        // right expand and left shrink
        for(int R = 0 ; R < s.length(); R++) {
            char c = s.charAt(R);
            have.put(c, have.getOrDefault(c, 0) +  1);

            // what we added if its needed , we increment formed
            if(need.containsKey(c) && have.get(c).equals(need.get(c))){
                formed++;
            }

            // Shrinking from L
            // if true it is a valid window
            // update result and shrink L
            while (formed == required && L <= R) {
                if(result[0] == -1 || R - L + 1 <= result[0]) {
                    result[0] = R - L + 1;
                    result[1] = L;
                    result [2] = R ;
                }


                char leftChar = s.charAt(L);
                have.put(leftChar, have.get(leftChar) - 1 ); // remove from left
                if(need.containsKey(leftChar) && have.get(leftChar) < need.get(leftChar)) {
                    formed--;
                }

                L++;


            }

        }
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);

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
