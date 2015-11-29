public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length==0||prices.length==1) return 0;
    	int top=prices[0];
    	int [] b= new int[prices.length];
        for(int i=0;i<prices.length;i++){
        	if(prices[i]-top>=0)
        		b[i]=prices[i]-top;
        	else {
				top=prices[i];
			}
        }
        Arrays.sort(b);
       return b[b.length-1];
        
    }
}