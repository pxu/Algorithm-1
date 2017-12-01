package systemdesign.jiuzhang.week5;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import systemdesign.jiuzhang.week3.HtmlParser;

/**
 * Created by zhahua on 7/28/17.
 */


class HtmlHelper {
      public static List<String> parseUrls(String url){
          switch(url) {
            case "www.yahoo.com" :
              return Arrays.asList(new String[]{"www.yahoo.com/1","www.yahoo.com/2","www.yahoo.com/3","www.google.com"});
            case "www.baidu.com" :
              return Arrays.asList(new String[]{"www.baidu.com/1","www.baidu.com/2","www.baidu.com/3","www.baidu.com"});
            case "www.google.com" :
              return Arrays.asList(
                  new String[]{"www.google.com/1", "www.google.com/2", "www.google.com/3",
                      "www.google.com"});
            default:
              return Collections.EMPTY_LIST;
          }
      }
 // Get all urls from a webpage of given url.
 }

public class Crawler
{
  public static void main(String[] args) {
    new Crawler().crawler("www.yahoo.com");

  }
  public int THREAD_POOL_SIZE = 10;

  public List<String> crawler(String url) {
    // Write your code here
    TaskManager tm = new TaskManager();
    tm.waitingQueue.add(url);
    tm.isAccessed.put(url,true);

    Thread[] threadPool = new Thread[THREAD_POOL_SIZE];

    for(int i = 0; i < THREAD_POOL_SIZE; i++) {
      threadPool[i] = new Thread(new UrlWorker(tm));
      threadPool[i].start();
    }
    do {

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }while(tm.workingCount.get() != 0);

    for(int i = 0; i < THREAD_POOL_SIZE; i++) {
      threadPool[i].interrupt();
    }

    System.out.println(tm.isAccessed.keySet());
    return new ArrayList(tm.isAccessed.keySet());
  }

}
class TaskManager {

  ConcurrentMap<String,Boolean> isAccessed = new ConcurrentHashMap<>();

  BlockingQueue<String> waitingQueue = new LinkedBlockingQueue<String>();

  AtomicInteger workingCount = new AtomicInteger();

}
class UrlWorker implements Runnable {

  TaskManager taskManager = null;
  public UrlWorker(TaskManager taskManager) {

    this.taskManager = taskManager;

  }



  public void run() {
    try {
      while(true) {

        String url = taskManager.waitingQueue.take();
        taskManager.workingCount.incrementAndGet();
        System.out.println("process:" + url);
        List<String> adjacentUrls = HtmlHelper.parseUrls(url);
        for(String adjacentUrl : adjacentUrls) {
          URL netUrl = new URL(adjacentUrl);


          if(netUrl.getHost().endsWith("wikipedia.org") && taskManager.isAccessed.get(adjacentUrl) == null) {
            taskManager.waitingQueue.add(adjacentUrl);
            taskManager.isAccessed.put(adjacentUrl,true);
            System.out.println("add    :" + url);
          }
          System.out.println("ignore    :" + url);
        }
        taskManager.workingCount.decrementAndGet();
      }
    }catch(InterruptedException ex) {

    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
