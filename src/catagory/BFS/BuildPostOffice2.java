package catagory.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by zhahua on 11/12/17.
 */
public class BuildPostOffice2 {
    private class Coordinate {
        public int x;
        public int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public final static int WALL = 2;

    public final static int HOUSE = 1;

    public final static int EMPTY = 0;

    private final static int[] deltaX = new int[]{-1, 0, 1, 0};

    private final static int[] deltaY = new int[]{0, -1, 0, 1};

    //并不是第一个交汇的点就是最小值
    //有可能很多人在一起，有一个特别远
    public int shortestDistance(int[][] grid) {
        // write your code here
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return -1;
        List<Coordinate> houses = getCoordinate(HOUSE, grid);
        if(houses.size() == 0) {
            return -1;
        }

        int h = grid.length;
        int w = grid[0].length;

        int[][] distanceGrid = new int[h][w];
        int[][] countGrid = new int[h][w];

        for(Coordinate house : houses) {
            int distance = 0;
            boolean[][] visitedGrid = new boolean[h][w];
            Queue<Coordinate> queue = new LinkedList<>();
            queue.offer(house);
            while(!queue.isEmpty()) {
                int size = queue.size();
                distance++;
                for(int i = 0; i < size; i++) {
                    Coordinate head = queue.poll();
                    for(int d = 0; d < deltaX.length; d++) {
                        Coordinate newCoor = new Coordinate(head.x + deltaX[d], head.y + deltaY[d]);
                        if(isPath(newCoor, grid, visitedGrid, w, h)) {
                            visitedGrid[newCoor.y][newCoor.x] = true;
                            countGrid[newCoor.y][newCoor.x]++;
                            distanceGrid[newCoor.y][newCoor.x] += distance;
                            queue.offer(newCoor);
                        }
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        boolean isFound = false;
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                if(countGrid[y][x] == houses.size()) {//有可能某些点根本没走到过
                    min = Math.min(min, distanceGrid[y][x]);
                    isFound = true;
                }
            }
        }
        if(!isFound) {
            return -1;
        }
        return min;

    }
    public boolean isPath(Coordinate coor, int[][] grid, boolean[][] visitedGrid, int w, int h) {
        if(coor.x < 0 || coor.x >= w) {
            return false;
        }
        if(coor.y < 0 || coor.y >= h) {
            return false;
        }
        return grid[coor.y][coor.x] == EMPTY && visitedGrid[coor.y][coor.x] == false;
    }
    private List<Coordinate> getCoordinate(int type, int[][] grid) {
        List<Coordinate> result = new ArrayList<>();
        for(int y = 0; y < grid.length; y++) {
            int[] row = grid[y];
            for(int x = 0; x < row.length; x++) {
                if(row[x] == type) {
                    result.add(new Coordinate(x, y));
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {

    }

}
