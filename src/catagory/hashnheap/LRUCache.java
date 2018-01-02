package catagory.hashnheap;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  public static void main(String[] args) {
    LRUCache lru = new LRUCache(3);
    lru.set(1, 1);
    lru.set(2, 2);
    lru.set(3, 3);
    lru.set(4, 4);
    lru.get(4);
    lru.get(3);
    lru.get(2);
    lru.get(1);
    lru.set(5, 5);
    lru.get(1);
    lru.get(2);
    lru.get(3);
    lru.get(4);
    lru.get(5);
  }
  private class Node {
    public int val;
    public Node next;
    public int key;
    public Node(int val, int key) {
      this.val = val;
      this.key = key;
    }
  }
  int capacity;
  /*
   * @param capacity: An integer
   */public LRUCache(int capacity) {
    // do intialization if necessary
    this.capacity = capacity;
  }
  Map<Integer,Node> cache = new HashMap<>();

  Node head = new Node(0,0);

  Node tail = head;

  int sz = 0;
  /*
   * @param key: An integer
   * @return: An integer
   */
  public int get(int key) {
    // write your code here
    Node crt = cache.get(key);
    if (crt != null) {
      int value = crt.next.val;
      deleteByKey(key);
      add(key, value);
      return value;
    }
    return -1;
  }

  /*
   * @param key: An integer
   * @param value: An integer
   * @return: nothing
   */
  public void set(int key, int value) {
    // write your code here
    Node crt = cache.get(key);
    if (crt != null) {
      deleteByKey(key);
    }
    add(key, value);
  }
  private void add(int key, int value) {
    Node tmp = new Node(value, key);
    cache.put(key, tail);
    tail.next = tmp;
    tail = tmp; //****
    sz++;
    if (sz > capacity) {
      deleteLRU();
    }
  }

  private void deleteLRU() {
    deleteByKey(head.next.key);
  }
  private void deleteByKey(int key) {
    Node crt = cache.get(key);
    cache.remove(key);
    Node next2 = crt.next.next;

    crt.next = next2;
    if (next2 != null) {
      cache.put(next2.key, crt);
    } else {
      tail = crt;
    }
    sz--;
  }

}
