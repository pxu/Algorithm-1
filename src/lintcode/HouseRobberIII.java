package lintcode;

public class HouseRobberIII {
//    3
//   / \
//  4   5
// / \   \ 
//1   3   1

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		TreeNode root=new TreeNode(3
//				,new TreeNode(4
//						,new TreeNode(1)
//						,new TreeNode(3)
//				)
//				,new TreeNode(5
//						,new TreeNode(1)
//						,null
//				)
//		);
		TreeNode root=new TreeNode(3
				,new TreeNode(2
						,null
						,new TreeNode(3)
				)
				,new TreeNode(3
						,null
						,new TreeNode(1)
				)
		);
		int re=new Solution().houseRobber3(root);
		System.out.println(re);
	}
	
	
}
 class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int x) { val = x; }
    public TreeNode(int x,TreeNode left, TreeNode right) { val = x;this.left=left;this.right=right; }
    
}

 class Solution {
	public int max(int arr[]){
		if (arr==null)
			return 0;
		return arr[0]>arr[1]? arr[0]:arr[1];
	}
	public int[] tran(TreeNode node){
		int[] result=new int[2];
		if(node==null)
		    return result;
		int[] resL=null;
		int[] resR=null;
		if(node.left!=null)
			resL=tran(node.left);
		if(node.right!=null)
			resR=tran(node.right);
		result[0]=max(resL)+max(resR);
		result[1]=(resL!=null?resL[0]:0)
					+(resR!=null?resR[0]:0)+node.val;
		return result;
	}
  /**
   * @param root: The root of binary tree.
   * @return: The maximum amount of money you can rob tonight
   */
  public int houseRobber3(TreeNode root) {
      // write your code here
  	int[] re=tran(root);
  	return max(re);
  }
}