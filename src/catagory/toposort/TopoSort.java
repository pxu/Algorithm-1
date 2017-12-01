package catagory.toposort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * Created by zhahua on 3/25/17.
 */
public class TopoSort {
  public static void main(String[] args) {
    DirectedGraphNode n0 = new DirectedGraphNode(0);
    DirectedGraphNode n1 = new DirectedGraphNode(1);
    DirectedGraphNode n2 = new DirectedGraphNode(2);
    DirectedGraphNode n3 = new DirectedGraphNode(3);
    DirectedGraphNode n4 = new DirectedGraphNode(4);
    n0.neighbors.add(n1);
    n0.neighbors.add(n2);
    n0.neighbors.add(n3);
    n0.neighbors.add(n4);

    n1.neighbors.add(n3);
    n1.neighbors.add(n4);

    n2.neighbors.add(n1);
    n2.neighbors.add(n4);

    n3.neighbors.add(n4);

    ArrayList<DirectedGraphNode> nodes = new ArrayList<DirectedGraphNode>();
    nodes.add(n0);
    nodes.add(n1);
    nodes.add(n2);
    nodes.add(n3);
    nodes.add(n4);

    new TopoSort().topSort(nodes);
  }

  public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    Map<DirectedGraphNode,Integer> inDegrees = new HashMap<>();
    for(DirectedGraphNode node : graph) {
      inDegrees.put(node, 0);
    }
    for(DirectedGraphNode node : graph) {
      for(DirectedGraphNode neibor : node.neighbors) {
        Integer inDegree = inDegrees.get(neibor);
        if(inDegree == null) {
          //invalid input
          return null;
        }
        inDegrees.put(neibor, inDegree + 1);
      }
    }
    DirectedGraphNode startNode = null;
    for(java.util.Map.Entry<DirectedGraphNode,Integer> entry : inDegrees.entrySet()) {
      if(entry.getValue().equals(0)) {
        startNode = entry.getKey();
        break;
      }
    }
    if(startNode == null) {
      return null;
    }

    Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
    queue.add(startNode);
    ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>(graph.size());
    while(queue.size() != 0) {
      DirectedGraphNode node = queue.poll();
      result.add(node);
      for(DirectedGraphNode neighbor : node.neighbors) {
        Integer inDegree = inDegrees.get(neighbor);
        inDegree -= 1;
        inDegrees.put(neighbor, inDegree);
        if(inDegree.equals(0)) {
          if(!queue.offer(neighbor)) {
            return null;
          }
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