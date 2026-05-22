package arrays;

/**
 * Trapping rain water problem solved using 3 methods
 * 1 -- Naive method that implement the insight directly
 * 2 -- Prefix array method
 * 3 -- Two pointer method
 *
 * Question URL : <a href="https://leetcode.com/problems/trapping-rain-water/?envType=problem-list-v2&envId=array">...</a>
 */
public class TrappingRainWater {

    public static void main(String[] arg) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] arr1 = {3,0,2};
        System.out.println(naiveMethod(arr1));
        System.out.println(prefixArrayMethod(arr1));
        System.out.println(twoPointerMethod(arr));

    }


    /**
     *
     * Time complexity   ---- O(n^2)
     * Space complexity O(1)
     */
    static int naiveMethod(int[] heights) {

        if (heights.length == 0) return 0;

        int sum  = 0;
        for(int point = 0;  point < heights.length ; point++) {
            int maxLeft = 0, maxRight = 0;

            // For max left
            for(int left = 0; left < point; left++) {
                maxLeft = Math.max(maxLeft, heights[left]);
            }

            //for max right
            for(int right = point + 1; right < heights.length ; right++) {
                maxRight = Math.max(maxRight, heights[right]);
            }

            // applying formulae directly
            int unit = Math.max(0, Math.min(maxLeft, maxRight) - heights[point]);
            sum += unit;
        }
        return sum;

    }


    /**
     * Prefix method solution
     * Time complexity -- O(n)
     * Space complexity -- O(n)
     */
    static int prefixArrayMethod(int[] heights) {
        int n = heights.length;
        if( n == 0) return  0;
        // Gather max left
        int[] maxLeft = new int[n];
        maxLeft[0] = 0;
        for(int left = 1; left < n; left++) {
            maxLeft[left]  =  Math.max(maxLeft[left - 1], heights[left - 1]);
        }

        //Gather max right
        int[] maxRight = new int[n];
        maxRight[n - 1] = 0 ;
        for(int right = n - 2; right > 0; right--) {
            maxRight[right] = Math.max(maxRight[right + 1], heights[right + 1]);
        }

        int sum = 0;
        for(int i = 0; i < n; i++){
            // Applying formulae
            int result = Math.max(0, Math.min(maxLeft[i], maxRight[i]) - heights[i]);
            sum+= result;
        }

        return sum;

    }

    /**
     *
     * Time complexity --- O(n)
     * Space complexity --- O(1)
     */
    static int twoPointerMethod(int[] heights) {

        int L = 0;
        int R = heights.length - 1;
        int maxLeft = 0;
        int maxRight = 0 ;
        int totalSum = 0;

        while (L < R) {
            if(maxLeft <= maxRight) {
                maxLeft = Math.max(maxLeft, heights[L]);
                totalSum += Math.max(0, maxLeft - heights[L]);
                L++;
            } else {
                maxRight = Math.max(maxRight, heights[R]);
                totalSum += Math.max(0, maxRight - heights[R]);
                R--;
            }
        }
        return totalSum;

    }
}
