package catagory.BFS;

import java.util.*;

/**
 * Created by zhahua on 11/11/17.
 *  http://www.lintcode.com/en/problem/topological-sorting/
 *  https://www.jiuzhang.com/solution/topological-sorting/
 */
public class TopoSorting {

    public static void main(String[] args) {

    }
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();

        if(graph == null) {
            return result;
        }
        Map<DirectedGraphNode,Integer> inDegree = new HashMap<>();

        for(DirectedGraphNode node : graph) {
            for(DirectedGraphNode neighbor : node.neighbors) {
                inDegree.computeIfAbsent(neighbor, (key) -> 0);
                inDegree.compute(neighbor, (key, deg) -> deg + 1);
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for(DirectedGraphNode node : graph) {
            if(!inDegree.containsKey(node)) { //*
                queue.offer(node);

            }
        }

        while(!queue.isEmpty()) {
            DirectedGraphNode head = queue.poll();
            result.add(head);
            for(DirectedGraphNode neighbor : head.neighbors) {
                int changedDegree = inDegree.compute(neighbor, (key, degree) -> degree - 1);
                if(changedDegree == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }
}
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
};