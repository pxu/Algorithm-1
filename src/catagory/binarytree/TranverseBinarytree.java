package catagory.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhahua on 11/16/17.
 */
//http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
public class TranverseBinarytree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static void main(String[] args) {
        TreeNode r = new TreeNode(0);

    }

    public List<Integer> tranverseBinaryTree(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while(current != null) {
            TreeNode left = current.left;
            if(left == null) {
                System.out.println(current.val);
                result.add(current.val);
                current = current.right;
                continue;
            }
            TreeNode prev = left;
            while(prev.right != null && prev.right != current) {
                prev = prev.right;
            }
            if(prev.right == current) {
                result.add(current.val);
                System.out.println(current.val);
                prev.right = null;
                current = current.right;
                continue;
            }
            if(prev.right == null) {
                prev.right = current;
                current = current.left;
                continue;
            }
        }
        return result;
    }


}
