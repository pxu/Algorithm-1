package catagory.unionfind;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhahua on 11/28/17.
 */
public class SentenceSimilarity2 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        for (int i = 0; i < pairs.length; i++) {
            union(pairs[i]);
        }
        for (int i = 0; i < words1.length; i++) {
            if(!find(words1[i], words2[i])) {
                return false;
            }
        }
        return true;
    }
    Map<String, String> mapUnion = new HashMap<String, String>();

    public String root(String str) {
        while (!str.equals(mapUnion.get(str))) {
            str = mapUnion.get(str);
        }
        return str;
    }
    public void union(String[] pair) {
        mapUnion.computeIfAbsent(pair[0], k -> k);
        mapUnion.computeIfAbsent(pair[1], k -> k);

        mapUnion.put(root(pair[0]), root(pair[1])); //***
    }
    public boolean find(String w1, String w2) {
        if(w1.equals(w2)) { //****
            return true;
        }
        if(mapUnion.get(w1) == null || mapUnion.get(w2) == null) {
            return false;
        }
        return root(w1).equals(root(w2));
    }
}
