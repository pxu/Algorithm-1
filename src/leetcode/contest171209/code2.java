package leetcode.contest171209;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class code2 {
  public static void main(String[] args) {
    int[][] graph = new int[][] {
        new int[]{0, 1, 10},
        new int[]{0, 2, 11},
        new int[]{1, 3, 10},
        new int[]{2, 3, 5},
        new int[]{5, 4, 5},

    };
    new code2().networkDelayTime(graph, 6, 0);
  }
  public class Node {
    int id;

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
      adjacentNodes.put(destination, distance);
    }
    public int getDistance() {
      return distance;
    }
    public void setDistance(int distance) {
      this.distance = distance;
    }

    public Node(int id) {
      this.id = id;
    }

    // getters and setters
  }
    public int networkDelayTime(int[][] times, int N, int K) {

      Map<Integer,Node> graph = new HashMap<>();
      buildGraph(graph, times);
      if (graph.keySet().size() < N) {
        return -1;
      }
      //validate

      Node source = graph.get(K);
      source.setDistance(0);

      Set<Node> settledNodes = new HashSet<>();
      Set<Node> unsettledNodes = new HashSet<>();

      unsettledNodes.add(source);

      while (unsettledNodes.size() != 0) {
        Node currentNode = getLowestDistanceNode(unsettledNodes);
        unsettledNodes.remove(currentNode);
        for (Map.Entry < Node, Integer> adjacencyPair:
            currentNode.adjacentNodes.entrySet()) {
          Node adjacentNode = adjacencyPair.getKey();
          Integer edgeWeight = adjacencyPair.getValue();
          if (!settledNodes.contains(adjacentNode)) {
            calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
            unsettledNodes.add(adjacentNode);
          }
        }
        settledNodes.add(currentNode);
      }

      int maxDist = -1;

      for (Map.Entry<Integer,Node> entry : graph.entrySet()) {
        if (entry.getValue().getDistance() == Integer.MAX_VALUE) {
          return -1;
        }
        maxDist = Math.max(maxDist, entry.getValue().getDistance());
      }
      return maxDist;

    }
    public void buildGraph(Map<Integer,Node> graph, int[][] times) {
      for (int i = 0; i < times.length; i++) {
        int source = times[i][0];
        int dest = times[i][1];
        graph.computeIfAbsent(source, k -> new Node(source));
        graph.computeIfAbsent(dest, k -> new Node(dest));
      }
      for (int i = 0; i < times.length; i++) {
        int source = times[i][0];
        int dest = times[i][1];
        Node destNode = graph.get(dest);
        graph.get(source).adjacentNodes.put(destNode, times[i][2]);
      }

    }
  private static Node getLowestDistanceNode(Set< Node > unsettledNodes) {
    Node lowestDistanceNode = null;
    int lowestDistance = Integer.MAX_VALUE;
    for (Node node: unsettledNodes) {
      int nodeDistance = node.distance;
      if (nodeDistance < lowestDistance) {
        lowestDistance = nodeDistance;
        lowestDistanceNode = node;
      }
    }
    return lowestDistanceNode;
  }

  private static void calculateMinimumDistance(Node evaluationNode,
      Integer edgeWeigh, Node sourceNode) {
    Integer sourceDistance = sourceNode.getDistance();
    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
      evaluationNode.setDistance(sourceDistance + edgeWeigh);
    }
  }



}
