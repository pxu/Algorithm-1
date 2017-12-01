package systemdesign.jiuzhang.week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Random;
/**
 * Created by zhahua on 7/17/17.
 */
public class ConsistentHasing2 {

  public static void main(String[] args) {
    ConsistentHasing2 ch= create(10,3);
    ch.getMachineIdByHashCode(1);
    ch.addMachine(1);
    ch.getMachineIdByHashCode(2);
    ch.addMachine(2);
    ch.addMachine(3);
    ch.getMachineIdByHashCode(2);
    ch.addMachine(3);
  }
  int n = 0;

  int k = 0;

  TreeMap<Integer,Integer> machineMap = new TreeMap<>();

  Random random = new Random();

  public ConsistentHasing2(int n, int k) {
    this.n = n;
    this.k = k;
  }
  // @param n a positive integer
  // @param k a positive integer
  // @return a Solution object
  public static ConsistentHasing2 create(int n, int k) {
    // Write your code here
    return new ConsistentHasing2(n, k);
  }

  private int getNonRepeatRandom() {
    int number = 0;
    if(machineMap.keySet().size() == n) {
      return -1;
    }
    do {
      number = random.nextInt(n);
    } while(machineMap.get(number) != null);

    return number;
  }
  // @param machine_id an integer
  // @return a list of shard ids
  public List<Integer> addMachine(int machine_id) {
    // Write your code here
    if(machineMap.keySet().size() + k > n) {
      return Collections.EMPTY_LIST;
    }
    List<Integer> result = new ArrayList<>();
    for(int i = 0; i < k; i++) {
      int microShardingPoint = getNonRepeatRandom();
      machineMap.put(microShardingPoint, machine_id);
      result.add(microShardingPoint);
    }
    return result;
  }

  // @param hashcode an integer
  // @return a machine id
  public int getMachineIdByHashCode(int hashcode) {
    // Write your code here
    if(machineMap.keySet().size() == 0) {
      return -1;
    }
    int hashCodeMod = hashcode % n;
    Map.Entry<Integer,Integer> machineIdEntry = machineMap.ceilingEntry(hashCodeMod);
    if(machineIdEntry == null) {
      machineIdEntry = machineMap.firstEntry();
    }
    return machineIdEntry.getValue();
  }
}
