package ladder1;

import java.util.HashMap;
import java.util.Map;

public class CompareStrings {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Solutioncs().compareStrings("ABCCD", "ABCDE"));
	}

}
class Solutioncs {
    Map<Character,Integer> getWc(String s){
    	Map<Character,Integer> wc=new HashMap<Character,Integer>();
    	for(Character c : s.toCharArray()){
    		if(!wc.containsKey(c))
    			wc.put(c, 1);
    		else 
    			wc.put(c,wc.get(c)+1);
    	}
    	return wc;
    }
	/**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
    	Map<Character,Integer> wcA=getWc(A);
    	Map<Character,Integer> wcB=getWc(B);
    	for(Character c:wcB.keySet()){
    		if(!wcA.containsKey(c))
    			return false;
    		else if(wcA.get(c)<wcB.get(c))
    			return false;
    	}
    	return true;
    }
}