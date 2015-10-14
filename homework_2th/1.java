public class Solution {
    public int[] twoSum(int[] nums, int target) {
         if(nums== null || nums.length < 2) {
	    		return null;
	    }
        HashMap<Integer,Integer> hash =new HashMap<Integer,Integer>();
		//使用HashMap函数，可以将给定的数存入hash表，并输他的位置。
		int m=0;
		int[] index =new int[2];
		index[0]=0;
		index[1]=0;
		//将所有得整数存入hash表，便于匹配
		for(int i=0;i<nums.length;i++)
		{
			hash.put(nums[i],i);
		}
		for(int i=0;i<nums.length;i++)
		{
				m=target-nums[i];
				//如果两个数相加等于VAL，则对于任意一个数的差应存于数组中
				if(hash.containsKey(m))
				{
					index[0]=i+1;
					index[1]=hash.get(m)+1;
					//将匹配的数组放入index中。
//					System.out.println(i+1); 
//					System.out.println(hash.get(m)+1); 
					if(index[0]<index[1])
					{
						return index;
						//只返回第一个序号比第二个序号小的组合即可，避免重复
					}
				}
		}
		//return null;
		return index; 
	    }
}