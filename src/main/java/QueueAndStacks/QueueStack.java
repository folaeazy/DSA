package QueueAndStacks;

import java.util.ArrayDeque;
import java.util.Deque;


public class QueueStack {
    public static void main(String[] args) {

        // stack implementation with Deque
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); // addFirst
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop(); //removeLast
        System.out.println(stack);



        Deque<Integer> queue =  new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4); //add last
        queue.poll(); // removeFirst
        System.out.println(queue);
    }
}
