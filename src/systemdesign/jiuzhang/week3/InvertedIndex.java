package systemdesign.jiuzhang.week3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhahua on 7/20/17.
 */
public class InvertedIndex {
  /**
   * Definition of Document:
   * class Document {
   *     public int id;
   *     public String content;
   * }
   */

  /**
   * @param docs a list of documents
   * @return an inverted index
   */
  public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
    // Write your code here
    Map<String,List<Integer>> mapping = new HashMap<>();
    for(Document doc : docs) {
      if(doc.content == null) {
        continue;
      }
      String[] words = doc.content.split(" ");
      for(String word : words) {
        if(word.length() == 0) {
          continue;
        }
        List<Integer> refs = mapping.get(word);
        if(refs == null) {
          refs = new ArrayList<Integer>();
          mapping.put(word,refs);
        }
        if(refs.size() > 0 && !refs.get(refs.size()-1).equals(doc.id)) {
          refs.add(doc.id);
        }
      }
    }

    return mapping;
  }
}

class Document {
  public int id;
  public String content;
}