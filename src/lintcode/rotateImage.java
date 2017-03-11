package lintcode;

public class rotateImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix=new int[][]{
				new int[]{0,1,2,3,4},
				new int[]{10,11,12,13,14},
				new int[]{20,21,22,23,24},
				new int[]{30,31,32,33,34},
				new int[]{40,41,42,43,44}
				
		};
		new Solution2().rotate(matrix);
	}

}
class Solution2 {
	public void test(int[][] matrix){
		System.out.println();
    	for(int i=0;i<matrix.length;i++){
    		for(int j=0;j<matrix.length;j++){
    			System.out.print(""+matrix[i][j]+" ");
    		}

			System.out.println();
    	}
	}
    /**
     * @param matrix: A list of lists of integers
     * @return: Void
     */
    public void rotate(int[][] matrix) {
        // write your code here
    	int n=matrix.length;
    	test(matrix);
    	for(int y=0;y<n/2;y++){
    		for(int x=y;x<n-y-1;x++){
    			int tmp=matrix[y][x];
    			matrix[y][x]=matrix[n-1-x][y];
    			matrix[n-1-x][y]=matrix[n-1-y][n-1-x];
    			matrix[n-1-y][n-1-x]=matrix[x][n-1-y];
    			matrix[x][n-1-y]=tmp;
    			//test(matrix);
    		}
    	}

    }
}