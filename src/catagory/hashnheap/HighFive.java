package catagory.hashnheap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * http://www.lintcode.com/en/problem/high-five/
 */
public class HighFive {
  class Record {
    public int id, score;
    public Record(int id, int score){
      this.id = id;
      this.score = score;
    }
  }
  public Map<Integer, Double> highFive(Record[] results) {
    Map<Integer, PriorityQueue<Integer>> scores = new HashMap<>();

    for (Record record : results) {
      PriorityQueue<Integer> queue = scores.computeIfAbsent(record.id,
          k -> new PriorityQueue<Integer>());
      queue.offer(record.score);
      if (queue.size() > 5) {
        queue.poll();
      }
    }
    Map<Integer, Double> result = new HashMap<>();
    for (Map.Entry<Integer, PriorityQueue<Integer>> entry
        : scores.entrySet()) {
      int sum = 0;
      int cnt = 0;
      for (Integer score: entry.getValue()) {
        sum += score;
        cnt++;
      }
      result.put(entry.getKey(), (double)sum / cnt);
    }
    return result;
  }
}
