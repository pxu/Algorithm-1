package ladder2;

import java.util.ArrayList;
import java.util.Arrays;

public class threeSum {
	public static ArrayList<ArrayList<Integer>> sum3(int[] numbers){
		
		ArrayList<ArrayList<Integer>> re=new ArrayList<ArrayList<Integer>>();
		Arrays.sort(numbers);
		for(int k=0;k<numbers.length-2;k++){
			if(k>0 && numbers[k-1]==numbers[k])
				continue;
			int s=k+1;
			int e=numbers.length-1;
			int i=s;
			int j=e;
			int target=0-numbers[k];
			while(true){
				while(i<j && numbers[i]+numbers[j]<=target){
					if ((i==s || numbers[i-1]!=numbers[i]) && numbers[i]+numbers[j]==target){
						ArrayList<Integer> tuple=new ArrayList<Integer>();
						tuple.add(numbers[k]);
						tuple.add(numbers[i]);
						tuple.add(numbers[j]);
						re.add(tuple);
					}
						//System.out.println(""+numbers[k]+","+numbers[i]+","+numbers[j]);
					i++;
				}
				if(i==j)
					break;
				while(i<j && numbers[i]+numbers[j]>=target){
					if ((j==e || numbers[j]!=numbers[j+1]) && numbers[i]+numbers[j]==target){
						ArrayList<Integer> tuple=new ArrayList<Integer>();
						tuple.add(numbers[k]);
						tuple.add(numbers[i]);
						tuple.add(numbers[j]);
						re.add(tuple);
					}
					j--;
				}
				if(i==j)
					break;
			}
		}
		return re;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sum3(new int[]{0,1,1,2,2,3,4,5,6,7,7,8,8,9,9});
	}

}
