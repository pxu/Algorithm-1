package JavaFund.JavaSynchronize;


import com.sun.org.apache.xpath.internal.operations.Bool;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

// rsync -avrz -e "ssh "  --include=*.class /Users/zhahua/Documents/workspaceAlgorithm/Algorithm/bin zhahua@zhahuagcp:/home/zhahua/javasynchronized
// rsync -avrz -e "ssh -p 2222 "  --include=*.class /Users/zhahua/Documents/workspaceAlgorithm/Algorithm/bin zhahua@d-sjn-20001275:/home/zhahua/Documents/workspace/javasynchronized
//ps -elf |grep Synchronize |awk -F" " '{print $4}' |xargs -I{} taskset -p -c 0 {}
//java JavaFund.JavaSynchronize.Synchronize 100 100000000 4 true
public class Synchronize {
  public static void main(String[] args) throws InterruptedException {
    SynchronizeBase.size = Integer.parseInt(args[0]);
    SynchronizeBase.total = Integer.parseInt(args[1]);
    SynchronizeBase.threadNum = Integer.parseInt(args[2]);

    SynchronizeBase.allocatePaddingBlock = Boolean.parseBoolean(args[3]);

    try {
      System.out.println("press any key to continue... ok3");
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
//    OneThreadWithoutSynchronized.start();
//    OneThreadWithoutSynchronized.start();
//    OneThreadSynchronized.start();
//    MultiThreadXCoreOneArraySynchronized.start();
    MultiThreadXCoreMultiArraySynchronized.start();
//    MultiThreadXCoreMultiArrayMySpinLock.start();
//    MultiThreadXCoreOneArrayNoSynchronized.start();
    //MultiThreadXCoreMultiArrayNoSynchronized.start();

  }

}
class SynchronizeBase {
  static int size = 100;
  static int total = 100000000;
  int[] array;
  static int threadNum;
  static boolean allocatePaddingBlock = true;
  static int[] wy;
  public SynchronizeBase(int[] array) {
    this.array = array;
  }
  public static int[] createNewArray() {
    int[] array = new int[size];
    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }
    if(allocatePaddingBlock) {
      wy = new int[1024];
      wy = new int[1024];
      wy = new int[1024];
    }

    return array;
  }
  public void reverse(int[] array) {
    for (int i = 0; i < array.length / 2; i++) {
      int tmp = array[i];
      array[i] = array[array.length - 1 - i];
      array[array.length - 1 - i] = tmp;
    }
  }
}
class OneThreadWithoutSynchronized extends SynchronizeBase
{
  public static void start() {
    long start = System.currentTimeMillis();
    int[] array = SynchronizeBase.createNewArray();
    OneThreadWithoutSynchronized instance = new OneThreadWithoutSynchronized(array);
    for (int i = 0 ; i < SynchronizeBase.total; i++) {
      instance.run();
    }
    System.out.println("OneThreadWithoutSynchronized t = " + (System.currentTimeMillis() - start));
  }
  public OneThreadWithoutSynchronized(int[] array) {
    super(array);
  }
  public void run() {
    reverse(array);
  }
}
class OneThreadSynchronized extends SynchronizeBase
    implements Runnable{
  public static void start() {

    long start = System.currentTimeMillis();
    int[] array = SynchronizeBase.createNewArray();
    OneThreadSynchronized instance = new OneThreadSynchronized(array);
    for (int i = 0 ; i < SynchronizeBase.total; i++) {
      instance.run();
    }
    System.out.println("OneThreadSynchronized t = " + (System.currentTimeMillis() - start));
  }
  public OneThreadSynchronized(int[] array) {
    super(array);
  }
  public synchronized void run() {
    reverse(array);
  }
}
class MultiThreadXCoreOneArraySynchronized extends SynchronizeBase
    implements Runnable{
  public static void start() throws InterruptedException {
    long start = System.currentTimeMillis();
    int[] array = SynchronizeBase.createNewArray();
    Thread[] ths = new Thread[threadNum];
    for(int t = 0; t < threadNum; t++) {
      ths[t] = new Thread(new MultiThreadXCoreOneArraySynchronized(array));
      ths[t].start();
    }
    for(int t = 0; t < threadNum; t++) {
      ths[t].join();
    }
    System.out.println("MultiThreadXCoreOneArraySynchronized, thread = " + threadNum + " t = " + (System.currentTimeMillis() - start));
  }
  public MultiThreadXCoreOneArraySynchronized(int[] array) {
    super(array);
  }
  @Override
  public void run() {
    int total = SynchronizeBase.total / SynchronizeBase.threadNum;
    for (int i = 0 ; i < total; i++) {

      reverseSync();
    }
  }
  public void  reverseSync() {
    synchronized (MultiThreadXCoreOneArraySynchronized.class) {
      reverse(array);
    }
  }
}

