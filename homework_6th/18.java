public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
         List<List<Integer>> rst = new ArrayList<>();
    	if (nums == null||nums.length<4){
        	return rst;
        }
    	Arrays.sort(nums);
        int num = nums.length;
		int left = 0;
		int right = 0;
		for (int x = 0; x < num - 3; x++) {
			if (x != 0 && nums[x - 1] == nums[x])
				continue;
			for (int i = x + 1; i < num - 2; i++) {
				if (i != x + 1 && nums[i] == nums[i - 1]) {
					continue;
				}
				left= i + 1;
				right = num - 1;
				while (left < right ) {
					if (left > i + 1 && nums[left] == nums[left - 1]) {
						left = left + 1;
						continue;
					}
					if (right < num - 2 && nums[right + 1] == nums[right]) {
						right = right- 1;
						continue;
					}
					if (nums[i] + nums[left] + nums[right] + nums[x] == target) {
						List<Integer> templist = new ArrayList<>();
						templist.add(nums[x]);
						templist.add(nums[i]);
						templist.add(nums[left]);
						templist.add(nums[right]);
						rst.add(templist);
						left = left + 1;
						right = right - 1;
					} else if (nums[i] + nums[left] + nums[right] + nums[x] < target) {
						left = left + 1;
					} else
						right = right - 1;
				}

			}
		}
		return rst;
    }
}
