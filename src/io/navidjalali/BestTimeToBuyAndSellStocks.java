package io.navidjalali;

/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
*/

public class BestTimeToBuyAndSellStocks {
  public int maxProfit(int[] prices) {

    int size = prices.length;

    if (size <= 1)
      return 0;

    int buy = 0;
    int maxProfit = 0;

    for(int sell = 1; sell < size; sell++) {
      int profit = prices[sell] - prices[buy];

      if (profit < 0) {
        buy = sell;
      }

      maxProfit = Math.max(profit, maxProfit);
    }

    return maxProfit;
  }
}
