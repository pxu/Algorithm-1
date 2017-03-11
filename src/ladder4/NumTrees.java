package ladder4;

/**
 * Created by zhahua on 2/19/17.
 */
public class NumTrees {
    public static void main(String[] args){
        int wt=new SolutionNT().numTrees(3);
    }
}
class SolutionNT {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        // write your code here
        if(n<2)
            return 1;
        int[] mem=new int[n+1];
        mem[1]=1;
        mem[0]=1;
        for(int i=2;i<=n;i++){
            int count=0;
            for(int j=1;j<=i;j++){
                count+=mem[j-1]*mem[i-j];
            }
            mem[i]=count;
        }
        return mem[n];
    }
}