package systemdesign.jiuzhang.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by zhahua on 7/15/17.
 */
public class ConsistentHashing {
  public static void main(String args[]) {
    System.out.println(new ConsistentHashing().consistentHashing(5).toString());
  }
  static final int HASH_SPACE = 360;


  /**
   * @param n a positive integer
   * @return n x 3 matrix
   */
  public List<List<Integer>> consistentHashing(int n) {
    // Write your code here
    int initCapacity = 1;
    List<HashRange> ranges = createInitialRange(initCapacity);

    PriorityQueue<HashRange> queue = new PriorityQueue<>(ranges);

    for(int i = initCapacity; i < n; i++) {
      HashRange range = queue.poll();
      queue.offer(new HashRange(range.start,range.start + (range.end - range.start) / 2,range.mid));
      queue.offer(new HashRange(range.start + (range.end - range.start) / 2 + 1,range.end,i + 1));
    }

    ranges = new ArrayList(queue);
    Collections.sort(ranges, new Comparator<HashRange>() {
      @Override
      public int compare(HashRange o1, HashRange o2) {
        return o1.start - o2.start;
      }
    });
    List<List<Integer>> result = new ArrayList<>();
    for(HashRange range : ranges) {
      result.add(range.toList());
    }
    return result;

  }

  public List<HashRange> createInitialRange(int initCapacity) {
    List<HashRange> ranges = new ArrayList<>();
    for(int i = 0; i < initCapacity; i++) {
      HashRange range = new HashRange(HASH_SPACE * i / initCapacity,
          HASH_SPACE * (i + 1) / initCapacity - 1, i + 1);
      ranges.add(range);
    }
    return ranges;
  }

  static class HashRange implements Comparable<HashRange> {
    private int start;
    private int end;
    private int mid;

    public List<Integer> toList() {
      List<Integer> result = new ArrayList<>();
      result.add(start);
      result.add(end);
      result.add(mid);
      return result;
    }
    public HashRange(int start, int end, int mid) {
      this.start = start;
      this.end = end;
      this.mid = mid;
    }
    @Override
    public String toString() {
      return toList().toString();
    }
    @Override
    public int compareTo(HashRange other) {
      int result = (other.end - other.start) - (end - start);
      if(result == 0) {
        result = mid - other.mid;
      }
      return result;
    }

  }
}
