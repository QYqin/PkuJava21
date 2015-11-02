public class Solution {
    public int majorityElement(int[] nums) {
     /* int Element=nums[0],MajorElement=nums[0];
		int count=0,maxcount=0;
		for(int j=0;j<nums.length/2;j++){
			Element=nums[j];
			for (int i = 0; i < nums.length; i++) {
				if (nums[i]== Element) {
					count++;
				}
				if(count>(nums.length)/2){
				return Element;
				}
			}
			if(count>maxcount){
				MajorElement=Element;
			}
			count=0;
			j=j+num.length/5；
		}*/
		//时间超时，只能遍历一次，可以使用一个计数，因为Major大于二分之一N，所以count加减后输出的一定为MajorElement
		int MajorElement=nums[0];
		int count=0;
		for(int j=0;j<nums.length;j++)
		{
		    if(count==0)
		    MajorElement=nums[j];
		    if(nums[j]==MajorElement)
		    {
		        count++;
		    }else{
		        count--;
		    }
		}
		return MajorElement;
    }
}