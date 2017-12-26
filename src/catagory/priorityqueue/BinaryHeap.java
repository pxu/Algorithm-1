package catagory.priorityqueue;

import java.util.Arrays;

public class BinaryHeap {
  public boolean less(int[] nums, int i, int j) {
    return nums[i] < nums[j];
  }
  public void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
  public int swim(int[] nums, int k) {
    while (k != 0 && less(nums,(k - 1) / 2, k)) {
      swap(nums, k, (k - 1) / 2 );
      k = (k - 1) / 2;
    }
    return k;
  }
  public int sink(int[] nums, int N, int k) {
    while (k * 2 < N) {
      int j = k * 2;
      if (j + 1 < N && less(nums, j, j + 1)) {
        j = j + 1;
      }
      if (!less(nums, k, j)) {
        break;
      }
      swap(nums, j, k);
      k = j;
    }
    return k;
  }
  public void constructHeap(int[] nums) {
    for (int i = (nums.length - 1) / 2; i >= 0; i--) {
      sink(nums, nums.length, i);
    }
  }
  public void sort(int[] nums) {
    constructHeap(nums);
    for (int i = nums.length - 1; i > 0; i--) {
      swap(nums, 0, i);
      sink(nums, i, 0); // not len
    }

  }
  public static void main(String[] args) {
    int[] nums = new int[]{3,4,2,5,1,6,8,7,9,0};
    new BinaryHeap().sort(nums);
    System.out.println(Arrays.toString(nums));
  }
}
