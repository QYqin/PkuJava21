public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
       	List<List<Integer>> Res =new ArrayList<List<Integer>>();
		if(nums.length<3){
			return Res;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length-2; ++i) 
		{
			//去掉重复项
			if(i!=0&&nums[i]==nums[i-1])
			{
				continue;
			}
			int p=i+1;
			int q=nums.length-1;
			int sum=0;
			while(p<q){
				sum=nums[i]+nums[p]+nums[q];
				if(sum==0)
				{
					List<Integer> newRes = new ArrayList<Integer>();
					newRes.add(nums[i]);
					newRes.add(nums[p]);
					newRes.add(nums[q]);
					Res.add(newRes);
					//去掉重复项
					while(++p<q&&nums[p]==nums[p-1]);
					while(p<--q&&nums[q]==nums[q+1]);
				}else if(sum<0){
					p++;//和太小，向前移动
				}else
				{
					q--;//和太大，向后移动
				}
				//如果不等于零，继续查找，
				
			}
		}
		/*
			public static int[] InertSort(int[] sortnum)
	 {
		 int[] resnum= null;
		 int index=0;
		 //设置一个新的数组，一般排序后返回并去掉重复项；
		 resnum[index]=sortnum[0];
		 for(int i=1;i<sortnum.length;i++)
		 {
			 //去掉重复值
			 if(sortnum[i]==sortnum[i-1])
			 {
				 continue;
			 }
			 resnum[++index]=sortnum[i];
			 int temp=resnum[index];
			 for(int j=index;j>0&&temp<resnum[j-1];j++){				 
			 }
			 //resnum[i]=
		
		 }
		int temp;
		for(int i=1;i<sortnum.length;++i)
		{
			temp=sortnum[i];
			int j;
			for(j=i;j>0&&temp<sortnum[j-1];--j)
			{
				sortnum[j]=sortnum[j-1];
			}
			sortnum[j]=temp;
		}
		 return sortnum;
	 }
		*/
		return Res;
	}
}