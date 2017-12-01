package leetcode;

/**
 * Created by zhahua on 10/5/17.
 */
public class maxProfit121 {
  public static void main(String []args) {
    int[] data = new int[]{7, 6, 4, 3, 1};
    int result = new maxProfit121().maxProfit(data);
  }
  public int maxProfit(int[] prices) {
    if(prices == null || prices.length <= 1) {
      return 0;
    }
    int minValue = prices[0];
    int maxProfit = 0;
    for(int i = 1; i < prices.length; i++) {
      if(prices[i - 1] < minValue) {
        minValue = prices[i - 1];
      }
      int profit = prices[i] - minValue;
      if(profit > maxProfit) {
        maxProfit = profit;
      }
    }
    return maxProfit;
  }
}
