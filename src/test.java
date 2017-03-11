
public class test {

	private static void max_heapify(int[] array,int i,int n){
		int left=2*i+1;
		int right=2*i+2;
		int largest=i;
		if(left<n && array[left]>array[largest])
			largest=left;
		if(right<n && array[right]>array[largest])
			largest=right;
		if(largest!=i){
			int tmp=array[largest];
			array[largest]=array[i];
			array[i]=tmp;
			max_heapify(array,largest,n);
		}
	}
	private static void build_max_heap(int[] array,int n){
		for(int i=n/2-1;i>=0;i--){
			max_heapify(array,i,n);
		}
	}
	private static void heap_sort(int []array){
		int n= array.length;
		build_max_heap(array,n);
		for(int i=n-1;i>0;i--){
			int tmp=array[i];
			array[i]=array[0];
			array[0]=tmp;
			max_heapify(array,0,i);
			print(array);
		}
	}
	
	private static void print(int [] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	private static void increase_key(int [] array,int i, int value){
		array[i]=value;
		while(i>0 && array[i]>array[(i-1)/2]){
			int tmp=array[i];
			array[i]=array[(i-1)/2];
			array[(i-1)/2]=tmp;
			i=(i-1)/2;
			print(array);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array=new int[]{8,7,6,3,4,5,1};
		print(array);
		increase_key(array,5,9);
		
	}


}
