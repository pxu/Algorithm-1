package systemdesign.jiuzhang.week5;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhahua on 10/18/17.
 */
public class InvertedIndex {
    static class Document {
        public int id;
        public String content;
    }

    public static void main(String[] args) {

    }

    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        if (docs == null || docs.size() == 0) {
            return new HashMap<>();
        }
        Map<String, List<Integer>> result = new HashMap<>();
        for (Document doc : docs) {
            if (doc.content == null) {
                continue;
            }
            String[] words = doc.content.split("\\s");
            Set<String> wordSet = new HashSet<>();
            for (String word : words) {
                if (word.equals("")) {
                    continue;
                }
                wordSet.add(word);
            }
            for (String word : wordSet) {
                result.computeIfAbsent(word, (k) -> new ArrayList<>()).add(doc.id);
            }

        }
        return result;
    }
}