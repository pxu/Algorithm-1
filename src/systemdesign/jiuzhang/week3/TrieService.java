package systemdesign.jiuzhang.week3;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

// note:
// 往arrayList 插top k 的算法看下
/**
 * Definition of TrieNode:
 * public class TrieNode {
 *     public NavigableMap<Character, TrieNode> children;
 *     public List<Integer> top10;
 *     public TrieNode() {
 *         children = new TreeMap<Character, TrieNode>();
 *         top10 = new ArrayList<Integer>();
 *     }
 * }
 */
public class TrieService {

  public static void main(String [] args) {
    TrieService wt = new TrieService();
    wt.insert("abc",2);
    wt.insert("ab",4);
    wt.insert("ac",9);

  }
  private TrieNode root = null;

  public TrieService() {
    root = new TrieNode();
  }

  public TrieNode getRoot() {
    // Return root of trie root, and
    // lintcode will print the tree struct.
    return root;
  }

  // @param word a string
  // @param frequency an integer
  public void insert(String word, int frequency) {
    // Write your cod here
    insert(root,word,0,frequency);
  }
  // @param word a string
  // @param frequency an integer
  private void insert(TrieNode parentNode, String word, int index, int frequency) {
    // Write your cod here
    if(index == word.length()) {
      return;
    }
    char ch = word.charAt(index);

    TrieNode child = parentNode.children.get(ch);
    if(child == null) {
      child = new TrieNode();
      parentNode.children.put(ch, child);
    }
    int i;
    for(i = 0; i < child.top10.size(); i++) {
      if(child.top10.get(i) < frequency) {
        break;
      }
    }
    child.top10.add(i ,frequency);
    if(child.top10.size() > 10) {
      child.top10.remove(child.top10.size());
    }
    insert(child,word,index + 1,frequency);
  }

}
class TrieNode {
  public NavigableMap<Character, TrieNode> children;
  public List<Integer> top10;
  public TrieNode() {
    children = new TreeMap<Character, TrieNode>();
    top10 = new ArrayList<Integer>();
  }
}