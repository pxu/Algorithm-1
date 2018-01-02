package leetcode.contest171216;

import java.util.Set;
import java.util.TreeSet;

public class code3 {
  public  static  void main(String[] args ){
    int[][] aa= new int[][]{
        new int[]{1,1,1},
        new int[]{1,1,1},
        new int[]{1,1,1},
        new int[]{1,1,1}
    };
    new code3().countCornerRectangles(aa);
  }
  public int countCornerRectangles(int[][] grid) {
    if(grid == null || grid.length < 2 || grid[0].length < 2) {
      return 0;
    }
    TreeSet<Integer>[] rows = getRow(grid);
    TreeSet<Integer>[] columns = getColumn(grid);

    int h = grid.length;
    int counter = 0;
    for (int r = 0; r < h - 1; r++) {
      for (int c : rows[r]) {
        Integer cr = rows[r].higher(c);
        while (cr != null) {
          Integer rb = columns[c].higher(r);
          while (rb != null) {
            if (columns[cr].contains(rb)) {
              counter++;
            }
            rb = columns[c].higher(rb);
          }
          cr = rows[r].higher(cr);
        }
      }
    }
    return counter;

  }
  public TreeSet<Integer>[] getRow(int[][] grid) {
    TreeSet<Integer>[] result = new TreeSet[grid.length];
    for (int r = 0; r < grid.length; r++) {
      TreeSet<Integer> tmp = new TreeSet<Integer>();
      for (int c = 0; c < grid[r].length; c++) {
        if(grid[r][c] == 1) {
          tmp.add(c);
        }
      }
      result[r] = tmp;
    }
    return result;
  }
  public TreeSet<Integer>[] getColumn(int[][] grid) {
    TreeSet<Integer>[] result = new TreeSet[grid[0].length];
    for (int c = 0; c < grid[0].length; c++) {
      TreeSet<Integer> tmp = new TreeSet<Integer>();
      for (int r = 0; r < grid.length; r++) {
        if(grid[r][c] == 1) {
          tmp.add(r);
        }
      }
      result[c] = tmp;
    }
    return result;
  }
}
