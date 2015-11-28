public class Solution {
    public int removeDuplicates(int[] nums) {
       	 int start=0;
		 if (nums == null || nums.length == 0){
			 return 0; 
		 }
		 for(int i=1;i<nums.length;i++){
			 if(nums[i]!=nums[start]){
				 nums[start+1]=nums[i];
				 start++;
			 }
		 }
		return ++start;
    }
}