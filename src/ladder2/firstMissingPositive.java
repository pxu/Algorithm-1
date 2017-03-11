package ladder2;

import java.util.Arrays;

public class firstMissingPositive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int wt=new SolutionfmP().firstMissingPositive1(new int[]{2,1});
		System.out.println(wt);
	}

}
class SolutionfmP {
	public void print(int [] A){
		System.out.println(Arrays.toString(A));
	}
    /**    
     * @param A: an array of integers
     * @return: an integer
     */
    public int firstMissingPositive(int[] A) {
        if(A.length==0)
        	return 1;
        
    	int min=A[0];
    	int max=A[0];
    	for(int i=1;i<A.length;i++){
    		if(min>A[i])
    			min=A[i];
    		if(max<A[i])
    			max=A[i];
    	}
    	if(max<=0)
    		return 1;
    	int s= 1;
    	for(int i=0;i<A.length;i++){
			int currentNumber=A[i];
			A[i]=0;
    		while(currentNumber>0 && currentNumber-s<A.length && currentNumber !=A[currentNumber-s]){
    			int tmp=A[currentNumber-s];
    			A[currentNumber-s]=currentNumber;
    			currentNumber=tmp;
    			//print(A);
    		}
    		//print(A);
    	}
    	int j=0;
    	for(j=0;j<A.length;j++){
    		if(A[j]==0)
    			return s+j;
    	}
        return s+j;    
        
    }
    //the standard way
    public int firstMissingPositive1(int[] A) {
    	if(A.length==0)
        	return 1;
        for(int i=0;i<A.length;i++){
        	//A[i]!=i+1 和 A[A[i]-1]!=A[i]重复
        	while(A[i]>0 && A[i]!=i+1 && A[i]-1<A.length && A[A[i]-1]!=A[i]){
        		int tmp=A[A[i]-1];
        		A[A[i]-1]=A[i];
        		A[i]=tmp;
        		print(A);
        	}
        }
        for(int i=0;i<A.length;i++){
        	if(A[i]<=0 || i!=A[i]-1){
        		return i+1;
        	}
        }
        return A.length+1;
        
    }
}