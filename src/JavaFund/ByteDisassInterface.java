package JavaFund;

/**
 * Created by zhahua on 3/2/17.
 */
public class ByteDisassInterface {
    public static void main(String[] args){
        TestInterface a=new TestClass();
        a.test(10);
        ((TestClass)a).test1(10);
    }
}
interface TestInterface{
    int test(int a);
}
class TestClass implements TestInterface{
    public int test(int a){
        return 10;
    }
    public int test1(int a){
        return 12;
    }
}
