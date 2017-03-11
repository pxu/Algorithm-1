
public class longestCommonPrefix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(longestCommonPrefix(new String[]{"aababbcd","aabacd","aabadc","aaba"}));
	}
    public static String longestCommonPrefix(String[] strs) {
    	if(strs.length==0)
    		return "";
        int i=0;
        String prefix="";
        char ch='0';
        boolean first=true;
    	outer: while(true){
    		for(int j=0;j<strs.length;j++){
    			if(i==strs[j].length())
    				break outer;
    			char chTmp=strs[j].charAt(i);
    			if(first){
    				ch=chTmp;first=false;
    			}else if(chTmp!=ch){
    				break outer;
    			}
    		}
    		i++;
    		first=true;
    	}
        return strs[0].substring(0,i);
    }
}
