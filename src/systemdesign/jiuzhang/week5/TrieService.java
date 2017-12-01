package systemdesign.jiuzhang.week5;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by zhahua on 10/18/17.
 */
public class TrieService {
     static class TrieNode {
         public NavigableMap<Character, TrieNode> children;
         public List<Integer> top10;
         public TrieNode() {
             children = new TreeMap<Character, TrieNode>();
             top10 = new ArrayList<Integer>();
     }

     public static void main(String[] args) {

     }
     TrieNode root = new TrieNode();

     private static final int LIMIT = 10;

     public TrieNode getRoot() {
         // Return root of trie root, and
         // lintcode will print the tree struct.
         return root;

     }
//     public void insert(String word, int frequency) {
//        TrieNode parent = root;
//
//        char[] chars = word.toCharArray();
//
//        for(int i = 0; i < chars.length; i++) {
//            TrieNode current = parent.children.computeIfAbsent(chars[i], k -> new TrieNode());
//            List<Integer> top10 = current.top10;
//            int t = top10.size() - 1;
//            while(t >= 0 && top10.get(t) < frequency) {
//                t--;
//            }
//            top10.add(t + 1);
//            if(top10.size())
//            parent = current;
//        }
//     }
 }
}
