package leetcode.contest171216;

/**
 * https://leetcode.com/contest/weekly-contest-63/problems/number-of-corner-rectangles/
 * https://discuss.leetcode.com/topic/114177/short-java-ac-solution-o-m-2-n-with-explanation
 */
public class NumberOfCornerRect {
    public int countCornerRectangles(int[][] grid) {
        int result = 0;
        for (int c0 = 0; c0 < grid[0].length - 1; c0++) {
            for (int c1 = c0 + 1; c1 < grid[0].length; c1++) {
                int counter = 0;
                for (int r = 0; r < grid.length; r++) {
                    if (grid[r][c0] == 1 && grid[r][c1] == 1) {
                        counter++;
                    }
                }
                result += counter * (counter - 1) / 2;
            }
        }
        return result;
    }
}
