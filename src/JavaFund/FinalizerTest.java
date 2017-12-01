package JavaFund;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhahua on 3/14/17.
 */
public class FinalizerTest {
  static class GoodFinalizer{
    static volatile AtomicInteger counter=new AtomicInteger();
    private byte[] data = new byte[10000];
    public GoodFinalizer(){
      counter.incrementAndGet();
    }
    @Override
    protected void finalize() throws Throwable {
      super.finalize();

      counter.decrementAndGet();
    }
  }
  static class BadFinalizer{
    static volatile AtomicInteger counter=new AtomicInteger();
    public BadFinalizer(){
      counter.incrementAndGet();
    }
    @Override
    protected void finalize() throws Throwable {
      super.finalize();
      Thread.sleep(1000000);
      counter.decrementAndGet();
    }
  }
  //java -Xmx2000m -Xms2000m -Xmn1m -XX:+UseConcMarkSweepGC -XX:-DoEscapeAnalysis -XX:+HeapDumpOnOutOfMemoryError -cp /Users/zhahua/Documents/workspaceAlgorithm/Algorithm/bin/ JavaFund.FinalizerTest  
  //-XX:+PrintGCTimeStamps -XX:+PrintGCDetails 
  public static void main(String[] args) throws InterruptedException {

    for(int i=0;i<1000000;i++){
      if(i==0) {
        Object wt = new BadFinalizer();
      }
      if(i % 10000 == 0) {
        System.out.println("GF" + GoodFinalizer.counter.get());
        Thread.sleep(1);
      }
      Object obj=new GoodFinalizer();
    }
    for(int i=0;i<10000;i++){
        Object obj=new Object();
        System.out.println("GF" + GoodFinalizer.counter.get());
        Thread.sleep(1);
    }
  }
}
