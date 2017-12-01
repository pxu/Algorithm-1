package catagory.BFS;


import java.util.*;

/**
 * Created by zhahua on 11/9/17.
 */
//https://leetcode.com/problems/binary-tree-level-order-traversal/description/
//http://www.jiuzhang.com/solution/binary-tree-level-order-traversal/
public class BFSTemplate {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1) {{
            this.left = new TreeNode(2) {{
                this.right = new TreeNode(4);
            }};
            this.right = new TreeNode(3) {{
                this.left = new TreeNode(5);
            }};
        }};
        List<List<Integer>> result = new BFSTemplate().levelOrder(root);
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while(queue.size() > 0) {
            int size = queue.size();
            List<Integer> level = new ArrayList<Integer>();

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
