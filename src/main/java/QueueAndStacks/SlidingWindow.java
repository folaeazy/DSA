package QueueAndStacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindow {


    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(slidingWindow(nums, 3)));

    }

    static int[] slidingWindow(int[] num, int k) {
        int n = num.length;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[n - k + 1];
        int resultIdx = 0;

        for(int i = 0; i < n; i++) {

            // evict indices that are out of the window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k ) {
                deque.pollFirst();
            }

            //pop smaller values from the back - they cannot be max again
            while (!deque.isEmpty() && num[deque.peekLast()] < num[i]) {
                deque.pollLast();
            }

            //current index is now a valid candidate
            deque.offerLast(i);

            //once the first window is formed - record result
            if(i >= k - 1) {
                result[resultIdx++] = num[deque.peekFirst()];
            }
        }
        return result;
    }

}
