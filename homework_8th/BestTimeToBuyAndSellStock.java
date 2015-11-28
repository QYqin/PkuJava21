public class Solution {
    public int maxProfit(int[] prices) {
        /*int min=prices[0];
		 int max=prices[0];
		 for(int i=1;i<prices.length;i++){
			 if(prices[i]>max)
			 {
				 max=prices[i];
			 }
			 if(prices[i-1]<min){
				 min=prices[i-1];
			 }
		 }
			return max-min;*/
		if(prices==null||prices.length==0){
			 return 0;
		 }
		 int minnum=prices[0];
		 int maxpro=0;
		 for(int i=1;i<prices.length;i++){
			 minnum=Math.min(minnum, prices[i]);
			 maxpro=Math.max(maxpro, prices[i]-minnum);
		 }
		 return maxpro;
    }
}