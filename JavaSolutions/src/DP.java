/**
 * Created by Scott on 01/10/2016.
 */
public class DP {















































    /**
     * 392. Is Subsequence
     * Medium, Dynamic Programming
     * https://leetcode.com/problems/is-subsequence/
     * */
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.isEmpty()) return true;
        if (t == null || t.isEmpty()) return false;
        int compareIndex = 0;
        int compareChar = s.charAt(compareIndex);
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == compareChar) {
                compareIndex++;
                if (compareIndex == s.length()) return true;
                compareChar = s.charAt(compareIndex);
            }
        }
        return false;
    }

    /**
     * 213. House Robber II
     * Medium, Dynamic Programming
     * https://leetcode.com/problems/house-robber-ii/
     * */
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return  Math.max(subRob(nums, 1, nums.length - 1), subRob(nums, 0, nums.length - 2));
    }
    public int subRob(int[] nums, int low, int high) {
        int include = 0, exclude = 0;
        for (int j = low; j <= high; j++) {
            int i = include, e = exclude;
            include = e + nums[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
    public void testSubRob(){
        int []test = {2,1,1,1};
        int ret = subRob(test, 0, 2);
    }





















































}