class MultiThreadXCoreMultiArraySynchronized extends SynchronizeBase
    implements Runnable{
  public static void start() throws InterruptedException {
    long start = System.currentTimeMillis();
    int[][] array= new int[threadNum][];
    for(int t = 0; t < threadNum; t++) {
      array[t] = SynchronizeBase.createNewArray();
    }
    Thread[] ths = new Thread[threadNum];


    for(int t = 0; t < threadNum; t++) {
      ths[t] = new Thread(new MultiThreadXCoreMultiArraySynchronized(array[t]));
      ths[t].start();
    }
    for(int t = 0; t < threadNum; t++) {
      ths[t].join();
    }
    System.out.println("MultiThreadXCoreMultiArraySynchronized, thread = " + threadNum + " t = " + (System.currentTimeMillis() - start));
  }
  public MultiThreadXCoreMultiArraySynchronized(int[] array) {
    super(array);
  }
  @Override
  public void run() {
    int total = SynchronizeBase.total / SynchronizeBase.threadNum;
    for (int i = 0 ; i < total; i++) {
      reverseSync();
    }
  }
  public void reverseSync() {
    synchronized (MultiThreadXCoreMultiArraySynchronized.class) {
      reverse(array);
    }
  }
}


class MultiThreadXCoreMultiArrayMySpinLock extends SynchronizeBase
    implements Runnable{
  public static AtomicReference<Thread> spinlock = new AtomicReference<Thread>();
  public static void start() throws InterruptedException {
    long start = System.currentTimeMillis();

    int[][] array= new int[threadNum][];
    for(int t = 0; t < threadNum; t++) {
      array[t] = SynchronizeBase.createNewArray();
    }
    Thread[] ths = new Thread[threadNum];


    for(int t = 0; t < threadNum; t++) {
      ths[t] = new Thread(new MultiThreadXCoreMultiArrayMySpinLock(array[t]));
      ths[t].start();
    }
    for(int t = 0; t < threadNum; t++) {
      ths[t].join();
    }
    System.out.println("MultiThreadXCoreMultiArrayMySpinLock, thread = " + threadNum + " t = " + (System.currentTimeMillis() - start));
  }
  public MultiThreadXCoreMultiArrayMySpinLock(int[] array) {
    super(array);
  }
  @Override
  public void run() {
    int total = SynchronizeBase.total / SynchronizeBase.threadNum;
    Thread curtTh = Thread.currentThread();
    int wasted = 0;
    for (int i = 0 ; i < total; i++) {
      while (!spinlock.compareAndSet(null, curtTh)) { wasted++; }
      reverse(array);
      spinlock.set(null);
    }
    System.out.println("wasted spin = "+ wasted);
  }
}


class MultiThreadXCoreOneArrayNoSynchronized extends SynchronizeBase
    implements Runnable{
  public static void start() throws InterruptedException {
    long start = System.currentTimeMillis();
    int[] array = SynchronizeBase.createNewArray();
    Thread[] ths = new Thread[threadNum];
    for(int t = 0; t < threadNum; t++) {
      ths[t] = new Thread(new MultiThreadXCoreOneArrayNoSynchronized(array));
      ths[t].start();
    }
    for(int t = 0; t < threadNum; t++) {
      ths[t].join();
    }
    System.out.println("MultiThreadXCoreOneArrayNoSynchronized, thread = " + threadNum + " t = " + (System.currentTimeMillis() - start));
  }
  public MultiThreadXCoreOneArrayNoSynchronized(int[] array) {
    super(array);
  }
  @Override
  public void run() {
    int total = SynchronizeBase.total / SynchronizeBase.threadNum;
    for (int i = 0 ; i < total; i++) {
      reverseSync();
    }
  }
  public void  reverseSync() {
    //synchronized (MultiThreadXCoreOneArraySynchronized.class) {
    reverse(array);
    //}
  }
}


class MultiThreadXCoreMultiArrayNoSynchronized extends SynchronizeBase
    implements Runnable{
  public static void start() throws InterruptedException {
    long start = System.currentTimeMillis();
    int[][] array= new int[threadNum][];
    for(int t = 0; t < threadNum; t++) {
      array[t] = SynchronizeBase.createNewArray();
    }
    Thread[] ths = new Thread[threadNum];

    for(int t = 0; t < threadNum; t++) {
      ths[t] = new Thread(new MultiThreadXCoreMultiArrayNoSynchronized(array[t]));
      ths[t].start();
    }
    for(int t = 0; t < threadNum; t++) {
      ths[t].join();
    }
    System.out.println("MultiThreadXCoreMultiArrayNoSynchronized, thread = " + threadNum + " t = " + (System.currentTimeMillis() - start));
  }
  public MultiThreadXCoreMultiArrayNoSynchronized(int[] array) {
    super(array);
  }
  @Override
  public void run() {
    int total = SynchronizeBase.total / SynchronizeBase.threadNum;
    for (int i = 0 ; i < total; i++) {

      reverseSync();
    }
  }
  public void reverseSync() {
    //synchronized (MultiThreadXCoreMultiArraySynchronized.class) {
    reverse(array);
    //}
  }
}