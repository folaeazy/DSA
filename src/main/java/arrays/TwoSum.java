package arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Two sum problem solved with different method
 */
public class TwoSum {

    static class Pair {
        private final int value;
        private final int index;

        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }


    public static void main(String[] arg) {
        int[] numbs = {1,2,3,4,5,6};
        int sum = 4;
        System.out.println(Arrays.toString(twoPointersUnsorted(sum, numbs)));
    }




    /**
     *  Question URL link
     *  <a href="https://leetcode.com/problems/two-sum/?envType=problem-list-v2&envId=array">...</a>
     */


    /**
     *Solution with two pointers method
     * Assumed it's sorted array
     */
    static int[] twoPointersSorted(int target, int[] array) {
        int left = 0;
        int right = array.length - 1 ;

        while(left < right) {
            int sum = array[left] + array[right];

            if(sum == target) {
                return new int[] {left, right};
            } else if (sum < target) {
                left++;

            }else {
                right--;
            }
        }
        return new int[] {-1, -1 } ;// no solution



    }

    /**
     * For unsorted Array
     * We Store the original pair - value and index
     * We sort it and apply two pointer method
     */
    static int[] twoPointersUnsorted(int target, int[] array) {
        // Allocate memory for pairs
        Pair[] pairs = new Pair[array.length];

        //save original pair (index and value)
        for(int i = 0; i < array.length; i++){
            pairs[i] = new Pair(array[i], i);
        }
        // Sort Array
        Arrays.sort(pairs, Comparator.comparingInt(p -> p.value));

        // Apply two pointer method
        int left = 0;
        int right = pairs.length - 1;
        while (left < right) {
            int sum = pairs[left].value + pairs[right].value;

            if(sum == target) {
                return new int[]{pairs[left].index, pairs[right].index};
            } else if (sum < target) {
                left++;

            }else {
                right--;
            }
        }

        return new int[] {-1 , -1};
    }


    /*
    Solution using hashmap method
     */
    static int[] Hashmap(int target, int[] array) {
        return new int[4];
    }

}
