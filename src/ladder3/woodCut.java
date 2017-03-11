package ladder3;

/**
 * Created by zhahua on 2/2/17.
 */
public class woodCut {
    public static void main(String[] args){
        System.out.println(woodCut(new int[]{2147483644,2147483645,2147483646,2147483647},4));
    }
    public static int woodCut(int[] L, int k) {
        // write your code here
        if(L.length==0)
            return 0;
        int max=L[0];
        for(int i=1;i<L.length;i++){
            max=Math.max(L[i],max);
        }
        long l=0;
        long r=max/(((long)k-1)/L.length+1)+1;
        long i=(l+r)/2;
        while(i!=l){
            System.out.println(i);
            int num=0;
            for(int j=0;j<L.length;j++){
                num+=L[j]/i;
            }
            if(num>=k){
                l=i;
            }else{
                r=i;
            }
            i=(l+r)/2;
        }
        return (int)i;
    }
}
