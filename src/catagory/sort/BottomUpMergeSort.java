package catagory.sort;

import java.util.Arrays;

//https://www.coursera.org/learn/algorithms-part1/lecture/PWNEl/bottom-up-mergesort
public class BottomUpMergeSort {
  public static void main(String[] args) {
//    Integer[] array = new Integer[]{2,5,3,1,4,7,6,9,8,0};
//    new BottomUpMergeSort().sort(array);
//    System.out.println(Arrays.toString(array));
  }
  public void sort(Comparable[] array) {
    Comparable[] aux = new Comparable[array.length];
    for (int sz = 1; sz < array.length; sz <<=1) {
      for (int lo = 0; lo < array.length - sz; lo += 2 * sz) {
        System.out.println(String.format("sz = %d, lo = %d, lob = %d", sz, lo, array.length - sz));
        merge(array, aux, lo, Math.min(lo + sz * 2 - 1, array.length - 1), lo + sz);
      }
    }
  }
  private void merge(Comparable[] array, Comparable[] aux, int lo, int hi, int mid) {
    int k = lo;
    int i = lo;
    int j = mid;
    while (i != mid && j <= hi) {
      int cmp = 1;//array[i].compareTo(array[j]);
      if(cmp < 0) {
        aux[k++] = array[i++];
      }else {
        aux[k++] = array[j++];
      }
    }
    while (i != mid) {
      aux[k++] = array[i++];
    }
    while (j != hi + 1) {
      aux[k++] = array[j++];
    }
    for(i = lo; i <= hi; i++) {
      array[i] = aux[i];
    }
  }
}
