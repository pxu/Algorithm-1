package systemdesign.jiuzhang.LBS;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhahua on 8/30/17.
 */
public class GeoHashDecode {

  public static void main(String[] args) {
    double[] result = new GeoHashDecode().decode("ezs42");
  }
  public double[] decode(String geohash) {
    BitSet resultBitSet = decodeToBits(geohash);
    Double longtitude = decodeOneDimension(resultBitSet, geohash.length() * 5, 180.0, -180.0, 0, 2);
    Double latitude = decodeOneDimension(resultBitSet, geohash.length() * 5, 90.0, -90.0, 1, 2);

    return new double[]{latitude,longtitude};
  }

  private Double decodeOneDimension(BitSet bits, int bitLen, Double maxValue, Double minValue, int startPos, int stride) {
    Double highValue = maxValue;
    Double lowValue = minValue;
    Double midValue = (highValue + lowValue) / 2;
    for(int i = startPos; i < bitLen; i += stride) {
      if(bits.get(i)) {
        lowValue = midValue;
      } else {
        highValue = midValue;
      }
      midValue = (highValue + lowValue) / 2;
    }
    return midValue;
  }
  private static final char[] BASE32_CHARS = "0123456789bcdefghjkmnpqrstuvwxyz".toCharArray();

  private static final Map<Character,BitSet> BASE32_MAP;

  static {
    Map<Character,BitSet> tmpMap = new HashMap<>();
    for(int i = 0; i < BASE32_CHARS.length; i++) {
      BitSet bits = new BitSet();
      int indexNum = i;
      for(int j = 4; j >= 0; j--) {
        bits.set(j, indexNum % 2 == 1);
        indexNum >>= 1;
      }
      tmpMap.put(BASE32_CHARS[i], bits);
    }
    BASE32_MAP = tmpMap;
  }

  private BitSet decodeToBits(String geohash) {
    BitSet result = new BitSet(geohash.length() * 5);
    for(int i = 0; i < geohash.length(); i++) {
      BitSet charBits = BASE32_MAP.get(geohash.charAt(i));
      for(int j = 0; j < 5; j++) {
        result.set(i * 5 + j, charBits.get(j));
      }
    }
    return result;
  }

}
