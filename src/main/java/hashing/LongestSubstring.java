package hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Solved with Naive method and two pointer HashMap
 */
public class LongestSubstring {

    public static void main(String[] args){
        String stat = "abcdebccabcbabcdef";
        System.out.println(lengthOfLongestSubstringNaive(stat));
        System.out.println(longestSubstringSlidingWindowMethod(stat));

    }

    /**
     *
     * Time complexity O(n^2)
     * Space Complexity --
     */
    public static int lengthOfLongestSubstringNaive(String s) {
        int n = s.length();

        int longestSubSum = 0;
        // outer loop for starting point
        for(int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();

            // inner loop for end point
            for(int j = i; j < n; j++) {
                char c = s.charAt(j);
                if(seen.contains(c)) {
                    break;
                }
                seen.add(c);
                longestSubSum = Math.max(longestSubSum, j - i + 1); // range of the substring (j -i + 1)

            }
            // resets the Hashset for new starting point once it breaks
        }

        return longestSubSum;

    }

    /**
     * Time complexity O(n)
     * @param s
     * @return
     */
    static int longestSubstringSlidingWindowMethod(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int L = 0;
        int sum = 0;

        for(int R = 0; R< n ; R++) {
            char c = s.charAt(R);

            // check existence/stale and position
            if(map.containsKey(c) && map.get(c) >= L) {
                L = map.get(c) + 1; // jump past previous occurrence
            }

            //update the map
            map.put(c, R);
            sum = Math.max(sum, R - L + 1);

        }

        return sum;
    }

}
