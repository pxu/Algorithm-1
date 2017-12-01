package catagory.Graph;

import java.util.*;

/**
 * Created by zhahua on 11/14/17.
 * 最小生成树的最长path
 */
public class LongestPathInMST {

    public static void main(String[] args) {
        Map<Integer,Set<Integer>> adjTable = new HashMap<>();
        adjTable.put(1,new HashSet(){{this.add(6);}});
        adjTable.put(2,new HashSet(){{this.add(7);}});
        adjTable.put(3,new HashSet(){{this.add(8);}});
        adjTable.put(4,new HashSet(){{this.add(5);}});
        adjTable.put(5,new HashSet(){{Arrays.asList(4,6);}});
        adjTable.put(6,new HashSet(){{Arrays.asList(4,6);}});

    }

 //   public void longestPathInMST
//
//    public int longestPathInMST(Map<Integer,Set<Integer>> adjTable) {
//        Map<Integer, Map<Integer,Integer>> inDegree = new HashMap<>();
//
//        List<Integer> leafs = new ArrayList<>();
//        Set<Integer> notLeaf = new HashSet<>();
//        for(Map.Entry<Integer,Set<Integer>> entry : adjTable.entrySet()) {
//            for(Integer other : entry.getValue()) {
//                notLeaf.add(other);
//            }
//        }
//        for(Map.Entry<Integer,Set<Integer>> entry : adjTable.entrySet()) {
//            if(!notLeaf.contains(entry.getKey())) {
//                leafs.add(entry.getKey());
//            }
//        }
//        int lastCurrent = -1;
//        for(Integer leaf : leafs) {
//            Integer current = leaf;
//            do {
//                current = findParentNodeAndFill(adjTable, inDegree, current);
//                if(current != null) {
//                    lastCurrent = current;
//                }
//            } while(current != null);
//        }
//
//        if(!inDegree.containsKey(lastCurrent)) {
//            return 0;
//        }
//        int[] top2 = new int[2];
//
//        for(Map.Entry<Integer,Integer> neighbor : inDegree.get(lastCurrent).entrySet() ) {
//            int i = top2.length - 1;
//            while(neighbor.getValue() > top2[i]){
//                if(i != top2.length - 1) {
//                    top2[i + 1] = top2[i];
//                }
//                top2[i] = neighbor.getValue();
//            }
//        }
//        return top2[0] + top2[1];
//    }
//    public Integer findParentNodeAndFill(Map<Integer,Set<Integer>> adjTable, Map<Integer, Map<Integer,Integer>> inDegree, Integer node) {
//        int inDegreeCount = 0;
//        if(inDegree.containsKey(node)) {
//            inDegreeCount = inDegree.get(node).size();
//        }
//        int connectedCount = adjTable.get(node).size();
//        if(connectedCount == 0) {
//            return null;
//        }
//        if(connectedCount - inDegreeCount == 1) {
//            int maxInDegree = 0;
//            int parent = -1;
//            for(Integer neighbor : adjTable.get(node)) {
//                if(!inDegree.get(node).containsKey(neighbor)) {
//                    parent = neighbor;
//                } else {
//                    maxInDegree = Math.max(maxInDegree, inDegree.get(node).get(neighbor));
//                }
//            }
//            inDegree.computeIfAbsent(parent, (k) -> new HashMap<>()).put(node, maxInDegree + 1);
//            return parent;
//        }
//        return null;
//
//    }

}
