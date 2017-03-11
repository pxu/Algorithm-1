package ladder2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class twoSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Solution2Sum().twoSum(new int[]{0,1,1,2,2,3,3,4,5,5,5,5,6,7,8,9},10);
	}

}

class Solution2Sum {
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] numbers, int target) {
        // write your code here
    	Map<Integer, HashSet<Integer>> map=new HashMap<Integer,HashSet<Integer>>();
    	for(int i=1;i<numbers.length;i++){
    		if(map.containsKey(numbers[i])){
    			map.get(numbers[i]).add(i);
    		}else{
    			HashSet<Integer> set=new HashSet<Integer>();
    			set.add(i);
    			map.put(numbers[i], set);
    		}
    	}
    	for(int i=0;i<numbers.length-1;i++){
    		int other=target-numbers[i];
    		if(!map.containsKey(other))
    			continue;
    		HashSet<Integer> set=map.get(other);
    		for(Integer index:set){
    			//System.out.println(""+i+","+index+","+numbers[i]+","+numbers[index]);
    			return new int[]{i+1,index+1};
    		}
    		map.get(numbers[i+1]).remove(i+1);
    	}
    	return null;
    }
}