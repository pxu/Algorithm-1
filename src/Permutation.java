
public class Permutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		permutation(new char[]{'1','2','3','4'},0);
	}
	public static void swap(char[] strArray,int a,int b){
		char tmp=strArray[a];
		strArray[a]=strArray[b];
		strArray[b]=tmp;
	}
	public static void permutation(char[] strArray,int start){
		if(start==strArray.length)
			System.out.println(new String(strArray));
		else{
			for(int i=start;i<strArray.length;i++){
				swap(strArray,start,i);
				permutation(strArray,start+1);
				swap(strArray,start,i);
				
			}
		}
	}

}
