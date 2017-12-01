package systemdesign.jiuzhang.week2;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by zhahua on 7/15/17.
 * TODO: how to implement LRU? TTL
 */
public class Memcache {

  public static void main(String[] args) {
    Memcache m = new Memcache();
    m.main1();

  }
  public void main1() {
    get(1, 0);
//        >> 2147483647
    set(2, 1, 1, 2);
    get(3, 1);
//        >> 1
    get(4, 1);
//        >> 2147483647
    incr(5, 1, 1);
//        >> 2147483647
    set(6, 1, 3, 0);
    incr(7, 1, 1);
//        >> 4
    decr(8, 1, 1);
//        >> 3
    get(9, 1);
//        >> 3
    delete(10, 1);
    get(11, 1);
//        >> 2147483647
    incr(12, 1, 1);
//        >> 2147483647
  }

  class Entry {
    private int expireTime = 0;
    private int value;
    public Entry(int value,int creatTime,int ttl) {
      this.value = value;
      if(ttl != 0) {
        this.expireTime = creatTime + ttl;
      }
    }
    public Entry(int value,int expireTime) {
      this.value = value;
      this.expireTime = expireTime;
    }
    public int getValue() {
      return value;
    }
    public int getExpireTime() {
      return expireTime;
    }
  }

  public final int EMPTY_VALUE = 2147483647;
  ConcurrentMap<Integer,Entry> map = new ConcurrentHashMap<>();

  public Memcache() {
    // Initialize your data structure here.
  }

  public int get(int curtTime, int key) {
    // Write your code here
    deleteIfExpired(curtTime,key);
    Entry entry = map.get(key);
    if(entry == null) {
      return EMPTY_VALUE;
    }
    return entry.value;
  }

  public void set(int curtTime, int key, int value, int ttl) {
    // Write your code here
    Entry entry = new Entry(value, curtTime, ttl);
    map.put(key,entry);
  }

  private void deleteIfExpired(int curtTime, int key) {
    Entry entry = map.get(key);
    if(entry != null && entry.expireTime != 0 && entry.expireTime <= curtTime) {
      map.remove(key);
    }
  }

  public void delete(int curtTime, int key) {
    // Write your code here
    map.remove(key);
  }

  public int incr(int curtTime, int key, int delta) {
    // Write your code here
    deleteIfExpired(curtTime,key);
    Entry oldEntry;
    Entry newEntry;
    do {
      oldEntry = map.get(key);
      if(oldEntry == null) {
        return EMPTY_VALUE;
      }
      newEntry = new Entry(oldEntry.value + delta,oldEntry.expireTime);
    }while(!map.replace(key,oldEntry,newEntry));
    return newEntry.value;
  }

  public int decr(int curtTime, int key, int delta) {
    // Write your code here
    // Write your code here
    deleteIfExpired(curtTime,key);
    Entry oldEntry;
    Entry newEntry;
    do {
      oldEntry = map.get(key);
      if(oldEntry == null) {
        return EMPTY_VALUE;
      }
      newEntry = new Entry(oldEntry.value - delta,oldEntry.expireTime);
    }while(!map.replace(key,oldEntry,newEntry));
    return newEntry.value;
  }
}
