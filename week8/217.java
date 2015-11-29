import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> map = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (!map.contains(nums[i])) {
				map.add(nums[i]);
			} else
				return true;
		}
		return false;
	}
}