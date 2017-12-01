package systemdesign.jiuzhang.week5;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by zhahua on 7/21/17.
 */
class TrieNode {
  public Map<Character,TrieNode> children = new HashMap<Character,TrieNode>();
  // Initialize your data structure here.
  public TrieNode() {

  }
}
public class Trie {
  public static void main(String[] args) {
    new Trie();
  }
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
    insert("a");
    insert("b");
    insert("c");
    startsWith("a");
    boolean wt =search("b");
    wt = startsWith("lint");
    wt = startsWith("linterror");
    insert("linterror");
    wt = search("lintcode");
    wt = startsWith("linterror");
  }

  // Inserts a word into the trie.
  public void insert(String word) {
    if(word == null) {
      return;
    }
    insert(root,word,0);
  }
  private void insert(TrieNode parent, String word, int index) {
    if(index == word.length()) {
      parent.children.put('\n',new TrieNode());
      return;
    }
    char ch = word.charAt(index);
    TrieNode child = parent.children.get(ch);
    if(child == null) {
      child = new TrieNode();
      parent.children.put(ch,child);
    }

    insert(child, word, index + 1);
  }
  // Returns if the word is in the trie.
  public boolean search(String word) {
    return search(root, word, 0);
  }
  private boolean search(TrieNode parent, String word, int index) {
    if(word.length() == index) {
      if(parent.children.get('\n') != null) {
        return true;
      }else {
        return false;
      }
    }
    TrieNode child = parent.children.get(word.charAt(index));
    if(child == null) {
      return false;
    }
    return search(child,word,index + 1);
  }
  // Returns if there is any word in the trie
  // that starts with the given prefix.
  public boolean startsWith(String prefix) {
    return startsWith(root,prefix,0);
  }
  private boolean startsWith(TrieNode parent, String word, int index) {
    if(word.length() == index) {
      return true;
    }
    TrieNode child = parent.children.get(word.charAt(index));
    if(child == null) {
      return false;
    }
    return startsWith(child,word,index + 1);
  }
}
