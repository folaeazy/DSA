package hashing;

/**
 * Fixed window problem
 * Solved with Naive method and sliding window method
 */
public class FixedWindow {


    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] arr1 = {4,2,0,3,2,5};
        System.out.println(naiveMethod(arr, 2));
        System.out.println(slidingWindowMethod(arr, 2));
    }

    /**
     *
     * @param arr of integers
     * @param k size of the fixed window
     * @return maximum sum of windows of size k
     * Time complexity = O(n * k)
     * Space complexity = O(1)
     */
    static int naiveMethod(int[] arr, int k){
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        // Outer loop for window starting position
        for(int i = 0; i <= n - k; i++ ) {
            int windowSum = 0;

            // inner loop for window sum
            for (int j = i; j < k + i; j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }


    /**
     * @param arr of integers
     * @param k size of the fixed window
     * @return maximum sum of windows of size k
     * Time complexity = O(n)
     * Space complexity = O(1)
     */
    static int slidingWindowMethod(int[] arr, int k) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        int windowSum = 0;
        // build the first sum
        for(int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        maxSum = windowSum;

        // Slide window over arr
        for(int i = k; i < n; i++) {
            windowSum = windowSum + arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;

    }
}
