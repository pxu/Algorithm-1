package leetcode.contest171223;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class code3 {
  public static void main(String[] args) {
    int re = new code3().openLock(new String[]{"0201","0101","0102","1212","2002"},"0202");
  }
  public int openLock(String[] deadends, String target) {
    Set<List<Integer>> deadendSet = getDeadendSet(deadends);
    Set<List<Integer>> visited = new HashSet<>();
    List<Integer> targetList = covert(target);
    Queue<List<Integer>> queue = new ArrayDeque<>();
    int step = 0;
    List<Integer> start = new ArrayList<Integer>();
    start.add(0);
    start.add(0);
    start.add(0);
    start.add(0);

    queue.add(start);
    visited.add(start);

    while (!queue.isEmpty()) {
      int sz = queue.size();
      step++;
      for (int i = 0; i < sz; i++) {
        List<Integer> head = queue.poll();
        List<List<Integer>> nextSteps = permute(head);
        for (List<Integer> ns : nextSteps) {
          if (targetList.equals(ns)) {
            return step;
          }
          if (!visited.contains(ns) && !deadendSet.contains(ns)) {
            queue.add(ns);
            visited.add(ns);
          }
        }
      }
    }

    return -1;

  }
  private List<Integer> covert(String str) {
    List<Integer> list = new ArrayList<>();
    for (Character ch : str.toCharArray()) {
      list.add(Integer.parseInt(ch.toString()));
    }
    return list;
  }
  private Set<List<Integer>> getDeadendSet(String[] deadends) {
    Set<List<Integer>> result = new HashSet<>();
    for (String deadend :deadends) {
      result.add(covert(deadend));
    }
    return result;
  }

  private List<List<Integer>> permute(List<Integer> crt) {
    List<List<Integer>> result = new ArrayList<>();
    for(int i = 0; i < 4; i++) {


      for (int j = -1; j <= 1; j +=2) {
        List<Integer> num = new ArrayList<>(crt);
        num.set(i, (crt.get(i) + 10 + j) % 10);
        result.add(num);
      }

    }
    return result;
  }
}
