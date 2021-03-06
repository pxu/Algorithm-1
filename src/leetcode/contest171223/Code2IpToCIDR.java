package leetcode.contest171223;

import java.util.ArrayList;
import java.util.List;

public class Code2IpToCIDR {

  public static void main(String[] args) {
    new Code2IpToCIDR().ipToCIDR("255.0.0.0", 10);
  }
  public List<String> ipToCIDR(String ipString, int range) {
    int ip = convertStringIPtoNum(ipString);
    List<String> cidrBlocks = new ArrayList<>();
    while(range > 0) {
      int zeros = getZeros(ip);
      int thisRange = 1 << zeros;
      thisRange = Math.min(range,thisRange);
      getCidrBlocks(ip, thisRange, cidrBlocks);
      ip += thisRange;
      range -= thisRange;
    }
    return cidrBlocks;
  }

  private int getZeros(int ip) {
    int zeros = 0;
    for(int i = 0; i<32; i++) {
      if((ip & (1 << i)) == 0) {
        zeros++;
      } else break;
    }
    return zeros;
  }

  private void getCidrBlocks(int ip, int range, List<String> cidrBlocks) {
    if(range <= 0) return;
    int i = 0;
    while((1 << i+1) <= range) i++; // Get power of 2 within range
    int prefixLength = 32-i;
    int thisRange = 1 << i;
    String ipString = convertNumToIPString(ip) + "/" + prefixLength;
    cidrBlocks.add(ipString);
    getCidrBlocks(ip+thisRange,range-thisRange,cidrBlocks);
  }

  private String convertNumToIPString(int num) {
    StringBuilder sb = new StringBuilder();

    for(int i=0; i<4; i++) {
      if(i>0) sb.insert(0, '.');
      sb.insert(0,(num & 255));
      num >>= 8;
    }
    return sb.toString();
  }

  private int convertStringIPtoNum(String ipstring) {
    String[] ipArray = ipstring.split("[.]");
    int ip = 0;
    for(int i=0; i<4; i++) {
      ip += Integer.parseInt(ipArray[i]) * (1 << (8*(3-i)));
    }
    return ip;
  }
}
