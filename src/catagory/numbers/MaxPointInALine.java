package catagory.numbers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhahua on 11/4/17.
 */
public class MaxPointInALine {
    public static void  main(String[] args) {
        int res = maxPointInALine(new Point[]{
                new Point(){{this.x = 10; this.y = 10;}},
                new Point(){{this.x = 10; this.y = 10;}},
                new Point(){{this.x = 20; this.y = 20;}},
                new Point(){{this.x = 30; this.y = 30;}},
                new Point(){{this.x = 40; this.y = 40;}},
                new Point(){{this.x = 50; this.y = 50;}},
                new Point(){{this.x = 15; this.y = 30;}},
        });

    }
//    public static int maxPointInALine(Point[] points) {
//        if(points.length == 0)
//            return 0;
//        if(points.length == 1)
//            return 1;
//
//        int res = 0;
//        for(int i = 0; i < points.length; i++) {
//            Map<Map<Integer,Integer>,Integer> map = new HashMap<>();
//            int duplicates = 1;
//            for(int j = i + 1; j < points.length; j++) {
//                Map<Integer,Integer> slope = new HashMap<Integer,Integer>();
//                if(points[i].x == points[j].x && points[i].y == points[j].y) {
//                    duplicates++;
//                    continue;
//                }
//                if(points[i].x == points[j].x) {
//                    slope.put(Integer.MAX_VALUE, Integer.MAX_VALUE);
//                }else {
//                    int deltaX = points[i].x - points[j].x;
//                    int deltaY = points[i].y - points[j].y;
//
//                    int gcdNum = gcd(deltaX, deltaY);
//
//                    slope.put(deltaX / gcdNum, deltaY / gcdNum);
//
//                }
////
////                map.computeIfAbsent(slope,(value) -> 1);
////                map.compute(slope, (key, value)-> value + 1);
//                map.put(slope, map.getOrDefault(slope, 1) + 1);
//            }
//            res = Math.max(res, duplicates);
//            for(Map.Entry<Map<Integer,Integer>, Integer> entry : map.entrySet()) {
//                res = Math.max(res, entry.getValue() + duplicates);
//            }
//
//        }
//
//        return res;
//    }
//    private static int gcd(int a, int b) {
//        return b == 0 ? a : gcd(b, a % b);
//    }
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int maxPointInALine(Point[] points) {
        if(points == null || points.length == 0) {
            return 0;
        }
        if(points.length == 1) {
            return 1;
        }
        int res = 0;
        for(int i = 0; i < points.length; i++) {
            Map<Slope, Integer> slopeMap = new HashMap<>();
            int replicated = 0;
            for(int j = i + 1; j < points.length; j++) {
                int deltaX = points[i].x - points[j].x;
                int deltaY = points[i].y - points[j].y;
                if(deltaX == 0 && deltaY == 0) {
                    replicated ++;
                    continue;
                }
                Slope slope;
                if(deltaX == 0) {
                    slope = Slope.INFINATE;
                }else {
                    slope = new Slope(deltaX, deltaY);
                }
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 1) + 1);
            }
            res = Math.max(res, replicated + 1);
            for(Map.Entry<Slope, Integer> entry : slopeMap.entrySet()) {
                res = Math.max(res, entry.getValue() + replicated);
            }
        }
        return res;
    }
    static class Slope {

        int deltaX;
        int deltaY;
        public Slope(int deltaX, int deltaY) {
            int gcdNum = gcd(deltaX, deltaY);
            this.deltaX = deltaX / gcdNum;
            this.deltaY = deltaY / gcdNum;
        }
        private Slope() {}

        public static final Slope INFINATE = new Slope() {{
            this.deltaX = Integer.MAX_VALUE;
            this.deltaY = Integer.MAX_VALUE;
        }};

        private int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
        @Override
        public boolean equals(Object other) {
            if(other != null && other instanceof Slope) {
                return ((Slope) other).deltaX == deltaX
                        && ((Slope) other).deltaY == deltaY;
            }
            return false;
        }
        @Override
        public int hashCode(){
            return deltaX + deltaY;
        }

    }

}


class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }

    Point(int a, int b) { x = a; y = b; }
}
