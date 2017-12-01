package catagory.BFS;

import java.util.*;

/**
 * Created by zhahua on 11/11/17.
 */
public class GraphValidTree {
    public static void main(String[] args) {

    }
    public boolean validTree(int n, int[][] edges) {
        if(edges == null || n - 1 != edges.length) {
            return false;
        }
        Map<Integer,Set<Integer>> graph = initializeGraph(n, edges);

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> accessable = new HashSet<>();

        queue.offer(0);
        accessable.add(0);  //**
        while(!queue.isEmpty()) {
            int node = queue.poll();
            Set<Integer> neighbors = graph.get(node);
            for(Integer neighbor : neighbors) {
                if(accessable.contains(neighbor)) {
                    continue;
                }
                accessable.add(neighbor);
                queue.offer(neighbor);
            }
        }
        return n == accessable.size();
    }

    public Map<Integer,Set<Integer>> initializeGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for(int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }

        for(int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        return graph;
    }
}
