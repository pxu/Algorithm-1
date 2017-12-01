package systemdesign.jiuzhang.week5;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by zhahua on 7/25/17.
 */
public class TrieSerialization {

  public static void main(String[] args) {
    TrieNode root = new TrieNode() {{
      this.children.put('a',new TrieNode() {{
        this.children.put('b',new TrieNode() {{
          this.children.put('e',new TrieNode());
        }});
        this.children.put('c',new TrieNode());
        this.children.put('d',new TrieNode() {{
          this.children.put('f',new TrieNode());
        }});
      }});

    }};
    String wt = new TrieSerialization().serialize(root);
    wt = new TrieSerialization().serialize(new TrieSerialization().deserialize(wt));
  }
  public static final char CHILDREN_LEFTSTART = '<';

  public static final char CHILDREN_RIGHTSTART = '>';

  /**
   * This method will be invoked first, you should design your own algorithm
   * to serialize a trie which denote by a root node to a string which
   * can be easily deserialized by your own "deserialize" method later.
   */
  public String serialize(TrieNode root) {
    // Write your code here
    StringBuilder sb = new StringBuilder();
    serialize(root,null,sb);
    return sb.toString();
  }
  private void serialize(TrieNode current, Character currentChar, StringBuilder outputBuilder) {
    if(currentChar != null) {
      outputBuilder.append(currentChar);
    }
    outputBuilder.append(CHILDREN_LEFTSTART);
    for(Map.Entry<Character,TrieNode> ch : current.children.entrySet()) {
      serialize(ch.getValue(),ch.getKey(), outputBuilder);
    }
    outputBuilder.append(CHILDREN_RIGHTSTART);
  }
  public TrieNode deserialize(String data) {
    TrieNode root = new TrieNode();
    Character nodeChar = null;
    Stack<TrieNode> nodeStack = new Stack<TrieNode>();
    for(int i = 0; i < data.length(); i++) {
      char currentChar = data.charAt(i);

      switch(currentChar) {
        case CHILDREN_LEFTSTART:
          if(i == 0) {
            nodeStack.push(root);
          }else {
            TrieNode newNode = new TrieNode();
            nodeStack.peek().children.put(nodeChar, newNode);
            nodeStack.push(newNode);
          }
          break;
        case CHILDREN_RIGHTSTART:
          nodeStack.pop();
          break;
        default:
          nodeChar = currentChar;
      }
    }
    return root;
  }
  /**
   * This method will be invoked second, the argument data is what exactly
   * you serialized at method "serialize", that means the data is not given by
   * system, it's given by your own serialize method. So the format of data is
   * designed by yourself, and deserialize it here as you serialize it in
   * "serialize" method.
   */
//  public TrieNode deserialize(String data) {
//    // Write your code here
//    Stack<TrieNode> childStack = new Stack<TrieNode>();
//    Stack<TrieNode> paramStack = new Stack<TrieNode>();
//    Map<TrieNode,Character> charMap = new HashMap<>();
//    TrieNode root = null;
//    Character nodeName = null;
//    for(int i = 0; i < data.length(); i++) {
//      char ch = data.charAt(i);
//
//      switch(ch) {
//        case CHILDREN_RIGHTSTART:
//          TrieNode topNode = childStack.pop();
//          List<TrieNode> childs = new ArrayList<>();
//          while(!paramStack.empty() && paramStack.peek() != topNode) {
//            childs.add(paramStack.pop());
//          }
//          TrieNode parent = paramStack.peek();
//          for(TrieNode child : childs) {
//            parent.children.put(charMap.get(child),child);
//          }
//          break;
//        case CHILDREN_LEFTSTART:
//          TrieNode newNode = new TrieNode();
//          if(i == 0) {// root
//            root = newNode;
//          }else {
//            newNode.ch = nodeName;
//            charMap.put(newNode,nodeName);
//          }
//          childStack.push(newNode);
//          paramStack.push(newNode);
//          break;
//        default:
//          nodeName = ch;
//      }
//    }
//    return root;
//  }
  static class TrieNode {
    public NavigableMap<Character, TrieNode> children;
    public TrieNode() {
      children = new TreeMap<Character, TrieNode>();
    }
    public Character ch;
  }
}


/**
 * 本代码由九章算法编辑提供。版权所有，转发请注明出处。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，Big Data 项目实战班，
 * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
 */
//
///**
// * Definition of TrieNode:
// * public class TrieNode {
// *     public NavigableMap<Character, TrieNode> children;
// *     public TrieNode() {
// *         children = new TreeMap<Character, TrieNode>();
// *     }
// * }
// */
//class Solution {
//  /**
//   * This method will be invoked first, you should design your own algorithm
//   * to serialize a trie which denote by a root node to a string which
//   * can be easily deserialized by your own "deserialize" method later.
//   */
//  public String serialize(TrieNode root) {
//    // Write your code here
//    if (root == null)
//      return "";
//
//    StringBuffer sb = new StringBuffer();
//    sb.append("<");
//    Iterator iter = root.children.entrySet().iterator();
//    while (iter.hasNext()) {
//      Map.Entry entry = (Map.Entry)iter.next();
//      Character key = (Character)entry.getKey();
//      TrieNode child = (TrieNode)entry.getValue();
//      sb.append(key);
//      sb.append(serialize(child));
//    }
//    sb.append(">");
//    return sb.toString();
//  }
//
//  /**
//   * This method will be invoked second, the argument data is what exactly
//   * you serialized at method "serialize", that means the data is not given by
//   * system, it's given by your own serialize method. So the format of data is
//   * designed by yourself, and deserialize it here as you serialize it in
//   * "serialize" method.
//   */
//  public TrieNode deserialize(String data) {
//    // Write your code here
//    if (data == null || data.length() == 0)
//      return null;
//
//    TrieNode root = new TrieNode();
//    TrieNode current = root;
//    Stack<TrieNode> path = new Stack<TrieNode>();
//    for (Character c : data.toCharArray()) {
//      switch (c) {
//        case '<':
//          path.push(current);
//          break;
//        case '>':
//          path.pop();
//          break;
//        default:
//          current = new TrieNode();
//          path.peek().children.put(c, current);
//      }
//    }
//    return root;
//  }
//}