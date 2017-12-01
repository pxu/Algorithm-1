package leetcode;

/**
 * Created by zhahua on 10/7/17.
 */
public class maxProfitIV188 {
  public static void main(String[] args) {
    //int stock[] = new int[]{1,2};
    int stock[] = new int[]{6,2,4,8,10,5,7,9,10};
    new maxProfitIV188().maxProfit(3,stock);
  }
  public int maxProfit(int k, int[] prices) {
    int sell[] = new int[k + 1];
    int buy[] = new int[k + 1];

    for(int j = 0; j < prices.length; j++) {
      for(int i =0; i <= k; i++) {
        if(j == 0) {
          sell[i] = 0;
          if(i == 0) {
            buy[i] = Integer.MIN_VALUE;
          }else {
            buy[i] = -prices[0];
          }
          continue;
        }
        int newBuy;
        int newSell;
        if(i == 0) {
          newBuy = Integer.MIN_VALUE;
        }else {
          newBuy = Math.max(buy[i], sell[i - 1] - prices[j]);
        }
        if(i == 0) {
          newSell = 0;
        }else {
          newSell = Math.max(sell[i], buy[i] + prices[j]);
        }
        buy[i] = newBuy;
        sell[i] = newSell;
        System.out.print(newSell + "\t");
      }
      System.out.println();
    }

    return sell[k];
  }
}
