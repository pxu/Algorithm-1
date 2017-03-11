package ladder2;

import java.util.Arrays;

/**
 * Created by zhahua on 1/31/17.
 */
public class threeSumClosest {
    public static void main(String[] args){
        new threeSumClosest().threeSumClosest(new int[]{-8,-0,-7,-101,-123,-1,-2,1,1,4,-2,0,-1,0,-1111,0,-1,-2,-3,-4,-5,-6,-100,-98,-111,-11},-101);
    }
    public int closet(int num,int target,int cur){
        if(Math.abs(num-target)<Math.abs(target-cur)){
            return num;
        }
        return cur;
    }
    public int threeSumClosest(int[] numbers, int target) {
        if(numbers.length<3)
            return 0;
        Arrays.sort(numbers);
        int min=numbers[0]+numbers[1]+numbers[2];
        // write your code here
        for(int i=0;i<numbers.length-2;i++){
            if(i==0||numbers[i-1]!=numbers[i]){
                int s=i+1;
                int e=numbers.length-1;
                int j=s;
                int k=e;
                while(true){
                    while(j<k&&(j!=s&&numbers[j-1]==numbers[j])){
                        j++;
                    }
                    if(j==k)
                        break;
                    while(j<k&&numbers[i]+numbers[j]+numbers[k]>target){
                        min=closet(numbers[i]+numbers[j]+numbers[k],target,min);
                        k--;
                    }
                    if(j==k)
                        break;
                    min=closet(numbers[i]+numbers[j]+numbers[k],target,min);
                    j++;
                }
            }
        }
        return min;
    }
}
