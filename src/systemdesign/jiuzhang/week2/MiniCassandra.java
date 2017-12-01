package systemdesign.jiuzhang.week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Definition of Column:
 * public class Column {
 *     public int key;
 *     public String value;
 *     public Column(int key, String value) {
 *         this.key = key;
 *         this.value = value;
 *    }
 * }
 */
public class MiniCassandra {

  public static void main(String[] args) {
    new MiniCassandra();
  }
  public MiniCassandra() {
    // initialize your data structure here.
    insert("google", 1, "haha");
    query("google", 0, 1);
    insert("google", 2, "haha2");
    insert("google", 3, "haha3");
    query("google", 1, 2);

  }

  ConcurrentMap<String, Columns> cache = new ConcurrentHashMap<>();

  /**
   * @param raw_key a string
   * @param column_start an integer
   * @param column_end an integer
   * @return void
   */
  public void insert(String raw_key, int column_key, String column_value) {
    // Write your code here
    Columns columns = cache.get(raw_key);
    if(columns == null) {
      columns = new Columns();
      Columns oldColumns = cache.putIfAbsent(raw_key,columns);
      if(oldColumns != null) {
        columns = oldColumns;
      }
    }
    columns.lock.writeLock().lock();
    try{
      columns.map.put(column_key,new Column(column_key,column_value));
    }finally {
      columns.lock.writeLock().unlock();
    }
  }

  /**
   * @param raw_key a string
   * @param column_start an integer
   * @param column_end an integer
   * @return a list of Columns
   */
  public List<Column> query(String raw_key, int column_start, int column_end) {
    // Write your code here
    Columns columns = cache.get(raw_key);
    if(columns == null) {
      return Collections.EMPTY_LIST;
    }
    columns.lock.readLock().lock();
    try {
      java.util.Map.Entry<Integer, Column> entry = columns.map.ceilingEntry(column_start);
      List<Column> result = new ArrayList<>();

      while (entry != null && entry.getKey() <= column_end) {
        result.add(entry.getValue());
        entry = columns.map.higherEntry(entry.getKey());
      }
      return result;
    }finally {
      columns.lock.readLock().unlock();
    }
  }
  static class Columns {
    public ReadWriteLock lock = new ReentrantReadWriteLock();
    public TreeMap<Integer,Column> map = new TreeMap<>();
  }
  static class Column {
    public int key;
    public String value;
    public Column(int key, String value) {
      this.key = key;
      this.value = value;
    }
  }
}