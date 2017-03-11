package strings;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(anagram("abcd","bcaa"));
	}
	public static Map<Character,Integer> getChs(String s){
    	Map<Character, Integer> chs=new HashMap<Character,Integer>();
    	for(int i=0; i<s.length() ;i++){
    		Character ch=Character.valueOf(s.charAt(i));
    		if(!chs.containsKey(ch))
    			chs.put(ch, 1);
    		else 
    			chs.put(ch,chs.get(ch)+1);
    	}
    	return chs;
	}
    public static boolean anagram(String s, String t) {
        // write your code here
    	if(s.length()!=t.length())
    		return false;
    	Map<Character,Integer> mapS=getChs(s);
    	Map<Character,Integer> mapT=getChs(t);
    	return mapS.equals(mapT);
    }
}
