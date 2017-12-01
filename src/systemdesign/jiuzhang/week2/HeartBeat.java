package systemdesign.jiuzhang.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhahua on 7/17/17.
 */
public class HeartBeat {

  public static void main(String[] args) {
    new HeartBeat();
  }
  public HeartBeat() {
    // initialize your data structure here
    initialize(new ArrayList() {{ this.add("10.173.0.2"); this.add("10.173.0.3");}}, 10);
    ping(1, "10.173.0.2");
    getDiedSlaves(20);
    getDiedSlaves(21);
    ping(22, "10.173.0.2");
    ping(23, "10.173.0.3");
    getDiedSlaves(24);
    getDiedSlaves(42);

  }
  Map<String,Integer> heartBeatLog = new HashMap<>();

  int k = 0;
  // @param slaves_ip_list a list of slaves'ip addresses
  // @param k an integer
  // @return void
  public void initialize(List<String> slaves_ip_list, int k) {
    // Write your code here
    for(String ip : slaves_ip_list) {
      heartBeatLog.put(ip,0);
    }
    this.k = k;
  }

  // @param timestamp current timestamp in seconds
  // @param slave_ip the ip address of the slave server
  // @return nothing
  public void ping(int timestamp, String slave_ip) {
    // Write your code here
    if(!heartBeatLog.containsKey(slave_ip)) {
      return;
    }
    heartBeatLog.put(slave_ip,timestamp);
  }

  // @param timestamp current timestamp in seconds
  // @return a list of slaves'ip addresses that died
  public List<String> getDiedSlaves(int timestamp) {
    // Write your code here
    List<String> diedSlaves = new ArrayList<String>();
    for(Map.Entry<String,Integer> entry : heartBeatLog.entrySet()) {
      if(timestamp - entry.getValue() >= 2 * k) {
        diedSlaves.add(entry.getKey());
      }
    }
    return diedSlaves;
  }
}