package catagory.sort;

public class ThreeWayPartition {
  //https://leetcode.com/problems/sort-colors/description/
  //https://www.coursera.org/learn/algorithms-part1/lecture/XvjPd/duplicate-keys 8:35
  //https://algs4.cs.princeton.edu/lectures/23DemoPartitioning.pdf
  public void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
  public void sortColors(int[] nums) {
    int i = 0;
    while (i < nums.length && nums[i] != 1) {
      i++;
    }
    if (i != nums.length) {
      swap(nums, 0, i);
    }


    int v = nums[0];
    int lt = 0;
    i = 0;
    int gt = nums.length - 1;

    while (i <= gt) {
      if (nums[i] < v) {
        swap(nums, i++, lt++);
      } else if (nums[i] > v) {
        swap(nums, i, gt--);
      } else {
        i++;
      }
    }
  }
}
