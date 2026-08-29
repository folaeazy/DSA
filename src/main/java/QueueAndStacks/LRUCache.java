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

}
