package ladder2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zhahua on 1/31/17.
 */
public class ProductExcludeItself {

    public ArrayList<Long> productExcludeItself(ArrayList<Integer> A) {
        // write your code
        long s=1;
        ArrayList<Long> re=new ArrayList<Long>(A.size());
        for(int i=0;i<A.size();i++){
            re.add(i,Long.valueOf(0));
        }
        for(int i=A.size()-1;i>0;i--){
            s*=A.get(i);
            re.set(i,Long.valueOf(s));
        }
        s=1;
        for(int i=0;i<A.size()-1;i++){
            re.set(i,Long.valueOf(s*re.get(i+1)));
            s*=A.get(i);
        }
        re.set(A.size()-1,Long.valueOf(s));
        return re;
    }
    public static void main(String[] args){
        new ProductExcludeItself().productExcludeItself(new ArrayList<Integer>(){{this.addAll(Arrays.asList(new Integer[]{1,2,3}));}});
    }
}
