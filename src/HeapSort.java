
public class HeapSort {

	private static void max_heapify(int[] array,int i,int n){
		
		int left=2*i+1;
		int right=2*i+2;
		int largest=i;
		if(left<n && array[left]>array[i]){
			largest=left;
		}
		if(right<n && array[right]>array[largest]){
			largest=right;
		}
		if (largest!=i){
			int tmp=array[largest];
			array[largest]=array[i];
			array[i]=tmp;
			max_heapify(array,largest,n);
		}
		//print(array);
	}
	private static void build_max_heap(int[]array,int n){
		for(int i=n/2-1;i>=0;i--){
			max_heapify(array,i,n);
		}
	}
	private static void heap_sort(int []array,int n){
		build_max_heap(array,n);
		for(int i=n-1;i>0;i--){
			int tmp=array[0];
			array[0]=array[i];
			array[i]=tmp;
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array=new int[]{3,5,7,3,2,4,5,2,1,66,333,77,3,88,33,22,55,78,45,65,343};
		print(array);
		heap_sort(array,array.length);
		
	}

}
