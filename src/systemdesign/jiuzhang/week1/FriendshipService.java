package systemdesign.jiuzhang.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by zhahua on 7/15/17.
 * http://www.lintcode.com/en/problem/friendship-service/#
 */
public class FriendshipService {

  ConcurrentMap<Integer,ConcurrentMap<Integer,Integer>> fromToUser = new ConcurrentHashMap<>();
  ConcurrentMap<Integer,ConcurrentMap<Integer,Integer>> toFromUser = new ConcurrentHashMap<>();

  public static void main(String[] args) {
    new FriendshipService();
  }
  public FriendshipService() {
    // initialize your data structure here.
    follow(1, 3);
    getFollowers(1);
    getFollowings(3);
    follow(2, 3);
    getFollowings(3);
    unfollow(1, 3);
    getFollowings(3);
  }
  // @param user_id an integer
  // return all followers and sort by user_id
  public List<Integer> getFollowers(int user_id) {
    // Write your code here
    ConcurrentMap<Integer,Integer> toFromUserSet = toFromUser.get(user_id);
    if(toFromUserSet ==null) {
      return Collections.EMPTY_LIST;
    }
    List<Integer> result = new ArrayList(toFromUserSet.keySet());
    Collections.sort(result);
    return result;
  }

  // @param user_id an integer
  // return all followings and sort by user_id
  public List<Integer>  getFollowings(int user_id) {
    // Write your code here
    ConcurrentMap<Integer,Integer> fromToUserSet = fromToUser.get(user_id);
    if(fromToUserSet == null) {
      return Collections.EMPTY_LIST;
    }
    List<Integer> result = new ArrayList(fromToUserSet.keySet());
    Collections.sort(result);
    return result;
  }

  // @param from_user_id an integer
  // @param to_user_id an integer
  // from user_id follows to_user_id
  public void follow(int to_user_id, int from_user_id) {
    // Write your code here
    ConcurrentMap<Integer,Integer> fromToUserSet = fromToUser.get(from_user_id);
    if(fromToUserSet == null) {
      fromToUserSet = new ConcurrentHashMap<Integer,Integer>();
      ConcurrentMap<Integer,Integer> oldSet = fromToUser.putIfAbsent(from_user_id,fromToUserSet);
      if(oldSet != null) {
        fromToUserSet = oldSet;
      }
    }
    fromToUserSet.put(to_user_id,1);

    ConcurrentMap<Integer,Integer> toFromUserSet = toFromUser.get(to_user_id);
    if(toFromUserSet == null) {
      toFromUserSet = new ConcurrentHashMap<Integer,Integer>();
      ConcurrentMap<Integer,Integer> oldSet = toFromUser.putIfAbsent(to_user_id,toFromUserSet);
      if(oldSet != null) {
        toFromUserSet = oldSet;
      }
    }
    toFromUserSet.put(from_user_id,1);

  }

  // @param from_user_id an integer
  // @param to_user_id an integer
  // from user_id unfollows to_user_id
  public void unfollow(int to_user_id, int from_user_id) {
    // Write your code here
    ConcurrentMap<Integer,Integer> fromToUserSet = fromToUser.get(from_user_id);
    if(fromToUserSet == null) {
      return;
    }
    fromToUserSet.remove(to_user_id);

    ConcurrentMap<Integer,Integer> toFromUserSet = toFromUser.get(to_user_id);
    if(toFromUserSet == null) {
      return;
    }
    toFromUserSet.remove(from_user_id);
  }
}