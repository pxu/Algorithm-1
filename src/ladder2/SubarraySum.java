package ladder2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhahua on 1/30/17.
 */
public class SubarraySum {
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        int sum=0;
        map.put(0,-1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(map.containsKey(sum)){
                ArrayList<Integer> re=new ArrayList<Integer>();
                re.add(map.get(sum)+1);
                re.add(i);
                return re;
            }
            map.put(i,sum);
        }
        return null;
    }
    public static void main(String[] args){
        new SubarraySum().subarraySum(new int[]{-3, 1, 2, -3, 4});
    }
}
