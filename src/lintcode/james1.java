package lintcode;

import java.util.ArrayList;
import java.util.Arrays;


public class james1 {
	public static void main(String[] args) {
		new james1().removeDuplicates(new ArrayList<Integer>(){{
			this.add(1);this.add(1);this.add(1);this.add(2);this.add(2);}});
	}
    public int removeDuplicates(ArrayList<Integer> a) {
        int r=0;
        int w=0;
        int len = a.size();
        if (len <= 1) {
            // no dup
            return len;
        }
        
        for (r=1; r < len; r++) {
            if(!a.get(r-1).equals(a.get(r))){
                a.set(w,a.get(r-1));
                w++;
            }
        }
        a.set(w++, a.get(len-1));
        /// [ 1 2 3 3] [1 2 3 5]
        return w;
    }
}



