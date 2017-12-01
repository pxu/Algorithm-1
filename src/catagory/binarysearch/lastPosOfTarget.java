package catagory.binarysearch;

/**
 * Created by zhahua on 3/25/17.
 * 九章二分法第一题
 */
public class lastPosOfTarget {
  public static void main() {

  }

  public int lastPosition(int[] nums, int target) {
    if(nums == null || nums.length == 0) {
      return -1;
    }

    int start = 0, end = nums.length-1;

    while(start + 1 < end) {
      int mid = start + (end - start) / 2;

      if(nums[mid] == target) {
        start = mid;
      }else if(nums[mid] < target) {
        start = mid;
      }else {
        end = mid;
      }
    }

    if(nums[end] == target) {
      return end;
    }
    if(nums[start] == target) {
      return start;
    }
    return -1;
  }
}
