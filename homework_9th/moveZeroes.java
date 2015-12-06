public class Solution {
    public void moveZeroes(int[] nums) {
        int count=nums.length-1;
        int totalnum=nums.length;
        int j=0,i=0;
        /*算法思想，如果i为0，之后的所以数字下标前移。并至最后一位为0，i不用遍历最后置0的那位。
        如果i不为零，i++*/
        while(i<(count+1)){
            if(nums[i]==0){
                for(j=i;j<nums.length-1;j++){
                    nums[j]=nums[j+1];
                }
                nums[count]=0;
                count--;
            }else{
                i++;
            }
        }
        return ;
    }
}