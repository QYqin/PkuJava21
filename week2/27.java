public class Solution {
    public int removeElement(int[] nums, int val) {
        int length=nums.length;
		int end=nums.length-1;
		//设置一个数组尾变量，用于匹配的数与数组后面的数调换。
		//for(int i=0;i<nums.length;i++)
		for(int i=0;i<length;i++)
		{
			if(nums[i]==val)
			{
				nums[i]=nums[end];
				//如果匹配，将数组后面的赋值到前面
				i--;
				length--;
				end--;
			}
		}
		return length;
    }
}