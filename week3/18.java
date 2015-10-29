import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		int num = nums.length;
		int l = 0;// 定义左右指针，即采用夹逼法得出结果
		int r = 0;
		if (num < 3)
			return list;
		for (int x = 0; x < num - 3; x++) {
			if (x != 0 && nums[x - 1] == nums[x])
				continue;
			for (int i = x + 1; i < num - 2; i++) {
				if (i != x + 1 && nums[i] == nums[i - 1]) {
					continue;
				}
				l = i + 1;
				r = num - 1;
				while (l < r) {
					if (l > i + 1 && nums[l] == nums[l - 1]) {
						l = l + 1;
						continue;
					}
					if (r < num - 2 && nums[r + 1] == nums[r]) {
						r = r - 1;
						continue;
					}
					if (nums[i] + nums[l] + nums[r] + nums[x] == target) {
						List<Integer> templist = new ArrayList<>();
						templist.add(nums[x]);
						templist.add(nums[i]);
						templist.add(nums[l]);
						templist.add(nums[r]);
						list.add(templist);
						l = l + 1;
						r = r - 1;// 分别向左右移动，继续判断
					} else if (nums[i] + nums[l] + nums[r] + nums[x] < target) {// 如果相加结果小于0，则左边指针向右移动
						l = l + 1;
					} else
						r = r - 1;
				}

			}
		}
		return list;
	}
}