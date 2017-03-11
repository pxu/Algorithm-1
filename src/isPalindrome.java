
public class isPalindrome {
//简单，已复习
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean wt=isPalindrome(1221);
	}
	public static boolean isPalindrome(int x) {
        if(x==0)
            return true;
        if(x<0)
            return false;
        int l=0;
        int xl=x;
        int xr=x;
        int len=0;
        while(xl!=0){//先看一共有几位
            xl=xl/10;
            if(l==0)
                l=1;
            else
                l=l*10;
            len++;
        }
        int p=len,q=1;
        boolean isPal=true;
        while(p>q){
            int ln=x/l%10;//分别提取左右
            int rn=xr%10;
            if(ln!=rn){
                isPal=false;
                break;
            }
            l/=10;
            xr/=10;
            p--;
            q++;
        }
        return isPal;
    }
}
