public class Solution {
    public int singleNumber(int[] nums) {
		/*
    	int i ;
    	if(nums.length<0){
			return 0;
		}
		for( i=0;i<nums.length-1;i++){
			for(int j=i+1;j<nums.length;j++){
				if(nums[i]==nums[j]){
					break;
				}
				else{
					continue;
				}
			}
			//int y = nums[i];
		
		}
		return nums[i];
		//return i;
		 */
    	if(nums.length<1){
    		return 0;
    	}
		int x = nums[0];//将第一个数组定义为初始值
		for(int i=1;i<nums.length;i++){
			x=x^nums[i];//b=b^a^a
		}
    	return x;
    }
}