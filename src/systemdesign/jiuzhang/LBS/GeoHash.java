package systemdesign.jiuzhang.LBS;

import java.util.BitSet;

public class GeoHash {
  public static void main(String[] args) {
    new GeoHash().encode(42.6, -5.6, 5);
  }
  /**
   * @param latitude, longitude a location coordinate pair
   * @param precision an integer between 1 to 12
   * @return a base32 string
   */
  public String encode(double latitude, double longitude, int precision) {
    // Write your code here
    int bitPrecision = precision * 5;
    int bitHalfPrecision = bitPrecision / 2 + 1;
    BitSet longResult = encodeOneDimention(longitude, 180, -180, bitHalfPrecision);
    BitSet latResult = encodeOneDimention(latitude, 90, -90, bitHalfPrecision);
    BitSet result = new BitSet(bitPrecision);
    for(int i = 0; i < bitHalfPrecision; i++ ) {
      result.set(i * 2, longResult.get(i));
      result.set(i * 2 + 1, latResult.get(i));
    }
    return base32Encode(result, bitPrecision);
  }
  private char[] base32Chars = "0123456789bcdefghjkmnpqrstuvwxyz".toCharArray();
  private String base32Encode(BitSet bits, int bitPrecision) {
    StringBuilder sb = new StringBuilder();
    int currentCh = 0;
    for(int i = 0; i < bitPrecision; i++) {
      currentCh <<= 1;
      if (bits.get(i)) {
        currentCh += 1;
      }
      if((i + 1) % 5 == 0) {
        sb.append(base32Chars[currentCh]);
        currentCh = 0;
      }
    }
    return sb.toString();
  }
  private BitSet encodeOneDimention(double value, double maxValue, double minValue, int bitPrecision) {
    double highValue = maxValue;
    double lowValue = minValue;
    BitSet result = new BitSet(bitPrecision);
    for(int i = 0; i < bitPrecision; i++) {
      double midValue = (highValue + lowValue) / 2;
      if(value >= midValue) {
        result.set(i,true);
        lowValue = midValue;
      } else {
        result.set(i,false);
        highValue = midValue;
      }
    }
    return result;
  }

}