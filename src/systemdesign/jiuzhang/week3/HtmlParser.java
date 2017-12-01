package systemdesign.jiuzhang.week3;

/**
 * Created by zhahua on 7/19/17.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlParser {
  public static void main(String[] args) {
    new HtmlParser().parseUrls("CTYPE html>\n"

        + "\n");
  }
  /**
   * @param content source code
   * @return a list of links
   */
  public List<String> parseUrls(String content) {
    // Write your code here
    Pattern pattern = Pattern.compile("<(?:a|link)[^>]*href\\s*=\\s*\"([^>\"#][^>\"]*)\"[^>]*>",Pattern.CASE_INSENSITIVE);
    Matcher m = pattern.matcher(content);

    List<String> result = new ArrayList<>();
    while(m.find()) {
      result.add(m.group(1));
    }
    return result;

  }
}
