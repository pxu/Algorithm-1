package catagory.hashnheap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLinkedHashMap {

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
  /*
   * @param capacity: An integer
   */public LRUCacheLinkedHashMap(int capacity) {
    // do intialization if necessary
    final int capa = capacity;
    cache = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) { // accessOrder:
      //     * @param  accessOrder     the ordering mode - <tt>true</tt> for
      //     *         access-order, <tt>false</tt> for insertion-order
      public boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
        return this.size() > capa;
      }

    };
  }
  Map<Integer, Integer> cache;
  /*
   * @param key: An integer
   * @return: An integer
   */
  public int get(int key) {
    // write your code here
    Integer val = cache.get(key);
    return val == null ? -1 : val;
  }

  /*
   * @param key: An integer
   * @param value: An integer
   * @return: nothing
   */
  public void set(int key, int value) {
    // write your code here
    cache.put(key, value);
  }

}
