package JavaFund;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhahua on 3/10/17.
 */
public class GCCMSTest {
  //-XX:+UseConcMarkSweepGC
  //-XX:+UseSerialGC
  //java -Xmx20m -Xms20m -Xmn1m -XX:+UseConcMarkSweepGC -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -cp ~/Documents/workspaceAlgorithm/Algorithm/bin/ JavaFund.GCCMSTest  |tee /tmp/gccms
  public static void main(String[] args){

    ArrayList<LinkedList<byte[]>> gen = new ArrayList<LinkedList<byte[]>>();
    gen.add(new LinkedList<byte[]>());
    gen.add(new LinkedList<byte[]>());
    gen.add(new LinkedList<byte[]>());
    ArrayList<Integer> genMax = new ArrayList<Integer>();
    genMax.add(1000);
    genMax.add(10000);
    genMax.add(-1);
    ArrayList<Integer> genFreq = new ArrayList<Integer>();
    genFreq.add(1);
    genFreq.add(10);
    genFreq.add(10);
    int seq = 0;
    while (true) {
      for (int i = 0; i < gen.size(); i++) {
        LinkedList<byte[]> list = gen.get(i);
        if (seq % genFreq.get(i) == 0) {
          list.add(new byte[500]);
        }
        if(genMax.get(i) != -1 && list.size() == genMax.get(i)){
          list.pollFirst();
        }
      }
      seq++;
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
