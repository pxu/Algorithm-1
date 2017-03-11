package ladder1;

public class strStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new SolutionSS().strStr("SolutionSS","SolutionSS"));
	}

}
class SolutionSS {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        // write your code here
    	if(target.length()==0)
    		return 0;
    	if(target.length()>source.length())
    		return -1;
        for(int i=0;i<source.length()-target.length()+1;i++){
        	int j=0;
        	for(j=0;j<target.length();j++){
    			if(source.charAt(i+j)!=target.charAt(j))
    				break;
    		}
    		if(j==target.length())
    			return i;
    	}
        return -1;
    }
}