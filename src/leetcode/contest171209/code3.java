package leetcode.contest171209;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class code3 {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(0) {{
      this.left = new TreeNode(1);
      this.right = new TreeNode(2);

    }};
    new code3().findClosestLeaf(root, 0);
  }
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    TreeNode(int x) { val = x; }
  }
  static class MyTreeNode {
    int val;
    MyTreeNode left;
    MyTreeNode right;
    MyTreeNode parent;
    boolean visited = false;
    MyTreeNode(int x, MyTreeNode parent) { val = x; this.parent = parent; }
  }
  public int findClosestLeaf(TreeNode root, int k) {
    MyTreeNode copy = new MyTreeNode(root.val, null);
    MyTreeNode target = clone(root, copy, k);
    if (target == null) {
      return -1;
    }
    return findLeave(target);
  }

  public MyTreeNode clone(TreeNode start, MyTreeNode copy, int k) {
    Map<TreeNode, MyTreeNode> map = new HashMap<>();
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(start);
    map.put(start,copy);

    while (!queue.isEmpty()) {
      TreeNode head = queue.poll();

      if (head.left != null) {
        queue.add(head.left);
        map.put(head.left, new MyTreeNode(head.left.val, map.get(head)));
      }
      if (head.right != null) {
        queue.add(head.right);
        map.put(head.right, new MyTreeNode(head.right.val, map.get(head)));
      }
    }

    queue = new ArrayDeque<>();
    queue.offer(start);
    MyTreeNode myK = null;
    while (!queue.isEmpty()) {
      TreeNode head = queue.poll();
      MyTreeNode myHead = map.get(head);
      if (myHead.val == k) {
        myK = myHead;
      }
      myHead.left = map.get(head.left);
      myHead.right = map.get(head.right);
      if (head.left != null) {
        queue.add(head.left);
      }
      if (head.right != null) {
        queue.add(head.right);
      }
    }
    return myK;
  }

  public int findLeave(MyTreeNode start) {
    Queue<MyTreeNode> queue = new ArrayDeque<>();
    queue.offer(start);
    start.visited = true;
    while (!queue.isEmpty()) {
      MyTreeNode head = queue.poll();
      if (head.left == null && head.right == null && head != start) {
        return head.val;
      }
      if (head.left != null && !head.left.visited) {
        head.left.visited = true;
        queue.add(head.left);
      }
      if (head.right != null && !head.right.visited) {
        head.right.visited = true;
        queue.add(head.right);
      }
      if (head.parent != null && !head.parent.visited) {
        head.parent.visited = true;
        queue.add(head.parent);
      }

    }
    return -1;
  }
}
