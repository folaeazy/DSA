package arrays;

import java.util.Arrays;

/**
 * 08/05/2026
 * Question URl link
 * <a href="https://leetcode.com/problems/median-of-two-sorted-arrays/description/?envType=problem-list-v2&envId=array">...</a>
 */
public class MedianSortedArray {

    public static void main(String[] args) {
         int[] num1 = {1,3};
         int[] num2 = {2};

         System.out.println(findMergedMedian(num1, num2));




    }

    static double findMergedMedian(int[] array1, int[] array2) {
        int[] merged = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, merged, 0, array1.length);
        System.arraycopy(array2,0, merged,array1.length, array2.length);
        Arrays.sort(merged);

        double median;
        if(merged.length % 2 == 0) {
            int middle = merged.length / 2 ;
            int[] result =  {merged[middle], merged[middle -1]};
             median = (result[0] + result[1]) /2.0 ;
        }else {
            int ans = Math.round(merged.length / 2);
            median = merged[ans];
        }
        return median;
    }


}
