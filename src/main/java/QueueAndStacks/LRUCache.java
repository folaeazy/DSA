package QueueAndStacks;

import java.util.HashMap;

/**
 * Design a cache with O(1) get and put, evicting the least recently used item at capacity
 * LC #146
 */
public class LRUCache {

    // double-linked list imp
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) { this.key = key; this.value = value; }
    }

    private int capacity;
    private HashMap<Integer, Node> map;

    //sentinel - dummy head and tail - never holds real value: why ? to avoid constant null check of the Node.
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    // helper: detach a node from wherever it currently sits
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // helper: insert a Node right after head make it MRU
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node); //remove node
        addToFront(node); // add after head to make it MRU
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) { //if key exist update and make MRU
            Node node = map.get(key);
            node.value = value;
            remove(node);
            addToFront(node);
            return;
        }

        if(map.size() == capacity) {
            // at capacity full remove the LRU to add new  NOde
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key); //also remove from map
        }

        Node newNode = new Node(key,value);
        map.put(key, newNode);
        addToFront(newNode);
    }

    

    public static void main(String[] args) {
        System.out.println("=== LRU Cache — proving map and list share the same Node object ===\n");

        LRUCache cache = new LRUCache(3);

        System.out.println("put(1, 100)");
        cache.put(1, 100);
        cache.printOrder("  order");
        cache.proveSameObject(1);

        System.out.println("\nput(2, 200)");
        cache.put(2, 200);
        cache.printOrder("  order");

        System.out.println("\nput(3, 300)");
        cache.put(3, 300);
        cache.printOrder("  order");

        System.out.println("\nget(1)  -> should move key 1 to the FRONT (MRU)");
        int v = cache.get(1);
        System.out.println("  returned value = " + v);
        cache.printOrder("  order");
        cache.proveSameObject(1);

        System.out.println("\nput(4, 400)  -> cache is full (capacity 3), should evict key 2 (it is now LRU)");
        cache.put(4, 400);
        cache.printOrder("  order");

        System.out.println("\nget(2)  -> should return -1, key 2 was evicted");
        System.out.println("  returned value = " + cache.get(2));

        System.out.println("\n=== Now the key proof: mutate through the MAP reference, check the LIST sees it ===");
        LRUCache.Node n = cache.map.get(3);
        System.out.println("Before mutation, list shows:");
        cache.printOrder("  order");
        System.out.println("Manually changing node.value via the reference we got from the MAP...");
        n.value = 9999;
        System.out.println("After mutating the map's reference, list now shows:");
        cache.printOrder("  order");
        System.out.println("  -> key 3's value changed to 9999 in the LIST too, because it is the SAME object.");
    }

    private void proveSameObject(int key) {
        Node fromMap = map.get(key);
        // walk the list to find the "same" key manually
        Node cur = head.next;
        Node fromList = null;
        while (cur != tail) {
            if (cur.key == key) { fromList = cur; break; }
            cur = cur.next;
        }
        boolean identical = (fromMap == fromList); // == compares OBJECT IDENTITY (memory address), not .equals()
        System.out.println("   key=" + key
                + " | map's Node hashCode=" + System.identityHashCode(fromMap)
                + " | list's Node hashCode=" + System.identityHashCode(fromList)
                + " | SAME OBJECT? " + identical);
    }

    private void printOrder(String label) {
        StringBuilder sb = new StringBuilder(label + ": [");
        Node cur = head.next;
        while (cur != tail) {
            sb.append(cur.key).append("=").append(cur.value);
            if (cur.next != tail) sb.append(", ");
            cur = cur.next;
        }
        sb.append("]  (front=MRU ... back=LRU)");
        System.out.println(sb);
    }

}
