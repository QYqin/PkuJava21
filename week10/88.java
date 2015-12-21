public class Solution {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int x = m - 1;
		int y = n - 1;
		int z = m + n - 1;
		while (x >= 0 && y >= 0) {
			nums1[z--] = nums1[x] >= nums2[y] ? nums1[x--] : nums2[y--];
		}
		while (y >= 0) {
			nums1[z--] = nums2[y--];
		}
//		while (x >= 0) {
//			nums1[z--] = nums1[x--];
//		}//不需要判断nums1，因为本身就在数组中，是从后往前添加
	}
}