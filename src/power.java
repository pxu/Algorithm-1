
public class power {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int wt=power(10,7);
	}
	public static int power(int x,int y){
		int result=1;
		int base=x;
		
		while(y!=0){
			int remain=y%3;
			y=y/3;
			if(remain==1)
				result*=base;
			else if(remain==2)
				result*=base*base;
			base=base*base*base;
		}
		return result;
	}
}
