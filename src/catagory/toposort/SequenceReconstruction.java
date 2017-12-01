package catagory.toposort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by zhahua on 3/26/17.
 */
public class SequenceReconstruction {
  public static void main(String[] args){

  }

  private boolean SequenceRec(int[] org,int[][] seqs) {

    Map<Integer,Integer> inDegrees = new HashMap<>();
    Map<Integer,ArrayList<Integer>> adjacencyLists = new HashMap<>();
    Queue<Integer> queue = new LinkedList<>();

    for(int[] seq : seqs) {
      for(int i = 1; i < seq.length; i++) {
        int num = seq[i];
        int prevNum = seq[i-1];

        if(!adjacencyLists.containsKey(prevNum)) {
          adjacencyLists.put(prevNum,new ArrayList<Integer>());
        }
        ArrayList<Integer> adjacencyList = adjacencyLists.get(prevNum);
        adjacencyList.add(num);

        Integer inDegree = inDegrees.get(num);
        if(inDegree == null) {
          inDegree = 0;
        }
        inDegree += 1;
        inDegrees.put(num, inDegree);
      }
    }
    return false;

  }
}
