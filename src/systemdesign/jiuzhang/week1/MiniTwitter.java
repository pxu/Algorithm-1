package systemdesign.jiuzhang.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Created by zhahua on 7/12/17.
 */
/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */
public class MiniTwitter {

  
  public static void main(String[] args) {
    MiniTwitter tw = new MiniTwitter();
    tw.testmain(args);
  }
  public  void testmain(String[] args) {
    /*
postTweet(1, "LintCode is Good!!!")
>> 1
getNewsFeed(1)
>> [1]
getTimeline(1)
>> [1]
follow(2, 1)
getNewsFeed(2)
>> [1]
unfollow(2, 1)
getNewsFeed(2)
>> []
     */

    postTweet(1, "LintCode is Good!!!");
    getNewsFeed(1);
    getTimeline(1);
    follow(2, 1);
    follow(2, 3);
    postTweet(3, "LintCode is Cool!!!");
    postTweet(3, "How to do A + B problem?");
    postTweet(3, "I have accepted A + B problem.");
    postTweet(3, "I favorite A + B problem.");
    postTweet(1, "I favorite A + B problem too.");
    postTweet(2, "Nani?");
    postTweet(2, "I want to solve this problem now.");
    postTweet(3, "I want to solve this problem now.");
    postTweet(3, "The problem is so easy.");
    postTweet(1, "hehe.");
    postTweet(2, "Let's to do it together.");
    postTweet(2, "haha");
    System.out.print(getNewsFeed(2));
    getTimeline(2);
    getNewsFeed(1);
    follow(1, 2);
    getNewsFeed(1);
    follow(1, 3);
    getNewsFeed(1);
    unfollow(2, 1);
    getNewsFeed(2);
  }

  private ConcurrentMap<Integer,ConcurrentMap<Integer,Integer>> follows = new ConcurrentHashMap<>();
  private ConcurrentMap<Integer,BlockingDeque<TweetTuple>> tweets = new ConcurrentHashMap<>();

  public MiniTwitter() {
    // initialize your data structure here.
  }

  // @param user_id an integer
  // @param tweet a string
  // return a tweet
  public Tweet postTweet(int user_id, String tweet_text) {
    BlockingDeque<TweetTuple> userDeque = tweets.get(user_id);
    if(userDeque == null) {
      //**** https://stackoverflow.com/questions/16821767/per-key-blocking-map-in-java
      userDeque = new LinkedBlockingDeque<>();
      BlockingDeque<TweetTuple> oldQueue = tweets.putIfAbsent(user_id,userDeque);
      if(oldQueue != null) {
        userDeque = oldQueue;
      }
    }
    Tweet tweet = Tweet.create(user_id,tweet_text);
    userDeque.addFirst(new TweetTuple(tweet, System.currentTimeMillis()));
    return tweet;
  }

  // @param user_id an integer
  // return a list of 10 new feeds recently
  // and sort by timeline
  public List<Tweet> getNewsFeed(int user_id) {

    // Write your code here
    Map<Integer,Iterator<TweetTuple>> userToIter = new HashMap<Integer,Iterator<TweetTuple>>();
    PriorityQueue<TweetTuple> pq = new java.util.PriorityQueue<>(1,new Comparator<TweetTuple>(){
      @Override
      public int compare(TweetTuple o1, TweetTuple o2) {
        return (int)(o2.timestamp - o1.timestamp);
      }
    });

    Set<Integer> newsToUserId = new HashSet<>();
    ConcurrentMap<Integer,Integer> toUsers = follows.get(user_id);
    if(toUsers != null) {
      newsToUserId.addAll(toUsers.keySet());
    }
    newsToUserId.add(user_id); //add the new feeds from the user it self
    for(Integer toUserId : newsToUserId) {
      BlockingDeque<TweetTuple> deque = tweets.get(toUserId);
      if(deque == null) {
        continue;
      }
      Iterator<TweetTuple> iter = deque.iterator();
      userToIter.put(toUserId,iter);
      if(iter.hasNext()) {
        pq.add(iter.next());
      }
    }
    List<Tweet> result = new ArrayList<Tweet>();

    for(int i = 0; i < 10; i++) {
      if(pq.size() == 0) {
        break;
      }
      TweetTuple tup = pq.poll();
      result.add(tup.tweet);

      Iterator<TweetTuple> iter = userToIter.get(tup.tweet.user_id);
      if(iter.hasNext()) {
        pq.offer(iter.next());
      }
    }
    return result;
  }

  // @param user_id an integer
  // return a list of 10 new posts recently
  // and sort by timeline
  public List<Tweet> getTimeline(int user_id) {
    BlockingDeque<TweetTuple> deque = tweets.get(user_id);
    if(deque == null) {
      return Collections.EMPTY_LIST;
    }
    List<Tweet> result = new ArrayList<Tweet>();
    int i = 0;
    for(Iterator<TweetTuple> iter = deque.iterator(); iter.hasNext() && i < 10; ++i) {
      result.add(iter.next().tweet);
    }
    return result;
  }

  // @param from_user_id an integer
  // @param to_user_id an integer
  // from user_id follows to_user_id
  public void follow(int from_user_id, int to_user_id) {
    // Write your code here
    ConcurrentMap<Integer,Integer> toUserSet = follows.get(from_user_id);
    if(toUserSet == null) {
      toUserSet = new ConcurrentHashMap<Integer,Integer>();
      ConcurrentMap<Integer,Integer> oldToUserSet = follows.putIfAbsent(from_user_id,toUserSet);
      if(oldToUserSet != null) {
        toUserSet = oldToUserSet;
      }
    }
    toUserSet.put(to_user_id,1);
  }

  // @param from_user_id an integer
  // @param to_user_id an integer
  // from user_id unfollows to_user_id
  public void unfollow(int from_user_id, int to_user_id) {
    // Write your code herel
    ConcurrentMap<Integer,Integer> toUserSet = follows.get(from_user_id);
    if(toUserSet == null) {
      return;
    }
    toUserSet.remove(to_user_id);
    if(toUserSet.size() == 0) {
      // *****https://docs.oracle.com/javase/7/docs/api/java/util/Map.html#equals(java.lang.Object)
      follows.remove(from_user_id,toUserSet);
    }
  }
}
class TweetTuple {
  Tweet tweet;
  long timestamp;
  public TweetTuple(Tweet tweet,long timestamp) {
    this.tweet = tweet;
    this.timestamp = tweet.id;
  }

}
class Tweet{
  public int id;
  public int user_id;
  public String text;

  private static AtomicInteger idSerial = new AtomicInteger(1);
  public String toString(){
    return String.valueOf(id);
  }
  public static Tweet create(int user_id, String tweet_text) {
    // This will create a new tweet object,
    // and auto fill id
    Tweet tw = new Tweet();
    tw.text = tweet_text;
    tw.user_id = user_id;
    tw.id = idSerial.getAndIncrement();
    return tw;
  }
}