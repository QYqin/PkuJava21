public class Solution {
	public int maxProfit(int[] prices) {
		if (prices.length < 2)
			return 0;
		int profit = 0;// 当前差价
		int cur_min = prices[0];// 当前最低价
		for (int i = 1; i < prices.length; i++) {
			if ((prices[i] - cur_min) >= profit) {
				profit = prices[i] - cur_min;
			} else if (prices[i] <= cur_min)
				cur_min = prices[i];
		}
		return profit;
	}
}