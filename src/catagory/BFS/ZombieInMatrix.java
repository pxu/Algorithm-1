package catagory.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zhahua on 11/12/17.
 */
//http://www.lintcode.com/en/problem/zombie-in-matrix/
//https://www.jiuzhang.com/solution/zombie-in-matrix/
public class ZombieInMatrix {

    /**
     * @param grid  a 2D integer grid
     * @return an integer
     */

    private static class Coodinate {

        public int x;
        public int y;

        public Coodinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private final static int ZOMBIE = 1;

    private final static int PEOPLE = 0;

    private final static int WALL = 2;

    private final static int[] neighborMatX = new int[]{-1, 0, 1, 0};

    private final static int[] neighborMatY = new int[]{0, -1, 0, 1};

    public static int zombie(int[][] grid) {

        if(grid == null || grid.length == 0|| grid[0].length == 0) {
            return 0;
        }

        Queue<Coodinate> queue = new LinkedList<>();

        int people = 0;

        for(int y = 0; y < grid.length; y++) {
            int[] row = grid[y];
            for(int x = 0; x < row.length; x++) {
                if(row[x] == ZOMBIE) {
                    queue.offer(new Coodinate(x, y));
                }
                if(row[x] == PEOPLE) {
                    people++;
                }
            }
        }
        if(people == 0) {
            return 0;
        }
        int day = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            day++;
            for(int i = 0; i < size; i++) {
                Coodinate head = queue.poll();

                for(int m = 0; m < neighborMatX.length; m++) {
                    Coodinate newCoodinate = new Coodinate(head.x + neighborMatX[m], head.y + neighborMatY[m]);
                    if(isPeople(newCoodinate, grid)) {
                        queue.offer(newCoodinate);
                        grid[newCoodinate.y][newCoodinate.x] = ZOMBIE;
                        people--;
                        if(people == 0) {
                            return day;
                        }
                    }
                }
            }
        }
        return -1;

    }

    public static boolean isPeople(Coodinate coodinate, int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        if(coodinate.x < 0 || coodinate.x >= width) {
            return false;
        }
        if(coodinate.y < 0 || coodinate.y >= height) {
            return false;
        }
        return grid[coodinate.y][coodinate.x] == PEOPLE;
    }

}
