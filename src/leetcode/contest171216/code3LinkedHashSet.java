package leetcode.contest171216;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

 class code3HashSet {
  public  static  void main(String[] args ){
    int[][] aa= new int[][]{
        new int[]{1,1,1},
        new int[]{1,1,1},
        new int[]{1,1,1},
        new int[]{1,1,1}
    };
    new code3HashSet().countCornerRectangles(aa);
  }
  public int countCornerRectangles(int[][] grid) {
    if(grid == null || grid.length < 2 || grid[0].length < 2) {
      return 0;
    }
    int h = grid.length;
    int w = grid[0].length;

    int[][] rows = new int[h][];
    getRow(grid, rows);

    List<Set<Integer>> columnsSet = new ArrayList<>();
    int[][] columns = new int[w][];
    getColumn(grid, columns, columnsSet);

    int counter = 0;
    for (int r0 = 0; r0 < h - 1; r0++) {
      for (int ci0 = 0; ci0 < columns[r0].length; ci0++) {
        int c0 = columns[r0][ci0];
        for (int ci1 = ci0 + 1; ci1 < columns[r0].length; ci1++) {
          int c1 = columns[r0][ci1];
          //for (int ri)
        }
      }

    }
    return counter;

  }
  public void getRow(int[][] grid, int[][] sparse) {
    for (int r = 0; r < grid.length; r++) {
      List<Integer> list = new ArrayList<Integer>();
      for (int c = 0; c < grid[r].length; c++) {
        if(grid[r][c] == 1) {
          list.add(c);
        }
      }
      sparse[r] = list.stream().mapToInt(Integer::intValue).toArray();
    }
  }
  public void getColumn(int[][] grid, int[][] sparse, List<Set<Integer>> sparseSet) {
    for (int c = 0; c < grid[0].length; c++) {
      Set<Integer> set = new HashSet<Integer>();
      List<Integer> list = new ArrayList<>();
      for (int r = 0; r < grid.length; r++) {
        if(grid[r][c] == 1) {
          list.add(r);
          set.add(r);
        }
      }
      sparse[c] = list.stream().mapToInt(Integer::intValue).toArray() ;
      sparseSet.add(set);
    }
  }
}
