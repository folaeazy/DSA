package QueueAndStacks;

import java.util.*;

/**
 * Daily Temperature Problem (LC 739)
 * Question Url :
 *
 * Solved with Brute force method,
 */
public class DailyTemperature {

    public static void main(String[] args) {
        int[] temps = {73,74,75,71,69,72,76,73};
        System.out.println(bruteForce(temps));
        System.out.println(Arrays.toString(monotonicMethod(temps)));
    }

    /**
     * Time complexity - O(n^2)
     */
    static List<Integer> bruteForce(int[] temps) {
        int L = temps.length;
        ArrayList<Integer> result = new ArrayList<>();

        // outer-loop to track current position
        for(int x = 0; x < L; x++) {
            int count = 0;

            for(int y = x + 1; y < L ; y++) {
                if(temps[y] > temps[x]) {
                    count = y - x;
                    break;
                }
            }
            result.add(count);
        }
        return  result;

    }

    /**
     * Time complexity - O(n)
     */
    static int[] monotonicMethod(int[] temp) {
        int length = temp.length;
        int[] result = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int day = 0; day < length; day++) {

            // while today is warmer than the day on top of the stack -
            // pop the stack and wait to resolve
            while(!stack.isEmpty() && temp[day] >  temp[stack.peek()]){
                int prevDay = stack.pop();
                result[prevDay] = day - prevDay;
            }

            // Today is waiting for a warmer day
            stack.push(day);
        }
        return result;

    }
}
