public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index=m+n-1;
		int i=m-1;//nums1
		int j=n-1;//nums2
		if(n==0){
			return;
		}
		//�Ӻ���ǰ���룬����˲�����Ƶ�����
        for(;i>=0&&j>=0;index--){
        	if(nums1[i]<=nums2[j]){
        		nums1[index]=nums2[j];
        		j--;
        	}else if(nums1[i]>nums2[j]){
        		nums1[index]=nums1[i];
        		i--;
        	}
        }
      /*//nums1��ʣ��
        while(i>=0){
        	nums1[index]=nums1[i];
        	i--;
        	index--;
        }*/
        //nums2��ʣ��
        while(j>=0){
        	nums1[index]=nums2[j];
        	j--;
        	index--;
        }

    }
}