public class Solution {
    public void moveZeroes(int[] nums) {
        int j=0;//这个算法就是去记录已经走过的路径中出现0的次数，这样直接向前寻找j个互换就能得到想要的结果
        for(int i=0;i<nums.length;i++){
            if (nums[i]==0)
            j++;
            else {
            nums[i-j]=nums[i];
            if(j>0)//开始时没有判断没有出现0 的情况所以导致出错
            nums[i]=0;
            }
        }
    }
}