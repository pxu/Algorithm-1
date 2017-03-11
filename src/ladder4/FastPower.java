package ladder4;

/**
 * Created by zhahua on 2/19/17.
 */
public class FastPower {
    public static void main(String[] args){
        int re=fastPower(2, 2147483647, 2147483647);

    }
    public static int fastPower(int a,int b,int n){
        int am=a%b;
        long power=1;
        int nRemain=n;
        long amPower=am;
        while(nRemain!=0){
            if((nRemain&1)==1) {
                power = (power*amPower)%b;
            }
            nRemain>>=1;
            amPower=(amPower*amPower)%b;
        }
        return (int)(power%b);
    }
}
