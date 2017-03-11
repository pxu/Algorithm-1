package ladder1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Anagrams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
class SolutionAG {
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
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
    	Map<Map<Character,Integer>,ArrayList<String>> map=new HashMap<Map<Character,Integer>,ArrayList<String>>();
    	for(String str : strs){
    	    Map<Character,Integer> chs=getChs(str);
    	    if(map.containsKey(chs))
    	    	map.get(chs).add(str);
    	    else {
    	    	ArrayList<String> al=new ArrayList<String>(); 
    	    	al.add(str);
    	    	map.put(chs, al);
    	    }
    	}
    	List<String> re=new ArrayList<String>();
    	
    	for(Entry<Map<Character, Integer>, ArrayList<String>> entry  : map.entrySet()){
    		if(entry.getValue().size()>=2)
    			re.addAll(entry.getValue());
    			
    	}
    	return re;
    }
}