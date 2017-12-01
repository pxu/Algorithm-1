package systemdesign.jiuzhang.week2;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhahua on 7/16/17.
 */
public class TinyUrl2 {
  public static void main(String[] args) {
    new TinyUrl2();
  }
  ConcurrentMap<String,String> customUrlMap = new ConcurrentHashMap<>();

  final int SHARDING_NUMBER = 100;

  final int SHARDING_RESERVED_LENGTH = 2;

  final int SHORTURL_LENGTH = 6;

  final String BASE62_CHARS_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  SimpleDB shardingDB[] = null;

  Random random = new Random();

  Pattern shortUrlParser = Pattern.compile(".*\\/(.*)");

  public TinyUrl2() {
    shardingDB = new SimpleDB[SHARDING_NUMBER];
    for(int i =0; i < SHARDING_NUMBER; i++) {
      shardingDB[i] = new SimpleDB();
    }

//
//    createCustom("http://www.lintcode.com/", "lccode");
////        >> http://tiny.url/lccode
//    createCustom("http://www.lintcode.com/", "lc");
////        >> error
//    String link1 = longToShort("http://www.lintcode.com/problem/");
////        >> http://tiny.url/1Ab38c   // this is just an example, you can have you own 6 characters.
//    shortToLong("http://tiny.url/lccode");
////        >> http://www.lintcode.com/
//    shortToLong(link1);
////        >> http://www.lintcode.com/problem/
//    shortToLong(link1);
////        >> null
//    createCustom("http://www.lintcode.com/", "lccode");
//    longToShort("http://www.lintcode.com/problem/");
//    shortToLong("http://tiny.url/lccode");
//    createCustom("http://www.lintcode.com/", "lc");
//    createCustom("http://www.lintcode.com/en/ladder/", "lccode");
  }
  private int getLongUrlShardingPartition(String key) {
    return key.hashCode() % SHARDING_NUMBER;
  }
  private int getShortUrlShardingPartition(String key) {
    if(key.length() < SHARDING_RESERVED_LENGTH) {
      return 0;
    }
    String shardingPrefix = key.substring(0,SHARDING_RESERVED_LENGTH);
    return parseBase62String(shardingPrefix) % SHARDING_RESERVED_LENGTH;
  }
  private String getBase62String(int number, int length) {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < length; i++) {
      int charInx = number % BASE62_CHARS_STRING.length();
      sb.append(BASE62_CHARS_STRING.charAt(charInx));
      number = number / BASE62_CHARS_STRING.length();
    }
    return sb.toString();
  }
  private int parseBase62String(String str) {
    int number = 0;
    for(int i = str.length() - 1; i >= 0; i--) {
      char ch= str.charAt(i);
      int idx = BASE62_CHARS_STRING.indexOf(ch);
      number = number * BASE62_CHARS_STRING.length() + idx;
    }
    return number;
  }

  /**
   * @param long_url a long url
   * @param a short key
   * @return a short url starts with http://tiny.url/
   */
  public String createCustom(String long_url, String short_key) {
    // Write your code here
    String customLongUrl = customUrlMap.get(short_key);
    if(customLongUrl != null) {
      if (!customLongUrl.equals(long_url)) {
        return "error";
      } else {
        return "http://tiny.url/" + short_key;
      }
    }

    int shardingIdx = getShortUrlShardingPartition(short_key);

    if(shardingDB[shardingIdx].getByShortUrl(short_key) != null) {
      return "error";
    }
    customUrlMap.put(short_key,long_url);
    return "http://tiny.url/" + short_key;
  }

  /**
   * @param long_url a long url
   * @return a short url starts with http://tiny.url/
   */
  public String longToShort(String long_url) {
    // Write your code here
    int shardingIdx = getLongUrlShardingPartition(long_url);
    String shortUrl = shardingDB[shardingIdx].getByLongUrl(long_url);
    if(shortUrl != null) {
      return shortUrl;
    }
    shortUrl = getBase62String(shardingIdx, SHARDING_RESERVED_LENGTH)
        + getBase62String(Math.abs(random.nextInt()),SHORTURL_LENGTH - SHARDING_RESERVED_LENGTH);
    shardingDB[shardingIdx].addUrl(shortUrl,long_url);
    return "http://tiny.url/" + shortUrl;
  }

  /**
   * @param short_url a short url starts with http://tiny.url/
   * @return a long url
   */
  public String shortToLong(String short_url) {
    // Write your code here
    Matcher match = shortUrlParser.matcher(short_url);
    if(!match.find()) {
      return "error";
    }

    String shortUrl = match.group(1);
    String customLongUrl = customUrlMap.get(shortUrl);
    if(customLongUrl != null) {
      return customLongUrl;
    }
    int shardingIdx = getShortUrlShardingPartition(shortUrl);
    return shardingDB[shardingIdx].getByShortUrl(shortUrl);
  }

  static class SimpleDB {
    ConcurrentMap<String,String> longToShort = new ConcurrentHashMap<>();
    ConcurrentMap<String,String> shortToLong = new ConcurrentHashMap<>();

    public String getByLongUrl(String longUrl) {
      return longToShort.get(longUrl);
    }

    public String getByShortUrl(String shortUrl) {
      return shortToLong.get(shortUrl);
    }

    public void addUrl(String shortUrl,String longUrl) {
      longToShort.put(longUrl,shortUrl);
      shortToLong.put(shortUrl,longUrl);
    }

  }
}
