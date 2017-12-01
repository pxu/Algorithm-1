package catagory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhahua on 3/23/17.
 */
public class deepCopy {

   class UndirectedGraphNode {
       int label;
       ArrayList<UndirectedGraphNode> neighbors;
       UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
   };

  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null)
      return null;
    // write your code here
    return cloneGraphHelper(node
        ,new HashMap<UndirectedGraphNode,UndirectedGraphNode>()
    );
  }

  public UndirectedGraphNode cloneGraphHelper(UndirectedGraphNode src,Map<UndirectedGraphNode,UndirectedGraphNode> srcTargetMap) {

    UndirectedGraphNode cachedCopy = srcTargetMap.get(src);
    if(cachedCopy != null)
      return cachedCopy;

    UndirectedGraphNode copy = new UndirectedGraphNode(src.label);
    srcTargetMap.put(src,copy);
    for(UndirectedGraphNode connectedNode : src.neighbors) {
      copy.neighbors.add(cloneGraphHelper(connectedNode,srcTargetMap));
    }

    return copy;
  }
}
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
//public class Solution {
//  /**
//   * @param node: A undirected graph node
//   * @return: A undirected graph node
//   */
//  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
//    // write your code here
//  }
//}