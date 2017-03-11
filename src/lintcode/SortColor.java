package lintcode;

import java.util.Arrays;

public class SortColor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sortColors(new int[]{0,2,2,2,2,1,0,1,0,0,0,1,0,2,0},3);
	}
	public static int pointers[]=null;
	public static void swap(int[] nums,int i,int j){
		int tmp=nums[i];
		nums[i]=nums[j];
		nums[j]=tmp;
	}
	public static void moveColor(int[] nums,int color,int colorNum,int start){
		int end=pointers[color-1];
		if(nums[start]==color)
			return;
		else{
			if(end>start+1){
				swap(nums,start,end-1);
				if(color+1<colorNum-1)
					moveColor(nums,color+1,colorNum,end-1);
				pointers[color-1]--;
			}else if(end==start+1){
				if(color+1<colorNum-1)
					moveColor(nums,color+1,colorNum,end-1);
				pointers[color-1]--;
			}
		}
		System.out.println(Arrays.toString(nums));
	}
	public static void sortColors(int[] nums,int colorNum){
    	pointers=new int [colorNum-2];
    	for(int i=0;i<pointers.length;i++){
    		pointers[i]=nums.length;
    	}
		int i=0;
		int j=nums.length;
		
		while(true){
			while(i<j&&nums[i]==0){
				i++;
			}
			if(i==j)
				break;
			while(i<j&&nums[j-1]>0){
				moveColor(nums,1,colorNum,j-1);
				j--;
			}
			if(i==j)
				break;
			swap(nums,i,j-1);
			moveColor(nums,1,colorNum,j-1);
			System.out.println(Arrays.toString(nums));
			j--;
		}
	}
//	public static void moveColor(int[] nums,int color,int colorNum,int start){
//		if(color==colorNum-1)
//			return;
//		int end=pointers[color-1];
//		if(nums[start]>color ){
//			if(start <end)
//				swap(nums,start,end-1);
//			pointers[color-1]=end-1;
//			if(color<colorNum-2)
//				moveColor(nums,color+1,colorNum,end-1);
//		}
//	}
//    public static void sortColors(int[] nums,int colorNum) {
//        // write your code here
//    	pointers=new int [colorNum-2];
//    	for(int i=0;i<pointers.length;i++){
//    		pointers[i]=nums.length;
//    	}
//    	int i=0;
//    	int j=nums.length-1;
//    	while(true){
//    		while(i<j&&nums[i]==0){
//    			i++;
//    		}
//    		if(i==j)
//    			break;
//    		while(i<=j&&nums[j]>0){
//    			moveColor(nums,1,colorNum,j);
//    			j--;
//    		}
//    		if(i>j)
//    			break;
//    		swap(nums,i,j);
//    		moveColor(nums,1,colorNum,j);
//    		System.out.println(Arrays.toString(nums));
//    		j--;
//    	}
//    	
//    }
}
