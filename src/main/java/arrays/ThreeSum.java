package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3 sum problem solved using sort + two pointer method
 * Question URL --
 * <a href="https://leetcode.com/problems/3sum/solutions/7448027/just-keep-things-simple-by-gsai122-9if7/?envType=problem-list-v2&envId=array">...</a>
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));

    }

    static List<List<Integer>> threeSum(int[] array) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(array);

        for (int i = 0; i < array.length - 2; i++) {

            // skip outer static pointer duplicate
            if(i > 0 && array[i] == array[i -1]) continue;
            int left = i + 1;
            int right = array.length - 1;

            while (left < right) {
                int sum = array[i] + array[left] + array[right];
                if(sum == 0) {
                     result.add(Arrays.asList(array[i], array[left], array[right]));

                    // skip duplicates
                    while (left < right && array[left] == array[left + 1]) left++;
                    while (left < right && array[right] == array[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                }else {
                    right--;
                }


            }

        }
        return result;

    }
}
