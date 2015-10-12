public class Solution {
	public static void rotate(int[] nums, int k) {
		int temp = 0;
//		while (k>0){
//			temp = nums[nums.length-1];
//			for (int i = nums.length-1; i > 0; i--) {
//				nums[i]=nums[i-1];
//			}
//			nums[0]= temp;
//			k--;
//		} /*Time limited*/
//算法三次交换数组

		for (int i = 0; i < nums.length/2; i++) {
			temp= nums[i];
			nums[i]=nums[nums.length-i-1];
			nums[nums.length-i-1] =temp;
		}        
		if (nums.length<k)
        k=k-nums.length;
		for (int i = 0; i < k/2; i++) {
			temp= nums[i];
			nums[i]=nums[k-i-1];
			nums[k-i-1] =temp;
		}
		for (int i = k; i < (k+nums.length)/2; i++) {
			temp= nums[i];
			nums[i]=nums[nums.length+k-i-1];
			nums[nums.length+k-i-1] =temp;
		}
    }
}