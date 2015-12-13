public class Solution {
    public int countPrimes(int n) {
    	if(n<2){
			return 0;
		}
		int[] count =new int[n];
		for(int i=0;i<n;i++){
			count[i]=0;
		}
		for(int i=2;i<Math.sqrt(n);i++){
			for(int j=2;j*i<n;j++){
				count[i*j]=1;
			}
		}
		int k=0;
		for(int i=2;i<n;i++){
			if(count[i]==0){
				k++;
			}
		}
        return k;
    }
}