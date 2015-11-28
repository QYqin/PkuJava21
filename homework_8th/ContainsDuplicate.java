public class Solution {
    public boolean containsDuplicate(int[] nums) {
        /*if (nums == null || nums.length == 0){
			 return false; 
		 }
        int maxnumber=nums.length-1;
        for(int i=0;i<nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                if(nums[i]==nums[j])
                {
                    return true;
                }
            }
        }*/
        if (nums == null || nums.length == 0){
			 return false; 
		 }
		Set<Integer> numline=new HashSet<>();
		for(int i=0;i<nums.length;i++){
			if(numline.contains(nums[i])){
				return true;
			}else{
				numline.add(nums[i]);
			}
		}
        return false;
    }
}