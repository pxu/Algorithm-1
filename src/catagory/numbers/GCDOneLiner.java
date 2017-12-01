package catagory.numbers;

/**
 * Created by zhahua on 11/4/17.
 */
public class GCDOneLiner {
    public static void main(String[] args) {
        System.out.println(GCD(18, 24));
    }

    public static int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }
}
