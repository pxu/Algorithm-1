package systemdesign.jiuzhang.week2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhahua on 7/17/17.
 */
public class TinyUrl {

  public static void main(String[] args) {
    TinyUrl tu = new TinyUrl();
    String shortUrl = tu.longToShort("http://www.lintcode.com/faq/?id=10");
    String longUrl = tu.shortToLong(shortUrl);
    tu.shortToLong("http://tiny.url/");
  }

  public static final String BASE62_CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  public static final Map<Character,Integer> BASE62_CHARMAP;

  static {
    Map<Character,Integer> tmpMap = new HashMap<>();
    for(int i = 0; i < BASE62_CHARSET.length(); i++) {
      tmpMap.put(BASE62_CHARSET.charAt(i),i);
    }
    BASE62_CHARMAP = Collections.unmodifiableMap(tmpMap);
  }

  static volatile int sequentialNumber = 0;

  Map<String,String> shortKeyToLongUrl = new HashMap<>();

  Map<String,String> longUrlToShortKey = new HashMap<>();

  String shortKeyToShortUrl(String shortKey) {
    return "http://tiny.url/" + shortKey;
  }


  String shortUrlToShortKey(String shortUrl) {
    int pos = shortUrl.lastIndexOf("/") + 1;
    if(pos == -1 || pos == shortUrl.length()) {
      return null;
    }
    return shortUrl.substring(pos);
  }

  String toBase62(int intValue,int length) {
    char[] result = new char[length];
    for(int i = length -1; i >= 0; i--) {
      int num = intValue % BASE62_CHARSET.length();
      result[i] = BASE62_CHARSET.charAt(num);
      intValue = intValue / BASE62_CHARSET.length();
    }
    return new String(result);
  }

  int parseBase62(String strValue) {
    int intValue = 0;
    for(int i = 0; i < strValue.length(); i++) {
      Integer currentValue = BASE62_CHARMAP.get(strValue);
      if(currentValue == null) {
        return intValue;
      }
      intValue = intValue * BASE62_CHARSET.length() + currentValue;
    }
    return intValue;
  }

  /**
   * @param url a long url
   * @return a short url starts with http://tiny.url/
   */
  public String longToShort(String url) {
    // Write your code here
    String shortKey = longUrlToShortKey.get(url);
    if(shortKey != null) {
      return shortKeyToShortUrl(shortKey);
    }
    shortKey = toBase62(sequentialNumber, 6);
    sequentialNumber++;
    shortKeyToLongUrl.put(shortKey, url);
    longUrlToShortKey.put(url,shortKey);

    return shortKeyToShortUrl(shortKey);
  }

  /**
   * @param url a short url starts with http://tiny.url/
   * @return a long url
   */
  public String shortToLong(String url) {
    // Write your code here
    String shortKey = shortUrlToShortKey(url);
    if(shortKey == null) {
      return "error";
    }
    String longUrl = shortKeyToLongUrl.get(shortKey);
    if(longUrl == null) {
      return "error";
    }
    return longUrl;
  }
}