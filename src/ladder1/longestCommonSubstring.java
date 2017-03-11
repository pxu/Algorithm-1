package ladder1;

public class longestCommonSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new lcsSolution().longestCommonSubstring("BBAAACC", "CAAADD");
	}

}
 class lcsSolution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        int n=A.length();
        int m=B.length();
        int max=0;
    	for(int i=-m+1;i<n;i++){
    		int sb=i;
    		int eb=i+m;
    		int sc=sb>0?sb:0;
    		int ec=eb>n?n:eb;
    		int commonCt=0;
    		for(int ic=sc;ic<ec;ic++){
    			if(A.charAt(ic)==B.charAt(ic-i))
    				commonCt++;
    			else {
    				if(max<commonCt)
    					max=commonCt;
    				commonCt=0;
    			}
    		}
			if(max<commonCt)
				max=commonCt;
			commonCt=0;
				
    	}
    	return max;
    }
}