package JavaFund;

/**
 * Created by zhahua on 3/3/17.
 */
public class GCEscapeTest {
//    static int[] array =new int[100];
//    public static int helper(int a){
//
//        for(int i=0;i<array.length;i++){
//            array[i]=i;
//        }
//        return 1;
//
//    }
//    public static void main(String[] args){
//        while(true){
//            helper(100);
//        }
//    }
    private static class Foo {
        private long x;
        private static int counter;
        public int a=10;
        public int b=10;
    Foo c=null;
    public Foo() {
            x = (++counter);
        }
        public Foo(Foo other){
            this.a=other.a;
            this.b=other.b;
            this.x=other.x;
        }
    }
    public static void main(String[] args) {
        System.out.println("start");
        for (long i = 0;i<Integer.MAX_VALUE; ++i) {
            Foo foo = new Foo();
            Foo fo1=new Foo(foo);
        }

        System.out.println(Foo.counter);
    }
}
