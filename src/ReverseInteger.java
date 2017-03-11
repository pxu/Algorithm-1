
public class ReverseInteger {
//简单，复习过
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(reverse(1534236469));
	}
    public static int reverse(int x) {
        int f=x>=0? 1:-1;
        x=Math.abs(x);
        long y=0;
        while(x!=0){
        	y=10*y+x%10;
        	x=x/10;
        }
        y=y*f;
        if(y>Integer.MAX_VALUE || y<Integer.MIN_VALUE)
        	return 0;
        return (int) y;
    }

}
