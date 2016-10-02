import com.sun.glass.ui.SystemClipboard;
import com.sun.jmx.remote.internal.ArrayQueue;
import sun.plugin.net.protocol.jar.CachedJarURLConnection;

import java.util.*;

/**
 * Created by Scott on 1/30/16.
 */
public class ArrayEasy {
    /**
     * 217. Contains Duplicate
     * Array, Easy
     * https://leetcode.com/problems/contains-duplicate/
     * */
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) return false;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    /**
     * 66. Plus One
     * Array, Easy
     * https://leetcode.com/problems/plus-one/
     * */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length < 1) return new int[]{1};
        int cursor = digits.length - 1;
        digits[cursor]++;
        while (cursor > 0 && digits[cursor] > 9) {
            digits[cursor] = 0;
            digits[cursor - 1]++;
            cursor--;
        }
        if (cursor == 0 && digits[cursor] > 9) {
            digits[cursor] = 0;
            int [] newArray = new int[digits.length + 1];
            newArray[0] = 1;
            for (int i = 1; i <= digits.length; i++) {
                newArray[i] = digits[i - 1];
            }
            return newArray;
        }
        return digits;
    }

    /**
     * 189. Rotate Array
     * Array, Easy
     * https://leetcode.com/problems/rotate-array/
     * */
    public void rotate(int[] nums, int k) {
        if (k < 1 || nums == null || nums.length < 2) return;
        k = k%nums.length;

        int [] tempNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int tempIndex = i + k;
            tempNums[tempIndex >= nums.length ? tempIndex - nums.length : tempIndex] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = tempNums[i];
        }
    }

    /**
     * 27. Remove Element
     * Array, Easy
     * https://leetcode.com/problems/remove-element/
     * */
    public int removeElement(int[] A, int elem) {
        int m = 0;
        for(int i = 0; i < A.length; i++){

            if(A[i] != elem){
                A[m] = A[i];
                m++;
            }
        }

        return m;
    }

    /**
     * 219. Contains Duplicate II
     * Array, Easy
     * https://leetcode.com/problems/contains-duplicate-ii/
     * */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) numSet.remove(nums[i - k - 1]);
            if (!numSet.add(nums[i])) return true;
        }
        return false;
    }

    /**
     * 26. Remove Duplicates from Sorted Array
     * Array, Easy
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
     * */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int currentIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[currentIndex]) {
                nums[++currentIndex] = nums[i];
            }
        }
        return currentIndex + 1;
    }

    /**
     * 118. Pascal's Triangle
     * Array, Easy
     * https://leetcode.com/problems/pascals-triangle/
     * */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (numRows < 1) return result;

        for (int i = 0; i < numRows; i++) {
            int numOfElementsInThisRow = i + 1;
            List<Integer> elementsInThisRow = new ArrayList<Integer>();
            for (int n = 0; n < numOfElementsInThisRow; n++) {
                int left = 0, right = 0;
                int parentLevel = i - 1;
                int parentX = n - 1;
                int parentY = n;
                if (parentLevel >= 0 && parentLevel < result.size() && parentX >=0 && parentX < result.get(parentLevel).size()) {
                    left = result.get(parentLevel).get(parentX);
                }
                if (parentLevel >= 0 && parentLevel < result.size() && parentY >=0 && parentY < result.get(parentLevel).size()) {
                    right = result.get(parentLevel).get(parentY);
                }
                if (i == 0) {
                    left++;
                }
                elementsInThisRow.add(left + right);
            }
            result.add(elementsInThisRow);
        }
        return result;
    }

    /**
     * 119. Pascal's Triangle II
     * Array, Easy
     * https://leetcode.com/problems/pascals-triangle-ii/
     * */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();
        if (rowIndex < 0) return result;
        for (int i = 0; i < rowIndex + 1; i++) {
            result.add(1);
            for (int j = result.size() - 2; j > 0; j--) {
                result.set(j, result.get(j - 1) + result.get(j));
            }
        }
        return result;
    }

    public int conbination(int up, int down) {
        if (up < 1 || down < 1 || up > down) return -1;
        return factorial(down) / (factorial(up) * factorial(down - up));
    }

    public int factorial(int num) {
        if (num <= 1) return 1;
        return num * factorial(num - 1);
    }

    /**
     * 1. Two Sum
     * Array, Easy
     * https://leetcode.com/problems/two-sum/
     * */
    public int[] twoSum(int[] nums, int target) {
        // O(n2) solution
//        int result[] = new int[2];
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    result[0] = i;
//                    result[1] = j;
//                    return result;
//                }
//            }
//        }
//        return result;
        if (nums.length < 2) return null;
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();// Store [gap to the target, index]
        for (int i = 0; i < nums.length; i++) {
            if (hashmap.containsKey(nums[i])) {
                int []ret = {hashmap.get(nums[i]), i};
                return ret;
            } else {
                hashmap.put(target - nums[i], i);
            }
        }
        return null;
    }

    /**
     * 167. Two Sum II - Input array is sorted
     * Array, Medium
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
     * */
    public int[] twoSumII(int[] numbers, int target) {
        int[] indice = new int[2];
        if (numbers == null || numbers.length < 2) return indice;
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int v = numbers[left] + numbers[right];
            if (v == target) {
                indice[0] = left + 1;
                indice[1] = right + 1;
                break;
            } else if (v > target) {
                right --;
            } else {
                left ++;
            }
        }
        return indice;
    }

    /**
     * 15. 3Sum
     * Array, Medium
     * https://leetcode.com/problems/3sum/
     * */
    public List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        for (int i = 0; i < nums.length; i++) {
//            int target = 0 - nums[i];
//            HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
//            for (int j = 0; j < nums.length; j++) {
//                if (j == i) continue;
//                if (hashMap.containsKey(nums[j])) {
//                    //Found match
//                    Integer []match = {nums[i], nums[hashMap.get(nums[j])], nums[j]};
//                    Arrays.sort(match);
//                    boolean duplicated = false;
//                    for (List<Integer> list: ret) {
//                        if (list.get(0) == match[0] &&
//                                list.get(1) == match[1] &&
//                                list.get(2) == match[2])
//                            duplicated = true;
//                    }
//                    if (!duplicated) ret.add(Arrays.asList(match));
//                } else {
//                    hashMap.put(target - nums[j], j);
//                }
//            }
//        }
//        return ret;

        //Worst case O(n2) solution, best solution so far
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue; // to skip duplicate numbers; e.g [0,0,0,0]
            }
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum =  nums[i] + nums[start] + nums[end];
                if (sum == 0) {
                    Integer []match = {nums[i], nums[start], nums[end]};
                    ret.add(Arrays.asList(match));
                    end--;
                    while (start < end && nums[start] == nums[start - 1]) { // to skip duplicates
                        start++;
                    }
                    while (start < end && nums[end] == nums[end + 1]) { // to skip duplicates
                        end--;
                    }
                } else if (sum > 0) end--;
                else start++;
            }
        }
        return ret;
    }
    public void testThreeSum() {
//        int []test = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        int []test = {-1,0,1,2,-1,-4};
        System.out.println(fourSum(test, -1));
    }

    /**
     * 16. 3Sum Closest
     * Array, Medium
     * https://leetcode.com/problems/3sum-closest/
     * */
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return -1;
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;
            }
        }
        return result;
    }
    /**
     * 18. 4Sum
     * Array, Medium
     * https://leetcode.com/problems/4sum/
     * */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int start = j + 1, end = nums.length - 1;
                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        Integer []match = {nums[i] , nums[j] , nums[start] , nums[end]};
                        result.add(Arrays.asList(match));
                        start++;
                        end--;
                        while (start < end && nums[start] == nums[start - 1]) start++;
                        while (start < end && nums[end] == nums[end + 1]) end--;
                    } else if (sum > target) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }

        }
        return result;
    }

    /**
     * 31. Next Permutation
     * Array, Medium
     * https://leetcode.com/problems/next-permutation/
     * */
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) return;
        int i = nums.length - 1;
        while (i > 0 && nums[i] < nums[i - 1]) i--;
        if (i == 0) {
            //revert nums
            revertSubarray(nums, 0, nums.length - 1);
        } else {
            int j = nums.length - 1;
            while (nums[j] <= nums[i - 1]) j--;
            int temp = nums[i - 1];
            nums[i - 1] = nums[j];
            nums[j] = temp;
            revertSubarray(nums, i, nums.length - 1);
        }
    }
    public void revertSubarray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * 238. Product of Array Except Self
     * Array, Medium
     * https://leetcode.com/problems/product-of-array-except-self/
     * */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length < 1) return new int[0];
        int []ret = new int[nums.length];
        ret[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ret[i] = ret[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ret[i] = ret[i] * right;
            right = right * nums[i];
        }
        return ret;
    }

    /**
     * 11. Container With Most Water
     * Array, Medium
     * https://leetcode.com/problems/container-with-most-water/
     * */
    public int maxArea(int[] height) {
        // O(n2) solution
//        if (height.length < 2) return 0;
//        int max = 0;
//        for (int i = 0; i < height.length - 1; i++) {
//            for (int j = i + 1; j < height.length; j++) {
//                int area = (j - i) * Math.min(height[i], height[j]);
//                if (area > max) max = area;
//            }
//        }
//        return max;

        // O(n) solution
        if (height.length < 2) return 0;
        int max = 0;
        int start = 0, end = height.length - 1;
        while (start < end) {
            int area = Math.min(height[start], height[end]) * (end - start);
            if (area > max) max = area;
            if (height[start] > height[end]) end--;
            else start++;
        }
        return max;
    }
    public void testMaxArea() {
        int []test = {1,4,4};
        int ret = minSubArrayLen(4, test);
    }

    /**
     * 209. Minimum Size Subarray Sum
     * Array, Medium
     * https://leetcode.com/problems/minimum-size-subarray-sum/
     * */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length < 2) return 0;
        int start = 0, end = 0, minLen = Integer.MAX_VALUE, sum = 0;
        while (end < nums.length) {
            while (end < nums.length && sum < s) sum += nums[end++];
            if (sum < s) break;
            while (start < end && sum >= s) sum -= nums[start++];
            if (end - start + 1 < minLen) minLen = end - start + 1;
        }
        return minLen == Integer.MAX_VALUE? 0: minLen;
    }

    /**
     * 79. Word Search
     * Array, Medium
     * https://leetcode.com/problems/word-search/
     * */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length < 1 || board[0].length < 1) return false;
        if (word.length() < 1) return true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfsExist(board, i, j, word))
                    return true;
            }
        }
        return false;
    }
    public boolean dfsExist(char [][] board, int x, int y, String s) {
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0) return false;
        if (s.length() == 1) return s.charAt(0) == board[x][y];
        if (s.charAt(0) != board[x][y]) return false;
        String nextString = s.substring(1);
        board[x][y] ^= 256;
        boolean exist = dfsExist(board, x + 1, y, nextString) ||
                dfsExist(board, x, y + 1, nextString) ||
                dfsExist(board, x - 1, y, nextString) ||
                dfsExist(board, x, y - 1, nextString);
        board[x][y] ^= 256;
        return exist;
    }

    /**
     * 212. Word Search II
     * Array, Hard
     * https://leetcode.com/problems/word-search-ii/
     * */
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> ret = new HashSet<String>();
        TrieNode root = new TrieNode();
        for (String word: words) {
            buildTrie(word, root);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                StringBuilder retString = new StringBuilder();
                boolean isStringExist = dfsExist(board, i, j, root, retString);
                if (isStringExist) {
                    ret.add(retString.toString());
                }
            }
        }
        return new ArrayList<String>(ret);
    }
    public void buildTrie(String word, TrieNode root) {
        TrieNode loop = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            TrieNode nextNode = loop.hashmap.get(currentChar);
            if (nextNode == null) {
                nextNode = new TrieNode();
                loop.hashmap.put(currentChar, nextNode);
            }
            loop = nextNode;
        }
        loop.isEndOfWord = true;
    }
    public boolean dfsExist(char [][] board, int x, int y, TrieNode trie, StringBuilder retString) {
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0 || trie == null || !trie.hashmap.containsKey(board[x][y])) return false;
        if (trie.hashmap.containsKey(board[x][y])) {
            retString.append(board[x][y]);
            if (trie.isEndOfWord) return true;
        }
        board[x][y] ^= 256;
        boolean exist = dfsExist(board, x + 1, y, trie.hashmap.get(board[x][y]), retString) ||
                dfsExist(board, x, y + 1, trie.hashmap.get(board[x][y]), retString) ||
                dfsExist(board, x - 1, y, trie.hashmap.get(board[x][y]), retString) ||
                dfsExist(board, x, y - 1, trie.hashmap.get(board[x][y]), retString);
        board[x][y] ^= 256;
        return exist;
    }

    /**
     * 42. Trapping Rain Water
     * Array, Hard
     * https://leetcode.com/problems/trapping-rain-water/
     * */
    public int trap(int[] height) {
        int start = 0, end = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int ret = 0;
        while (start < end) {
            if (height[start] < height[end]) {
                if (height[start] < leftMax) {
                    ret += leftMax - height[start];
                } else {
                    leftMax = height[start];
                }
                start++;
            } else {
                if (height[end] < rightMax) {
                    ret += rightMax - height[end];
                } else {
                    rightMax = height[end];
                }
                end--;
            }
        }
        return ret;
    }

    /**
     * 56. Merge Intervals
     * Array, Hard
     * https://leetcode.com/problems/merge-intervals/
     * */
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null) return new ArrayList<Interval>();
        if (intervals.size() < 2) return intervals;
        List<Interval> ret = new ArrayList<Interval>();

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval: intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                ret.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        ret.add(new Interval(start, end));
        return ret;
    }

    /**
     * 4. Median of Two Sorted Arrays
     * Array, Hard
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if (totalLength % 2 == 0) {
            return (findKthSmallest(nums1, 0, nums2, 0, totalLength / 2) +
                    findKthSmallest(nums1, 0, nums2, 0, totalLength / 2 + 1)) / 2.0;
        } else {
            return findKthSmallest(nums1, 0, nums2, 0, totalLength / 2 + 1);
        }
    }
    public int findKthSmallest(int []A, int startA, int []B, int startB, int k) {
        if (startA >= A.length) return B[startB + k - 1];
        if (startB >= B.length) return A[startA + k - 1];
        if (k == 1) return Math.min(A[startA], B[startB]);
        int Akey = startA + k/2 - 1 < A.length? A[startA + k/2 - 1]: Integer.MAX_VALUE;
        int Bkey = startB + k/2 - 1 < B.length? B[startB + k/2 - 1]: Integer.MAX_VALUE;
        if (Akey < Bkey) {
            return findKthSmallest(A, startA + k/2, B, startB, k/2);
        } else {
            return findKthSmallest(A, startA, B, startB + k/2, k/2);
        }
    }

    /**
     * 283. Move Zeroes
     * Array, Easy
     * https://leetcode.com/problems/move-zeroes/
     * */
    public void moveZeroes(int[] nums) {
        /**Version 1*/
//        for (int i = nums.length - 1; i >= 0; i--) {
//            if (nums[i] == 0) {
//                for (int j = i + 1; j < nums.length; j++) {
//                    int temp = nums[j];
//                    nums[j] = nums[j - 1];
//                    nums[j - 1] = temp;
//                }
//            }
//        }

        /**Version 2*/
        int zeroCount = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                for (int j = i + 1; j < nums.length - zeroCount; j++) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
                zeroCount++;
            }
        }
    }

    /**
     * 88. Merge Sorted Array
     * Array, Easy
     * https://leetcode.com/problems/merge-sorted-array/
     * */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n < 1) return;
        if (m < 1) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        int[] num1Copy = Arrays.copyOf(nums1, m);
        int i = 0,j = 0, count = 0;
        while (true) {
            if (i >= m || j >= n) break;
            if (i < m && num1Copy[i] < nums2[j]) {
                nums1[count] = num1Copy[i];
                i++;
            } else if (j < n && num1Copy[i] >= nums2[j]) {
                nums1[count] = nums2[j];
                j++;
            }
            count++;
        }
        if (i == m) {
            while (j < n) {
                nums1[count] = nums2[j];
                j++;
                count++;
            }
        } else if (j == n) {
            while (i < m) {
                nums1[count] = num1Copy[i];
                i++;
                count++;
            }
        }
    }

    /**
     * 169. Majority Element
     * Array, Easy
     * https://leetcode.com/problems/majority-element/
     * */
    public int majorityElement(int[] nums) {
        if (nums.length < 1) return 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer integer: nums) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        int count = nums.length / 2 + 1;
        for (Integer integer: map.keySet()) {
            if (map.get(integer) > count) return integer;
        }
        return 0;
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     * Array, Easy
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * */
    public int maxProfit(int[] prices) {
        int maxProfit = 0, maxProfitSellingToday = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfitSellingToday = Math.max(0, maxProfitSellingToday + prices[i] - prices[i - 1]);
            maxProfit = Math.max(maxProfit, maxProfitSellingToday);
        }
        return  maxProfit;
    }

    /**
     * 48. Rotate Image
     * Array, Medium
     * https://leetcode.com/problems/rotate-image/
     * */
    public void rotate(int[][] matrix) {
        if (matrix.length < 2) return;
        int [][]ret = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ret[i][j] = matrix[matrix.length - j - 1][i];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = ret[i][j];
            }
        }
    }

    /**
     * 81. Search in Rotated Sorted Array II
     * Array, Medium, Binary Search
     * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
     * */
    public boolean search2(int[] nums, int target) {
        return false;
    }

    /**
     * 81. Search in Rotated Sorted Array
     * Array, Hard, Binary Search
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
     * */
    public int search(int[] nums, int target) {
        // Normal O(n) solution
//        for (int i = 0; i < nums.length; i++) {
//            if (target == nums[i]) return i;
//        }
//        return -1;

        // O(lgn) binary search solution
        int low = 0;
        int high = nums.length - 1;
        if (high < low) return -1;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[low]) {
                if (target < nums[mid] && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    public void testSearch(){
        int []test = {1, 3};
        System.out.println(search(test, 3));
    }

    /**
     * 363. Max Sum of Rectangle No Larger Than K
     * Array, Hard, Binary Search
     * https://leetcode.com/problems/max-sum-of-sub-matrix-no-larger-than-k/
     * */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length < 1 || matrix[0].length < 1) return 0;
        int row = matrix.length, col = matrix[0].length, max = Integer.MIN_VALUE;
        int low = row > col? col: row;
        int high = row > col? row: col;
        for (int left = 0; left < low; left++) {
            int []array = new int[high];
            for (int right = left; right < low; right++) {
                int sum = 0;
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(0);
                for (int i = 0; i < high; i++) {
                    array[i] += row > col? matrix[i][right]: matrix[right][i];
                    sum += array[i];
                    Integer preSum = set.ceiling(sum - k);
                    if (preSum != null) {
                        max = Math.max(max, sum - preSum);
                    }
                    set.add(sum);
                }
            }
        }
        return max;
    }
    public void testMaxSumSubmatrix() {
        int [][] array = {{1,0,1}, {0,-2,3}};
//        int [][] array = {{2,2,-1}};
        int ret = maxSumSubmatrix(array, 2);

        System.out.println(ret);
    }

    /**
     * 62. Unique Paths
     * Array, Medium, Dynamic Programming
     * https://leetcode.com/problems/unique-paths/
     * */
    public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) return 0;
        int [][]mem = new int[m][n];
        mem[0][0] = 0;
        for (int i = 0; i < m; i++) {
            mem[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            mem[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                mem[i][j] = mem[i - 1][j] + mem[i][j - 1];
            }
        }
        return mem[m - 1][n - 1];
    }

    /**
     * 63. Unique Paths II
     * Array, Medium, Dynamic Programming
     * https://leetcode.com/problems/unique-paths-ii/
     * */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length < 1 || obstacleGrid[0].length < 1) return 0;
        int [][]mem = new int[obstacleGrid.length][obstacleGrid[0].length];
        boolean hasFoundObstacle = false;
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] == 1) hasFoundObstacle = true;
            mem[i][0] = hasFoundObstacle? 0: 1;
        }
        hasFoundObstacle = false;
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if (obstacleGrid[0][i] == 1) hasFoundObstacle = true;
            mem[0][i] = hasFoundObstacle? 0: 1;
        }
        for (int i = 1; i < obstacleGrid.length ; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) mem[i][j] = 0;
                else mem[i][j] = mem[i - 1][j] + mem[i][j - 1];
            }
        }
        return mem[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    /**
     * 162. Find Peak Element
     * Array, Medium, Binary Search
     * https://leetcode.com/problems/find-peak-element/
     * */
    public int findPeakElement(int[] nums) {
        if (nums.length < 1) return -1;
        if (nums.length == 1) return 0;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (end + start) / 2;
            long midLeftValue = mid > 0? nums[mid - 1]: Long.MIN_VALUE;
            long midRightValue = mid < nums.length - 1? nums[mid + 1]: Long.MIN_VALUE;
            if (nums[mid] > midLeftValue && nums[mid] > midRightValue) return mid;
            else if (nums[mid] > midLeftValue) start = mid + 1;
            else end = mid - 1; //Handle both when mid > right and mid is valley
        }
        return -1;
    }
    public void testFindPeakElement() {
        int []test = {1,2,3,1};
        System.out.println(findPeakElement(test));
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     * Array, Medium, Binary Search
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
     * */
    public int findMin(int[] nums) {
        if (nums==null || nums.length==0) { return Integer.MIN_VALUE; }
        int left = 0, right = nums.length-1;
        while (left < right-1) {  // while (left < right-1) is a useful technique
            int mid = left + (right-left)/2;
            if (nums[mid] > nums[right]) { left = mid; }
            else { right = mid; }
        }
        if (nums[left] > nums[right]) { return nums[right]; }
        return nums[left];
    }

    /**
     * 74. Search a 2D Matrix
     * Array, Medium, Binary Search
     * https://leetcode.com/problems/search-a-2d-matrix/
     * */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length < 1) return false;
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m - 1;
        while (start < end) {

        }
        return false;
    }

    /**
     * 152. Maximum Product Subarray
     * Array, Medium, Dynamic Programming
     * https://leetcode.com/problems/maximum-product-subarray/
     * */
    public int maxProduct(int[] nums) {
        if (nums.length < 1) return 0;
        int []memMax = new int[nums.length];
        int []memMin = new int[nums.length];
        memMax[0] = nums[0];
        memMin[0] = nums[0];
        int maxSoFar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            memMax[i] = Math.max(Math.max(memMax[i - 1] * nums[i], memMin[i - 1] * nums[i]), nums[i]);
            memMin[i] = Math.min(Math.min(memMax[i - 1] * nums[i], memMin[i - 1] * nums[i]), nums[i]);
            maxSoFar = Math.max(maxSoFar, memMax[i]);
        }
        return maxSoFar;
    }

    /**
     * 64. Minimum Path Sum
     * Array, Medium, Dynamic Programming
     * https://leetcode.com/problems/minimum-path-sum/
     * */
    public int minPathSum(int[][] grid) {
        if (grid.length < 1 || grid[0].length < 1) return 0;
        if(grid.length == 1 && grid[0].length == 1) return grid[0][0];
        int [][]mem = new int[grid.length][grid[0].length];
        mem[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            mem[i][0] = mem[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            mem[0][i] = mem[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                mem[i][j] = Math.min(mem[i - 1][j], mem[i][j - 1]) + grid[i][j];
            }
        }
        return mem[grid.length - 1][grid[0].length - 1];
    }
    public void testMinPathSum() {
        int [][] grid = {{1,2}, {1,1}};
        System.out.println(minPathSum(grid));
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode test() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.next = three;
        two.next = three;
        three.next = four;
        return getIntersectionNode(one, two);
    }

    /**
     * 24. Swap Nodes in Pairs
     * Linked List, Easy
     * https://leetcode.com/problems/swap-nodes-in-pairs/
     *
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     * */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pointer = head.next;
        head.next = swapPairs(head.next.next);
        pointer.next = head;
        return pointer;
    }

    /**
     * 234. Palindrome Linked List
     * Linked List, Easy
     * https://leetcode.com/problems/palindrome-linked-list/
     * */
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        return isPalindromeArray(arrayList);
    }

    public boolean isPalindromeArray(ArrayList<Integer> arrayList) {
        if (arrayList.size() < 1) return true;
        for (int i = 0; i < arrayList.size() / 2 ; i++) {
            Integer left = arrayList.get(i);
            Integer right = arrayList.get(arrayList.size() - i - 1);
            if ( left.intValue() != right.intValue() ) {
                return false;
            }
        }
        return true;
    }

    /**
     * 19. Remove Nth Node From End of List
     * Linked List, Easy
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ArrayList<ListNode> arrayList = new ArrayList<ListNode>();
        while (head != null) {
            arrayList.add(head);
            head = head.next;
        }
        if (arrayList.size() < 2) return null;
        if (n == arrayList.size()) return arrayList.get(1);
        if (n == 1) {
            arrayList.remove(arrayList.size() - 1);
            arrayList.get(arrayList.size() - 1).next = null;
        } else {
            arrayList.get(arrayList.size() - n - 1).next = arrayList.get(arrayList.size() - n + 1);
        }
        return arrayList.get(0);
    }

    /**
     * 21. Merge Two Sorted Lists
     * Linked List, Easy
     * https://leetcode.com/problems/merge-two-sorted-lists/
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode current = head;

        while (true) {
            if (l1 != null && l2 != null) {
                if (l1.val > l2.val) {
                    current.next = l2;
                    l2 = l2.next;
                } else {
                    current.next = l1;
                    l1 = l1.next;
                }
            } else if (l1 == null && l2 != null) {
                current.next = l2;
                l2 = l2.next;
            } else if (l2 == null && l1 != null) {
                current.next = l1;
                l1 = l1.next;
            } else {
                break;
            }
            current = current.next;
        }
        return head.next;
    }

    /**
     * 83. Remove Duplicates from Sorted List
     * Linked List, Easy
     * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
     * */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode headNode = new ListNode(Integer.MIN_VALUE);
        headNode.next = head;
        ListNode current = headNode.next;
        while (current != null) {
            ListNode begin = current;
            while (current.next != null && current.val == current.next.val) {
                current = current.next;
            }
            begin.next = current.next;
            current = current.next;
        }
        return headNode.next;
    }

    /**
     * 203. Remove Linked List Elements
     * Linked List, Easy
     * https://leetcode.com/problems/remove-linked-list-elements/
     * */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode headNode = new ListNode(Integer.MIN_VALUE);
        headNode.next = head;
        ListNode current = headNode.next;
        ListNode pre = headNode;
        while (current != null) {
            if (current.val == val) {
                pre.next = current.next;
            } else {
                pre = current;
            }
            current = current.next;
        }
        return headNode.next;
    }

    /**
     * 206. Reverse Linked List
     * Linked List, Easy
     * https://leetcode.com/problems/reverse-linked-list/
     * */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pointer = head.next;
        ListNode pre = head;
        pre.next = null;
        ListNode cur = pointer;

        do {
            pointer = pointer.next;
            cur.next = pre;
            pre = cur;
            cur = pointer;
        } while (pointer != null);
        return pre;
    }

    /**
     * 160. Intersection of Two Linked Lists
     * Linked List, Easy
     * https://leetcode.com/problems/intersection-of-two-linked-lists/
     * */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null && headB == null) return null;
        if (headA == headB) return headA;
        ListNode aPointer = headA;
        ListNode bPointer = headB;
        Integer aCount = 0;
        Integer bCount = 0;
        while (aPointer != null) {
            aCount++;
            aPointer = aPointer.next;
        }
        while ( bPointer != null ) {
            bCount++;
            bPointer = bPointer.next;
        }
        aPointer = headA;
        bPointer = headB;
        if (aCount > bCount) {
            for (int i = 0; i < aCount - bCount; i++) {
                aPointer = aPointer.next;
            }
        } else {
            for (int i = 0; i < bCount - aCount; i++) {
                bPointer = bPointer.next;
            }
        }
        if (aPointer == bPointer && aPointer != null) return aPointer;
        while (aPointer != bPointer && aPointer != null && bPointer != null) {
            aPointer = aPointer.next;
            bPointer = bPointer.next;
            if (aPointer == bPointer) return aPointer;
        }
        return null;
    }

    /**
     * 237. Delete Node in a Linked List
     * Linked List, Easy
     * https://leetcode.com/problems/delete-node-in-a-linked-list/
     * */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 2. Add Two Numbers
     * Linked List, Medium
     * https://leetcode.com/problems/add-two-numbers/
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode ret = new ListNode(-1);
//        ListNode temp = ret;
//        boolean add = false;
//        while (l1 != null && l2 != null) {
//            int sum = l1.val + l2.val;
//            if (add) sum += 1;
//            ListNode current = new ListNode(sum % 10);
//            if (sum / 10 > 0) {
//                add = true;
//            } else add = false;
//            temp.next = current;
//            temp = current;
//            l1 = l1.next;
//            l2 = l2.next;
//        }
//        if (l1 == null && l2 == null) {
//            if (add) {
//                temp.next = new ListNode(1);
//            }
//            return ret.next;
//        }
//        ListNode rest = l1 != null? l1: l2;
//        if (add) rest.val += 1;
//        while (rest != null) {
//            temp.next = rest;
//            temp = rest;
//            rest = rest.next;
//        }
//        return ret.next;

        ListNode l1Loop = l1, l2Loop = l2;
        ListNode ret = new ListNode(0);
        ListNode retLoop = ret;
        int sum = 0;
        while (l1Loop != null || l2Loop != null) {
            sum /= 10;

            if (l1Loop != null) {
                sum += l1Loop.val;
                l1Loop = l1Loop.next;
            }
            if (l2Loop != null) {
                sum += l2Loop.val;
                l2Loop= l2Loop.next;
            }

            retLoop.next = new ListNode(sum % 10);
            retLoop = retLoop.next;
        }
        if (sum / 10 > 0) {
            retLoop.next = new ListNode(1);
        }
        return ret.next;
    }

    /**
     * 142. Linked List Cycle II
     * Linked List, Medium
     * https://leetcode.com/problems/linked-list-cycle-ii/
     * */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head, entry = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }

    /**
     * 23. Merge k Sorted Lists
     * Linked List, Hard
     * https://leetcode.com/problems/merge-k-sorted-lists/
     * */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) return null;
        ListNode ret = new ListNode(0);
        ListNode current = ret;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val == o2.val) {
                    return 0;
                } else if (o1.val < o2.val) {
                    return -1;
                } else return 1;
            }
        });
        for (ListNode list: lists) {
            if (list != null)
                queue.add(list);
        }
        while (!queue.isEmpty()){
            current.next = queue.poll();
            current = current.next;
            if (current.next != null) {
                queue.add(current.next);
            }
        }
        return ret.next;
    }

    /**
     * 204. Count Primes
     * Hash Table, Easy
     * https://leetcode.com/problems/count-primes/
     * */
    public int countPrimes(int n) {
        /** old O(n2) solution */
//        int count = 0;
//        for (int i = 0; i < n; i++) {
//            if (isPrime(i)) count++;
//        }
//        return count;

        /**Hint code*/
//        boolean[] isPrime = new boolean[n];
//        for (int i = 2; i < n; i++) {
//            isPrime[i] = true;
//        }
//        // Loop's ending condition is i * i < n instead of i < sqrt(n)
//        // to avoid repeatedly calling an expensive function sqrt().
//        for (int i = 2; i * i < n; i++) {
//            if (!isPrime[i]) continue;
//            //starting from i*i because i *(1~i) already counted in the previous loop
//            for (int j = i * i; j < n; j += i) {
//                isPrime[j] = false;
//            }
//        }
//        int count = 0;
//        for (int i = 2; i < n; i++) {
//            if (isPrime[i]) count++;
//        }
//        return count;

        if (n < 3) return 0;
        boolean[] isNotPrime = new boolean[n];
        int isNotPrimeCount = 0;
        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i < n; i++) {
            if (isNotPrime[i]) continue;
            //starting from i*i because i *(1~i) already counted in the previous loop
            for (int j = i * i; j < n; j += i) {
                //this may be called on same item for multiple times, so isNotPrimeCount will not be accurate.
//                isNotPrime[j] = true;
//                isNotPrimeCount++;
                if (isNotPrime[j] == false) {
                    isNotPrime[j] = true;
                    isNotPrimeCount++;
                }
            }
        }
        //Use isNotPrimeCount, there there will be no need to loop it again.
//        int count = 0;
//        for (int i = 2; i < n; i++) {
//            if (isNotPrime[i] == false) {
//                count++;
//            }
//        }
        return n - isNotPrimeCount - 2;
    }

    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i < n ; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * 205. Isomorphic Strings
     * Hash Table, Easy
     * https://leetcode.com/problems/isomorphic-strings/
     * */
    public boolean isIsomorphic(String s, String t) {
        int [] sCount = new int[256];
        int [] tCount = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i), tChar = t.charAt(i);
            if (sCount[sChar] != tCount[tChar]) return false;
            sCount[sChar]+= (i + 1);
            tCount[tChar]+= (i + 1);
        }
        return true;
    }

    public String replaceWithNumber(String string) {
        HashMap<Character, Boolean> characterRemember = new HashMap<Character, Boolean>();
        String result = string;
//        int startChar = 0;
        for (int i = 0; i < result.length(); i++) {
            Character currentChar = result.charAt(i);
            if (characterRemember.get(currentChar) == null) {
//                result = result.replace(currentChar, (char)startChar);
//                startChar++;
                characterRemember.put(currentChar, Boolean.TRUE);
            }
        }
        return result;
    }

    /**
     * 36. Valid Sudoku
     * Hash Table, Easy
     * https://leetcode.com/problems/valid-sudoku/
     * */
    public boolean isValidSudoku(char[][] board) {
        List<HashMap<Character, Boolean>> horizental = new ArrayList<HashMap<Character, Boolean>>();
        List<HashMap<Character, Boolean>> vertical= new ArrayList<HashMap<Character, Boolean>>();
        List<HashMap<Character, Boolean>> subBoard = new ArrayList<HashMap<Character, Boolean>>();
        for (int i = 0; i < 9; i++) {
            horizental.add(new HashMap<Character, Boolean>());
            vertical.add(new HashMap<Character, Boolean>());
            subBoard.add(new HashMap<Character, Boolean>());
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Character currentChar = board[i][j];
                if (currentChar == '.') continue;
                int subBoardIndex = i / 3 * 3 + j / 3;
                if (horizental.get(i).get(currentChar) != null ||
                        vertical.get(j).get(currentChar) != null ||
                                subBoard.get(subBoardIndex).get(currentChar) != null)
                    return false;
                horizental.get(i).put(currentChar, true);
                vertical.get(j).put(currentChar, true);
                subBoard.get(subBoardIndex).put(currentChar, true);
            }
        }
        return true;
    }

    /**
     * 349. Intersection of Two Arrays
     * Hash Table, Easy
     * https://leetcode.com/problems/intersection-of-two-arrays/
     * */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();

        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) {
                set2.add(nums2[i]);
            }
        }
        int []result = new int[set2.size()];
        int index = 0;
        for (int i: set2) {
            result[index] = i;
            index++;
        }
        return result;
    }

    /**
     * 242. Valid Anagram
     * Hash Table, Easy
     * https://leetcode.com/problems/valid-anagram/
     * */
    public boolean isAnagram(String s, String t) {
//        if (s == null || t == null || s.length() != t.length()) return false;
//        HashMap<Character, Integer> sHash = new HashMap<Character, Integer>();
//        HashMap<Character, Integer> tHash = new HashMap<Character, Integer>();
//        for (int i = 0; i < s.length(); i++) {
//            Character currentSChar = s.charAt(i);
//            Character currentTChar = t.charAt(i);
//            if (sHash.containsKey(currentSChar)) {
//                sHash.put(currentSChar, sHash.get(currentSChar) + 1);
//            } else {
//                sHash.put(currentSChar, 1);
//            }
//            if (tHash.containsKey(currentTChar)) {
//                tHash.put(currentTChar, tHash.get(currentTChar) + 1);
//            } else {
//                tHash.put(currentTChar, 1);
//            }
//        }
//        if (sHash.keySet().size() == tHash.keySet().size()) {
//            for(Character c: sHash.keySet()) {
//                if (!tHash.containsKey(c) || tHash.get(c) != sHash.get(c)) {
//                    return false;
//                }
//            }
//        } else {
//            return false;
//        }
//        return true;

        if (s == null || t == null || s.length() != t.length()) return false;
        char []sPool = new char[256];
        char []tPool = new char[256];
        for (int i = 0; i < s.length(); i++) {
            sPool[s.charAt(i)]++;
            tPool[t.charAt(i)]++;
        }
        for (int i = 0; i < 256; i++) {
            if (sPool[i] != tPool[i]) return false;
        }
        return true;
    }

    /**
     * 350. Intersection of Two Arrays II
     * Hash Table, Easy
     * https://leetcode.com/problems/intersection-of-two-arrays-ii/
     * */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums1.length; i++) {
            int countForCurrentValue = map1.get(nums1[i]) == null ? 0 : map1.get(nums1[i]);
            map1.put(nums1[i], countForCurrentValue + 1);
        }
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        for (int i = 0; i < nums2.length; i++) {
            int countForCurrentValue = map1.get(nums2[i]) == null ? 0 : map1.get(nums2[i]);
            if (countForCurrentValue != 0) {
                resultList.add(nums2[i]);
                map1.put(nums2[i], --countForCurrentValue);
            }
        }
        int []resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }

    /**
     * 202. Happy Number
     * Hash Table, Easy
     * https://leetcode.com/problems/happy-number/
     * */
    public boolean isHappy(int n) {
//        HashMap<Character, Integer> pool = new HashMap<Character, Integer>();
//        Set<Integer> resultSet = new HashSet<Integer>();
//        pool.put('0', 0);
//        pool.put('1', 1);
//        pool.put('2', 4);
//        pool.put('3', 9);
//        pool.put('4', 16);
//        pool.put('5', 25);
//        pool.put('6', 36);
//        pool.put('7', 49);
//        pool.put('8', 64);
//        pool.put('9', 81);
//        String str = String.valueOf(n);
//        int result = 0;
//        while (result != 1) {
//            result = 0;
//            for (int i = 0; i < str.length(); i++) {
//                result += pool.get(str.charAt(i));
//            }
//            if (!resultSet.contains(result)) {
//                resultSet.add(result);
//            } else {
//                return false;
//            }
//            str = Integer.toString(result);
//        }
//        return true;
        Set<Integer> resultSet = new HashSet<Integer>();
        String str = String.valueOf(n);
        int result = 0;
        while (result != 1) {
            result = 0;
            for (int i = 0; i < str.length(); i++) {
                int num = str.charAt(i) - 48;
                result += num * num;
            }
            if (!resultSet.contains(result)) {
                resultSet.add(result);
            } else {
                return false;
            }
            str = Integer.toString(result);
        }
        return true;
    }

    /**
     * 299. Bulls and Cows
     * Hash Table, Easy
     * https://leetcode.com/problems/bulls-and-cows/
     * */
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null) return null;
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        int A = 0, B = 0;
        for(int i = 0; i < secret.length(); i++) {
            Character currentChar = secret.charAt(i);
            if (currentChar == guess.charAt(i)) {
                A++;
                continue;
            }
            if (!hashMap.containsKey(currentChar)) {
                hashMap.put(currentChar, 1);
            } else {
                hashMap.put(currentChar, hashMap.get(currentChar) + 1);
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                continue;
            } else if (hashMap.containsKey(guess.charAt(i)) && hashMap.get(guess.charAt(i)) > 0) {
                B++;
                hashMap.put(guess.charAt(i), hashMap.get(guess.charAt(i)) - 1);
            }
        }
        return A + "A" + B + "B";
    }

    /**
     * 290. Word Pattern
     * Hash Table, Easy
     * https://leetcode.com/problems/word-pattern/
     * */
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) return false;
        String []words = str.split("\\s+");
        if (pattern.length() != words.length) return false;
        HashMap<Character, String> hashMap = new HashMap<Character, String>();
        for (int i = 0; i < pattern.length(); i++) {
            if (i >= words.length) return false;
            char currentPattern = pattern.charAt(i);
            if (hashMap.containsKey(currentPattern)) {
                if (!hashMap.get(currentPattern).equalsIgnoreCase(words[i]))
                    return false;
            } else {
                if (hashMap.containsValue(words[i]))
                    return false;
                else {
                    hashMap.put(currentPattern, words[i]);
                }
            }
        }
        return true;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * Hash Table, Medium
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     * */
    public int lengthOfLongestSubstring(String s) {
        // O(n2) Time Limit Exceeded
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<Character>();
            for (int j = i; j < s.length(); j++) {
                char currentChar = s.charAt(j);
                if (set.contains(currentChar)) break;
                else set.add(currentChar);
            }
            if (set.size() > max) max = set.size();
        }
        return max;
    }

    /**
     * 3. 325. Maximum Size Subarray Sum Equals k
     * Hash Table, Medium
     * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
     * */
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length < 1) return 0;
        HashMap<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) max = i + 1;
            else if (hashmap.containsKey(sum - k)) max = Math.max(max, i - hashmap.get(sum - k));
            if (!hashmap.containsKey(sum)) hashmap.put(sum, i);
        }
        return max;
    }

    /**
     * 49. Group Anagrams
     * Hash Table, Medium
     * https://leetcode.com/problems/anagrams/
     * */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length < 1) return new ArrayList<List<String>>();
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            char [] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String key = String.valueOf(charArray);
            if (hashMap.containsKey(key)) {
                hashMap.get(key).add(strs[i]);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(strs[i]);
                hashMap.put(key, list);
            }
        }
        return new ArrayList<List<String>>(hashMap.values());
    }
    public void testGroupAnagrams(){
        String []input = new String[1];
        input[0] = "";
        System.out.println(groupAnagrams(input));
    }

    /**
     * 138. Copy List with Random Pointer
     * Hash Table, Hard
     * https://leetcode.com/problems/copy-list-with-random-pointer/
     * */
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> hashmap = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode current = head;
        while (current != null) {
            hashmap.put(current, new RandomListNode(current.label));
            current = current.next;
        }
        current = head;
        RandomListNode ret = hashmap.get(current);
        while (current != null) {
            RandomListNode currentNewNode = hashmap.get(current);
            currentNewNode.next = hashmap.get(current.next);
            currentNewNode.random = hashmap.get(current.random);
            current = current.next;
        }
        return ret;
    }

    /**
     * 336. Palindrome Pairs
     * Hash Table, Hard
     * https://leetcode.com/problems/palindrome-pairs/
     * */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (words == null || words.length < 1) return ret;
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            hashmap.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j <= word.length(); j++) {
                String left = word.substring(0, j);
                String right = word.substring(j);
                if (isPalindrome(left)) {
                    String rightRevert = new StringBuilder(right).reverse().toString();
                    if (hashmap.containsKey(rightRevert) && hashmap.get(rightRevert) != i) {
                        List<Integer> pair = new ArrayList<Integer>();
                        pair.add(hashmap.get(rightRevert));
                        pair.add(i);
                        ret.add(pair);
                    }
                }
                if (isPalindrome(right)) {
                    String leftRevert = new StringBuilder(left).reverse().toString();
                    if (hashmap.containsKey(leftRevert) && hashmap.get(leftRevert) != i && right.length() != 0) {
                        List<Integer> pair = new ArrayList<Integer>();
                        pair.add(i);
                        pair.add(hashmap.get(leftRevert));
                        ret.add(pair);
                    }
                }
            }
        }
        return ret;
    }
    public boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    /**
     * 231. Power of Two
     * Math, Easy
     * https://leetcode.com/problems/power-of-two/
     * */
    public boolean isPowerOfTwo(int n) {
//        if (n == 1) return true;
//        if (n % 2 != 0) return false;
//        else return isPowerOfTwo(n / 2);
        return n > 0 && 0 == (n & (n - 1));
    }

    /**
     * 9. Palindrome Number
     * Math, Easy
     * https://leetcode.com/problems/palindrome-number/
     * */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int reverted = reverse(x);
        return reverted == x;
    }

    /**
     * 7. Reverse Integer
     * Math, Easy
     * https://leetcode.com/problems/reverse-integer/
     * */
    public int reverse(int x) {
        long ret = 0;
        while (x != 0) {
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
            return 0;
        return (int) ret;
    }

    /**
     * 326. Power of Three
     * Math, Easy
     * https://leetcode.com/problems/power-of-three/
     * */
    public boolean isPowerOfThree(int n) {
//        if (n == 1) return true;
//        if (n % 3 != 0) return false;
//        return isPowerOfThree(n / 3);
        return true;
    }

    /**
     * 263. Ugly Number
     * Math, Easy
     * https://leetcode.com/problems/ugly-number/
     * */
    public boolean isUgly(int num) {
        if (num < 1) return false;
        if (num == 1) return true;
        while (num > 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 5 == 0) {
                num /= 5;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 258. Add Digits
     * Math, Easy
     * https://leetcode.com/problems/add-digits/
     * */
    public int addDigits(int num) {
        if (num < 1) return 0;
        while (num > 9) {
            int temp = num;
            int sum = 0;
            while (temp != 0) {
                sum += temp % 10;
                temp /= 10;
            }
            num = sum;
        }
        return num;
    }

    /**
     * 168. Excel Sheet Column Title
     * Math, Easy
     * https://leetcode.com/problems/excel-sheet-column-title/
     * */
    public String convertToTitle(int n) {
//        if (n < 1) return null;
//        String ret = "";
//        if (n <= 26) {
//            ret += (char) (n + 64);
//            return ret;
//        }
//        while (n != 0) {
//            int mod = (n % 26);
//            Character currentChar = (char)(mod + 64);// 65 is the ASCII Value of 'A'
//            if (mod == 0) currentChar = 'Z';
//            ret = currentChar + ret;
//            n /= 26;
//        }
//        return ret;
        return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
    }

    /**
     * 172. Factorial Trailing Zeroes
     * Math, Easy
     * https://leetcode.com/problems/factorial-trailing-zeroes/
     * */
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    /**
     * 223. Rectangle Area
     * Math, Easy
     * https://leetcode.com/problems/rectangle-area/
     * */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int width = 0, height = 0;
        int widthStart = E > A ? E : A;
        int widthEnd = C > G ? G : C;
        width = Math.abs(widthEnd - widthStart);

        int heightStart = B > F ? B : F;
        int heightEnd = D > H ? H : D;
        height = Math.abs(heightEnd - heightStart);
        int doubleArea = width * height;
        if (A == C || B == D || E == G || F == H) doubleArea = 0;
        if (E > C || A > G) doubleArea = 0;
        if (B > H || F > D) doubleArea = 0;
        return (C - A) * (D - B) + (G - E) * (H - F) - doubleArea;
    }

    /**
     * 344. Reverse String
     * Two Pointers, Easy
     * https://leetcode.com/problems/reverse-string/
     * */
    public String reverseString(String s) {
        StringBuffer result = new StringBuffer();
        for (int i = s.length() - 1; i >= 0 ; i--) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    /**
     * 345. Reverse Vowels of a String
     * Two Pointers, Easy
     * https://leetcode.com/problems/reverse-vowels-of-a-string/
     * */
    public String reverseVowels(String s) {
        int left = 0, right = s.length() - 1;
        char [] result = s.toCharArray();
        while (left < right) {
            if (isVowels(s.charAt(left)) && isVowels(s.charAt(right))) {
                char temp = s.charAt(left);
                result[left] = s.charAt(right);
                result[right] = temp;
                left++;
                right--;
            } else if (isVowels(s.charAt(left))) {
                right--;
            } else if (isVowels(s.charAt(right))) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        return String.valueOf(result);
    }
    public boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u' || c == 'A'
                || c == 'E' || c == 'I' || c == 'O'
                || c == 'U';
    }


    /**
     * 383. Ransom Note
     * String, Easy
     * https://leetcode.com/problems/ransom-note/
     * */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null || magazine.length() < ransomNote.length()) return false;
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
        for (int i = 0; i < magazine.length(); i++) {
            Character currentChar = magazine.charAt(i);
            if (hashmap.containsKey(currentChar)) {
                hashmap.put(currentChar, hashmap.get(currentChar) + 1);
            } else {
                hashmap.put(currentChar, 1);
            }
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            Character currentChar = ransomNote.charAt(i);
            if (hashmap.get(currentChar) == null || hashmap.get(currentChar) < 1) return false;
            else {
                hashmap.put(currentChar, hashmap.get(currentChar) - 1);
            }
        }
        return true;
    }

    /**
     * 5. Longest Palindromic Substring
     * String, Medium
     * https://leetcode.com/problems/longest-palindromic-substring/
     * */
    int max = 0;
    int lowIndex = 0;
    public String longestPalindrome(String s) {
        if (s.length() < 2) return s;
        for (int i = 0; i < s.length() - 1; i++) {
            palindrome(s, i, i);
            palindrome(s, i, i + 1);
        }
        return s.substring(lowIndex, lowIndex + max);
    }
    public void palindrome(String s, int start, int end) {
        while (start >= 0 && end <= s.length() - 1 && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        if (end - start - 1 > max) { //because start, end now contains fail case, so need to -2
            max = end - start - 1;
            lowIndex = start + 1;
        }
    }

//    public String longestPalindrome(String s) {
//        if (s.length() < 2) return s;
//        Integer max = 0;
//        Integer lowIndex = 0;
//        for (int i = 0; i < s.length() - 1; i++) {
//            palindrome(s, i, i, max, lowIndex);
//            palindrome(s, i, i + 1, max, lowIndex);
//        }
//        return s.substring(lowIndex, lowIndex + max);
//    }
//    public void palindrome(String s, int start, int end, Integer max, Integer lowIndex) {
//        while (start >= 0 && end <= s.length() - 1 && s.charAt(start) == s.charAt(end)) {
//            start--;
//            end++;
//        }
//        if (end - start - 1 > max) { //because start, end now contains fail case, so need to -2
//            max = end - start - 1;
//            lowIndex = start + 1;
//        }
//    }

    /**
     * 214. Shortest Palindrome
     * String, Hard
     * https://leetcode.com/problems/shortest-palindrome/
     * */
    public String shortestPalindrome(String s) {
        String reverseString = s + "#" + new StringBuffer(s).reverse().toString();
        int []KPM = buildKPM(reverseString);
        return reverseString.substring(s.length() + 1,  KPM.length - KPM[KPM.length - 1]) + s;
    }
    public int [] buildKPM(String s) {
        int []ret = new int[s.length()];
        for (int i = 1; i < ret.length; i++) {
            int j = ret[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = ret[j - 1];
            if (s.charAt(i) == s.charAt(j))
                ret[i] = j + 1;
        }
        return ret;
    }

    /**
     * 278. First Bad Version
     * Binary Search, Easy
     * https://leetcode.com/problems/first-bad-version/
     * */
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (!isBadVersion(mid)) start = mid + 1;//+1 is very important
            else end = mid;
        }
        return start;
    }
    boolean isBadVersion(int version) {
        return false;
    }

    /**
     * 374. Guess Number Higher or Lower
     * Binary Search, Easy
     * https://leetcode.com/problems/guess-number-higher-or-lower/
     * */
    public int guessNumber(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end-start) / 2;
            int ret = guess(mid);
            if (ret == 0) {
                return mid;
            } else if (ret < 0) {
                end = mid;
            } else if (ret > 0) {
                start = mid + 1;//+1 is very important
            }
        }
        return start;
    }
    public int guess(int num) {
        return 0;
    }

    /**
     * 198. House Robber
     * Dynamic Programming, Easy
     * https://leetcode.com/tag/dynamic-programming/
     * */
    public int rob(int[] nums) {
        if (nums.length < 1) return 0;
        else if (nums.length == 1) return nums[0];
        else if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int []mem = new int[nums.length];
        mem[0] = nums[0];
        mem[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            mem[i] = Math.max(mem[i - 1], nums[i] + mem[i - 2]);
        }
        return mem[nums.length - 1];
    }

    /**
     * 303. Range Sum Query - Immutable
     * Dynamic Programming, Easy
     * https://leetcode.com/problems/range-sum-query-immutable/
     * */
    public class NumArray {
        private int []nums;
        private int []sums;
        public NumArray(int[] nums) {
            this.nums = nums;
            if (nums.length < 1) return;
            this.sums = new int[nums.length];
            this.sums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                this.sums[i] =  this.sums[i - 1] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            if (nums.length < 1 || i < 0 || j >= nums.length) return 0;
            return sums[j] - ((i - 1) < 0 ? 0 : sums[i - 1]);
        }
    }

    /**
     * 307. Range Sum Query - Mutable
     * Binary Indexed Tree
     * https://leetcode.com/problems/range-sum-query-mutable/
     * */
    public class NumArrayMutable {
        int [] nums;
        int [] binaryIndexedTree;
        public NumArrayMutable(int[] nums) {
            this.nums = nums;
            this.binaryIndexedTree = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                add(i + 1, nums[i]);
            }
        }

        void update(int i, int val) {
            int add = val - this.nums[i];
            this.nums[i] = val;
            add(i + 1, add);
        }
        void add(int i, int val) {
            while (i < this.binaryIndexedTree.length) {
                binaryIndexedTree[i] += val;
                i += (i & -i);
            }
        }

        public int sumRange(int i, int j) {
            return sum(j + 1) - sum(i);
        }
        public int sum(int i) {
            int sum = 0;
            while (i > 0) {
                sum += this.binaryIndexedTree[i];
                i -= (i & -i);
            }
            return sum;
        }
    }
    public void testNumArrayMutable() {
        int [] test = new int[]{1, 3, 5};
        NumArrayMutable array = new NumArrayMutable(test);
        int one = array.sumRange(0, 2);
        array.update(1, 2);
        int two = array.sumRange(0, 2);
    }

    public class NumMatrixImmutable {
        int [][] mem;
        public NumMatrixImmutable(int[][] matrix) {
            if (matrix == null || matrix.length < 1) return;
            this.mem = new int[matrix.length][matrix[0].length];
            this.mem[0][0] = matrix[0][0];
            for (int i = 1; i < matrix.length; i++) {
                this.mem[i][0] = this.mem[i - 1][0] + matrix[i][0];
            }
            for (int i = 1; i < matrix[0].length; i++) {
                this.mem[0][i] = this.mem[0][i - 1] + matrix[0][i];
            }
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    this.mem[i][j] = this.mem[i - 1][j] + this.mem[i][j - 1] - this.mem[i - 1][j - 1] + matrix[i][j];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 < 0 || col1 < 0 || row2 >= mem.length || col2 >= mem[0].length) return -1;
            int sum = mem[row2][col2];
            sum -= (row1 - 1 < 0)? 0:  mem[row1 - 1][col2];
            sum -= (col1 - 1 < 0)? 0: mem[row2][col1 - 1];
            sum += (row1 - 1 < 0 || col1 - 1 < 0)? 0: mem[row1 - 1][col1 - 1];
            return sum;
        }
    }

    /**
     * 308. Range Sum Query 2D - Mutable
     * Binary Indexed Tree
     * https://leetcode.com/problems/range-sum-query-2d-mutable/
     * */
    public class NumMatrix {
        int [][] mem;
        int [][] matrix;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return;
            this.matrix = matrix;
            this.mem = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    add(i + 1, j + 1, matrix[i][j]);
                }
            }
        }

        public void update(int row, int col, int val) {
            int add = val - matrix[row][col];
            matrix[row][col] = val;
            add(row + 1, col + 1, add);
        }
        public void add(int row, int col, int val) {
            for (int i = row; i < mem.length; i += (i & -i)) {
                for (int j = col; j < mem[0].length; j += (j & -j)) {
                    mem[i][j] += val;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row1++;
            row2++;
            col1++;
            col2++;
            int sum = sum(row2, col2);
            sum -= sum(row1 - 1, col2);
            sum -= sum(row2, col1 - 1);
            sum += sum(row1 - 1, col1 - 1);
            return sum;
        }
        public int sum(int row, int col) {
            int sum = 0;
            for (int i = row; i > 0; i -= (i & -i)) {
                for (int j = col; j > 0; j -= (j & -j)) {
                    sum += mem[i][j];
                }
            }
            return sum;
        }
    }
    public void testNumMatrix() {
        int [][]test = new int[][]{
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5}};
        NumMatrix numMatrix = new NumMatrix(test);
        int one = numMatrix.sumRegion(2, 1, 4, 3);
        numMatrix.update(3, 2, 2);
        int two = numMatrix.sumRegion(2, 1, 4, 3);
    }

    /**
     * 327. Count of Range Sum
     * Binary Search Tree
     * https://leetcode.com/problems/count-of-range-sum/
     * */
    public int countRangeSum(int[] nums, int lower, int upper) {
        return 0;
    }

    /**
     * 70. Climbing Stairs
     * Dynamic Programming, Easy
     * https://leetcode.com/problems/climbing-stairs/
     * */
    public int climbStairs(int n) {
        if (n < 1) return 0;
        else if (n == 1) return 1;
        else if (n == 2) return 2;
        int []mem = new int[n + 1];
        mem[1] = 1;
        mem[2] = 2;
        for (int i = 3; i <= n; i++) {
            mem[i] = mem[i -1] + mem[i - 2];
        }
        return mem[n];
    }

    /**
     * 155. Min Stack
     * Stack, Easy
     * https://leetcode.com/problems/min-stack/
     * */
    public class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else {
                minStack.push(Math.min(minStack.peek(), x));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * 225. Implement Stack using Queues
     * Stack, Easy
     * https://leetcode.com/problems/implement-stack-using-queues/
     * */
    class MyStack {
        Queue<Integer> queue = new LinkedList<Integer>();
        // Push element x onto stack.
        public void push(int x) {
            queue.add(x);
        }

        // Removes the element on top of the stack.
        public void pop() {
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.add(queue.poll());
            }
            queue.poll();
        }

        // Get the top element.
        public int top() {
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.add(queue.poll());
            }
            int top = queue.peek();
            queue.add(queue.poll());
            return top;
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * 232. Implement Queue using Stacks
     * Stack, Easy
     * https://leetcode.com/problems/implement-queue-using-stacks/
     * */
    class MyQueue {
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> tempStack = new Stack<Integer>();
        // Push element x to the back of queue.
        public void push(int x) {
            stack.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            int size = stack.size();
            for (int i = 0; i < size - 1; i++) {
                tempStack.push(stack.pop());
            }
            stack.pop();
            size = tempStack.size();
            for (int i = 0; i < size; i++) {
                stack.push(tempStack.pop());
            }
        }

        // Get the front element.
        public int peek() {
            int size = stack.size();
            for (int i = 0; i < size - 1; i++) {
                tempStack.push(stack.pop());
            }
            int peek = stack.peek();
            size = tempStack.size();
            for (int i = 0; i < size; i++) {
                stack.push(tempStack.pop());
            }
            return peek;
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return stack.isEmpty();
        }
    }
    public void testMyQueue() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.peek());
    }

    /**
     * 371. Sum of Two Integers
     * Bit Manipulation, Easy
     * https://leetcode.com/problems/sum-of-two-integers/
     * */
    public int getSum(int a, int b) {
//        boolean addOne = false;
//        int ret = a;
//        for (int i = 0; i < 15; i++) {
//            if (getBit(a, i) && getBit(b, i)) {
//                addOne = true;
//                ret = clearBit(ret, i);
//            } else if (getBit(a, i) || getBit(b, i)) {
//                if (addOne) {
//                    ret = clearBit(ret, i);
//                    addOne = false;
//                } else {
//                    ret = setBit(ret, i);
//                }
//            } else if (addOne) {
//                ret = setBit(ret, i);
//                addOne = false;
//            }
//        }
//        return ret;

        // Leetcode Answer
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }
    public boolean getBit(int numToGet, int nThBit) {
        return (numToGet & (1 << nThBit)) != 0;
    }
    public int setBit(int numToSet, int nThBit) {
        return numToSet | (1 << nThBit);
    }
    public int clearBit(int numToSet, int nThBit) {
        return numToSet & ~(1 << nThBit);
    }

    /**
     * 190. Reverse Bits
     * Bit Manipulation, Easy
     * https://leetcode.com/problems/reverse-bits/
     * */
    public int reverseBits(int n) {
//        long ret = 0;
//        for (int i = 0; i < 16; i++) {
//            long leftBit = (1 << (32 - i - 1)) & n;
//            long rightBit = (1 << i) & n;
//            if (leftBit != 0) {
//                ret = ret | (leftBit >>> (32 - 2 * i - 1));
//            }
//            if (rightBit != 0) {
//                ret = ret | (rightBit << (32 - 2 * i - 1));
//            }
//        }
//        return (int)ret;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;   // CATCH: must do unsigned shift
            if (i < 31) // CATCH: for last digit, don't shift!
                result <<= 1;
        }
        return result;
    }

    /**
     * Number of 1 Bits
     * Bit Manipulation, Easy
     * https://leetcode.com/problems/number-of-1-bits/
     * */
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) > 0) count++;
            n >>>= 1;
        }
        return count;
    }

    /**
     * 342. Power of Four
     * Bit Manipulation, Easy
     * https://leetcode.com/problems/power-of-four/
     * */
    public boolean isPowerOfFour(int num) {
        if (num < 1) return false;
        double root = Math.sqrt(num);
        if (root % 1 != 0) return false;
        return ((int)root & ((int)root - 1)) == 0;
    }

    /**
     * 107. Binary Tree Level Order Traversal II
     * Tree, Easy
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
     * */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
//        if (root == null) return new ArrayList<List<Integer>>();
//        Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
//        HashMap<TreeNode, Boolean> marksHashMap = new HashMap<TreeNode, Boolean>();
//        HashMap<TreeNode, Integer> levelsHashMap = new HashMap<TreeNode, Integer>();
//        marksHashMap.put(root, true);
//        levelsHashMap.put(root, 0);
//        bfsQueue.add(root);
//
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        int currentLevel = levelsHashMap.get(root);
//        List<Integer> currentList = new ArrayList<Integer>();
//
//        while (!bfsQueue.isEmpty()) {
//            // Visit
//            TreeNode currentVisit = bfsQueue.poll();
//            Integer currentVisitLevel = levelsHashMap.get(currentVisit);
//            if (currentVisitLevel != null && currentVisitLevel != currentLevel) {
//                ret.add(0, currentList);
//                currentList = new ArrayList<Integer>();
//                currentLevel = currentVisitLevel;
//            }
//            currentList.add(currentVisit.val);
//
//            if (currentVisit.left != null && marksHashMap.get(currentVisit.left) == null) {
//                marksHashMap.put(currentVisit.left, true);
//                levelsHashMap.put(currentVisit.left, levelsHashMap.get(currentVisit) + 1);
////                currentVisit.left.isMarked = true;
////                currentVisit.left.level = currentVisit.level + 1;
//                bfsQueue.add(currentVisit.left);
//            }
//            if (currentVisit.right != null && marksHashMap.get(currentVisit.right) == null) {
//                marksHashMap.put(currentVisit.right, true);
//                levelsHashMap.put(currentVisit.right, levelsHashMap.get(currentVisit) + 1);
////                currentVisit.right.isMarked = true;
////                currentVisit.right.level = currentVisit.level + 1;
//                bfsQueue.add(currentVisit.right);
//            }
//        }
//        ret.add(0, currentList);
//        return ret;

//        if (root == null) return new ArrayList<List<Integer>>();
//        Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
////        HashMap<TreeNode, Integer> levelsHashMap = new HashMap<TreeNode, Integer>();
////        levelsHashMap.put(root, 0);
//        bfsQueue.add(root);
//
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        int currentLevel = 0;
//        List<Integer> currentList = new ArrayList<Integer>();
//
//        while (!bfsQueue.isEmpty()) {
//            // Visit
//            TreeNode currentVisit = bfsQueue.poll();
//            if (currentVisitLevel != null && currentVisitLevel != currentLevel) {
//                ret.add(0, currentList);
//                currentList = new ArrayList<Integer>();
//                currentLevel = currentVisitLevel;
//            }
//            currentList.add(currentVisit.val);
//
//            if (currentVisit.left != null) {
//                bfsQueue.add(currentVisit.left);
//            }
//            if (currentVisit.right != null) {
//                bfsQueue.add(currentVisit.right);
//            }
//        }
//        ret.add(0, currentList);
//        return ret;

        return null;
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public void testLevelOrderBottom() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t2t = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t3t = new TreeNode(3);
        t1.left = t2;
        t1.right = t2t;
        t2.left = t3;
        t2t.right = t3t;
        t2.right = null;
        t2t.left = null;
        boolean ret = isSymmetric(t1);
        System.out.println(ret);
    }

    /**
     * 102. Binary Tree Level Order Traversal
     * Tree, Easy
     * https://leetcode.com/problems/binary-tree-level-order-traversal/
     * */
    public List<List<Integer>> levelOrder(TreeNode root) {
//        if (root == null) return new ArrayList<List<Integer>>();
//        Queue<TreeNode> bfsQueue = new LinkedList<TreeNode>();
//        HashMap<TreeNode, Boolean> marksHashMap = new HashMap<TreeNode, Boolean>();
//        HashMap<TreeNode, Integer> levelsHashMap = new HashMap<TreeNode, Integer>();
//        marksHashMap.put(root, true);
//        levelsHashMap.put(root, 0);
//        bfsQueue.add(root);
//
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        int currentLevel = levelsHashMap.get(root);
//        List<Integer> currentList = new ArrayList<Integer>();
//
//        while (!bfsQueue.isEmpty()) {
//            // Visit
//            TreeNode currentVisit = bfsQueue.poll();
//            Integer currentVisitLevel = levelsHashMap.get(currentVisit);
//            if (currentVisitLevel != null && currentVisitLevel != currentLevel) {
//                ret.add(currentList);
//                currentList = new ArrayList<Integer>();
//                currentLevel = currentVisitLevel;
//            }
//            currentList.add(currentVisit.val);
//
//            if (currentVisit.left != null && marksHashMap.get(currentVisit.left) == null) {
//                marksHashMap.put(currentVisit.left, true);
//                levelsHashMap.put(currentVisit.left, levelsHashMap.get(currentVisit) + 1);
//                bfsQueue.add(currentVisit.left);
//            }
//            if (currentVisit.right != null && marksHashMap.get(currentVisit.right) == null) {
//                marksHashMap.put(currentVisit.right, true);
//                levelsHashMap.put(currentVisit.right, levelsHashMap.get(currentVisit) + 1);
//                bfsQueue.add(currentVisit.right);
//            }
//        }
//        ret.add(currentList);
//        return ret;
        return null;
    }

    /**
     * 101. Symmetric Tree
     * Tree, Easy
     * https://leetcode.com/problems/symmetric-tree/
     * */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int level = 0;
        queue.add(root);
        queue.add(null);
        List<Integer> currentLevelNodes = new ArrayList<Integer>();
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode == null) {
                level++;
                queue.add(null);
                if (currentLevelNodes.size() > 1) {
                    for (int i = 0; i < currentLevelNodes.size(); i++) {
                        if (currentLevelNodes.get(i) != currentLevelNodes.get(currentLevelNodes.size() - 1 - i)) {
                            return false;
                        }
                    }
                }
                currentLevelNodes = new ArrayList<Integer>();
                if (queue.peek() == null) break;
                else continue;
            }
            //Visit current node
            currentLevelNodes.add(currentNode.val);
            if (currentNode.val == -10) continue;

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            } else {
                queue.add(new TreeNode(-10));
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            } else {
                queue.add(new TreeNode(-10));
            }
        }
        return true;
    }

    /**
     * 257. Binary Tree Paths
     * Tree, Easy
     * https://leetcode.com/problems/binary-tree-paths/
     * */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<String>();
        List<String> ret = new ArrayList<String>();
        StringBuffer path = new StringBuffer();
        dfs(root, path, ret);
        return ret;
    }
    public void dfs(TreeNode root, StringBuffer path, List<String> ret) {
        if (root == null) return;
        //visit node
        path.append(root.val + "->");
        if (root.left == null && root.right == null) {
            ret.add(path.substring(0, path.length() - 2));
        }
        dfs(root.left, path, ret);
        if (root.left != null)
            path.setLength(path.length() - 2 - Integer.valueOf(root.left.val).toString().length());
//            path.delete(path.length() - 3, path.length());
        dfs(root.right, path, ret);
        if (root.right != null)
            path.setLength(path.length() - 2 - Integer.valueOf(root.right.val).toString().length());
//            path.delete(path.length() - 3, path.length());
    }

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * Tree, Easy
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
     * */
    List<TreeNode> pPath;
    List<TreeNode> qPath;
    public TreeNode lowestCommonAncestorBinarySearchTree(TreeNode root, TreeNode p, TreeNode q) {
//        List<TreeNode> tempPath = new ArrayList<TreeNode>();
//        if (root == null) return null;
//        if (root == p || root == q) return root;
//        TreeNode minNode, maxNode;
//        if (p.val < q.val) {
//            minNode = p;
//            maxNode = q;
//        } else {
//            minNode = q;
//            maxNode = p;
//        }
//        lowestCommonAncestorDFS(root, minNode, maxNode, tempPath);
//        if (pPath != null && qPath != null) {
//            int i = 0;
//            int minSize = pPath.size() < qPath.size()? pPath.size() : qPath.size();
//            while (i < minSize && pPath.get(i) == qPath.get(i)) {
//                i++;
//            }
//            return pPath.get(i - 1 < 0 ? 0: i - 1);
//        }
//        return null;
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            root = (root.val - p.val) > 0? root.left: root.right;
        }
        return root;
    }
    public void lowestCommonAncestorDFS(TreeNode root, TreeNode minNode, TreeNode maxNode, List<TreeNode> tempPath) {
        if (root == null) return;
        //Visit node
        tempPath.add(root);
        if (root == minNode)
            pPath = new ArrayList<TreeNode>(tempPath);
        if (root == maxNode)
            qPath = new ArrayList<TreeNode>(tempPath);

        lowestCommonAncestorDFS(root.left, minNode, maxNode, tempPath);
        if (root.left != null && tempPath.size() > 0)
            tempPath.remove(tempPath.size() - 1);
        lowestCommonAncestorDFS(root.right, minNode, maxNode, tempPath);
        if (root.right != null && tempPath.size() > 0)
            tempPath.remove(tempPath.size() - 1);
    }
    public void testLowestCommonAncestor(){
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t1 = new TreeNode(1);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        System.out.println(minDepth(t1));
    }

    public TreeNode lowestCommonAncestorBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestorBinaryTree(root.left, p, q);
        TreeNode right = lowestCommonAncestorBinaryTree(root.right, p, q);
        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }

    /**
     * 226. Invert Binary Tree
     * Tree, Easy
     * https://leetcode.com/problems/invert-binary-tree/
     * */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        invertTreeHelper(root);
        return root;
    }
    public void invertTreeHelper(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTreeHelper(root.left);
        invertTreeHelper(root.right);
    }

    /**
     * 100. Same Tree
     * Tree, Easy
     * https://leetcode.com/problems/same-tree/
     * */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 111. Minimum Depth of Binary Tree
     * Tree, Easy
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/
     * */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        else if (root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        } else {
            return minDepth(root.left == null? root.right: root.left) + 1;
        }
    }

    /**
     * 104. Maximum Depth of Binary Tree
     * Tree, Easy
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/
     * */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        else if (root.left != null && root.right != null) {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        } else {
            return maxDepth(root.left == null? root.right: root.left) + 1;
        }
    }

    /**
     * 98. Validate Binary Search Tree
     * Tree, Medium
     * https://leetcode.com/problems/validate-binary-search-tree/
     * */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
//        boolean isLeftValid = root.left == null? true: isValidBST(root.left) && root.left.val < root.val;
        boolean isLeftValid = isValidBST(root.left);
        boolean isRightValid = isValidBST(root.right);

        return isLeftValid && isRightValid && (root.left == null? true: root.left.val < root.val)
                && (root.right == null? true: root.right.val > root.val);
    }

    /**
     * 110. Balanced Binary Tree
     * Tree, Easy
     * https://leetcode.com/problems/balanced-binary-tree/
     * */
//    public boolean isBalanced(TreeNode root) {
//        if (root == null) return true;
//        if (Math.abs(treeDepth(root.left) - treeDepth(root.right)) > 1) return false;
//        return isBalanced(root.left) && isBalanced(root.right);
//    }
//    public int treeDepth(TreeNode root) {
//        if (root == null) return -1;
//        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
//    }
    public boolean isBalanced(TreeNode root) {
        return treeDepth(root) != Integer.MIN_VALUE;
    }
    public int treeDepth(TreeNode root) {
        if (root == null) return -1;
        int leftDepth = treeDepth(root.left);
        if (leftDepth == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightDepth = treeDepth(root.right);
        if (rightDepth == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        if (Math.abs(rightDepth - leftDepth) > 1) return Integer.MIN_VALUE;
        else {
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    /**
     * 297. Serialize and Deserialize Binary Tree
     * Tree, Hard
     * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
     * */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder stringBuilder = new StringBuilder();
            buildString(root, stringBuilder);
            return stringBuilder.toString();
        }
        public void buildString(TreeNode root, StringBuilder stringBuilder) {
            if (root == null) {
                stringBuilder.append("X").append(",");
            } else {
                stringBuilder.append(root.val).append(",");
                buildString(root.left, stringBuilder);
                buildString(root.right, stringBuilder);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<String>();
            queue.addAll(Arrays.asList(data.split(",")));
            return buildTree(queue);
        }
        public TreeNode buildTree(Queue<String> queue) {
            String valueString = queue.poll();
            if (valueString.equalsIgnoreCase("X")) return null;
            else {
                TreeNode node = new TreeNode(Integer.valueOf(valueString));
                node.left = buildTree(queue);
                node.right = buildTree(queue);
                return node;
            }
        }
    }

    /**
     * 146. LRU Cache
     * Design, Hard
     * https://leetcode.com/problems/lru-cache/
     * */
    class DoubleListNode {
        int value, key;
        DoubleListNode pre;
        DoubleListNode next;
        public DoubleListNode(int val, int key) {
            this.value = val;
            this.key = key;
        }
    }
    public class LRUCache {

        HashMap<Integer, DoubleListNode> hashMap;
        int capacity;
        DoubleListNode head, end;


        public LRUCache(int capacity) {
            hashMap = new HashMap<Integer, DoubleListNode>();
            this.capacity = capacity;
            head = new DoubleListNode(0, 0);
            end = new DoubleListNode(0, 0);
            head.next = end;
            head.pre = null;
            end.pre = head;
            end.next = null;
        }

        public int get(int key) {
            if (!hashMap.containsKey(key)) return -1;
            DoubleListNode node = hashMap.get(key);
            //move node to head
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
            return node.value;
        }

        public void set(int key, int value) {
            if (hashMap.containsKey(key)) {
                //update value
                DoubleListNode node = hashMap.get(key);
                node.value = value;
                //move node to head
                node.pre.next = node.next;
                node.next.pre = node.pre;
                node.next = head.next;
                head.next.pre = node;
                node.pre = head;
                head.next = node;
            } else {
                if (hashMap.size() + 1 > capacity) {
                    //remove Hashmap item
                    hashMap.remove(end.pre.key);
                    //remove end node
                    end.pre.pre.next = end;
                    end.pre = end.pre.pre;
                }
                //Create node
                DoubleListNode node = new DoubleListNode(value, key);
                //Add to head
                node.next = head.next;
                head.next.pre = node;
                node.pre = head;
                head.next = node;
                //Add to HashMap
                hashMap.put(key, node);
            }
        }
    }

    /**
     * 208. Implement Trie (Prefix Tree)
     * Design, Medium
     * https://leetcode.com/problems/implement-trie-prefix-tree/
     * */
    public class MedianFinder {
        PriorityQueue<Integer> highHalfMinHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) return 0;
                return o1 > o2 ? 1: -1;
            }
        });
        PriorityQueue<Integer> lowHalfMaxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) return 0;
                return o1 > o2 ? -1: 1;
            }
        });

        // Adds a number into the data structure.
        public void addNum(int num) {
            lowHalfMaxHeap.add(num);
            highHalfMinHeap.add(lowHalfMaxHeap.poll());
            if (highHalfMinHeap.size() > lowHalfMaxHeap.size() + 1) {
                lowHalfMaxHeap.add(highHalfMinHeap.poll());
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            return highHalfMinHeap.size() > lowHalfMaxHeap.size()?
                    highHalfMinHeap.peek():
                    (highHalfMinHeap.peek() + lowHalfMaxHeap.peek()) / 2.0;
        }
    }
    public void testMedianFinder() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(-1);
        finder.addNum(-2);
        finder.addNum(-3);
        double i = finder.findMedian();
    }

    /**
     * 208. Implement Trie (Prefix Tree)
     * Design, Medium
     * https://leetcode.com/problems/implement-trie-prefix-tree/
     * */
    class TrieNode {
        HashMap<Character, TrieNode> hashmap;
        boolean isEndOfWord;
        public TrieNode() {
            hashmap = new HashMap<Character, TrieNode>();
            isEndOfWord = false;
        }
    }
    public class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode loop = root;
            for (int i = 0; i < word.length(); i++) {
                Character currentChar = word.charAt(i);
                TrieNode node = loop.hashmap.get(currentChar);
                if (node == null){
                    node = new TrieNode();
                    loop.hashmap.put(currentChar, node);
                }
                loop = node;
            }
            loop.isEndOfWord = true;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode loop = root;
            for (int i = 0; i < word.length(); i++) {
                Character currentChar = word.charAt(i);
                TrieNode node = loop.hashmap.get(currentChar);
                if (node == null) return false;
                loop = node;
            }
            return loop.isEndOfWord;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode loop = root;
            for (int i = 0; i < prefix.length(); i++) {
                Character currentChar = prefix.charAt(i);
                TrieNode node = loop.hashmap.get(currentChar);
                if (node == null) return false;
                loop = node;
            }
            return true;
        }
    }

    /**
     * 127. Word Ladder
     *
     * https://leetcode.com/problems/word-ladder/
     * */
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        wordList.add(endWord);

        Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);

        return -1;
    }
    public void addNeiggborWords(String word, Set<String> wordList, Queue<String> queue) {
        for (String string :wordList) {
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (string.contains("" + currentChar)) {
                    string.replaceFirst("" + currentChar, "");
                }
            }
        }
    }

    /**
     * 139. Word Break
     *
     * https://leetcode.com/problems/word-break/
     * */
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (wordDict.isEmpty()) return false;
        boolean []mem = new boolean[s.length() + 1];
        mem[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (mem[j] && wordDict.contains(s.substring(j, i))); {
                    mem[i] = true;
                    break;
                }
            }
        }
        return mem[s.length()];
    }
    public void testWordBreak() {
        Set<String> hashSet = new HashSet<String>();
        hashSet.add("a");
        wordBreak("a", hashSet);
    }

    /**
     * 239. Sliding Window Maximum
     *
     * https://leetcode.com/problems/sliding-window-maximum/
     * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int []ret = new int[nums.length - k + 1];
        int retIndex = 0;
        Deque<Integer> deque = new ArrayDeque<Integer>();
        for (int i = 0; i < nums.length; i++) {
            while(!deque.isEmpty() && deque.peek() < i - k + 1)
                deque.poll();
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast();
            deque.add(i);
            if (i >= k - 1) {
                ret[retIndex++] = nums[deque.peek()];
            }
        }
        return ret;
    }

    /**
     * 215. Kth Largest Element in an Array
     * Heap, Medium
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     * */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < 1) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        return null;
    }

    public class RandomizedSet {
        ArrayList<Integer> numsArray;
        HashMap<Integer, Integer> numIndexHashMap;
        Random random;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            numsArray = new ArrayList<Integer>();
            numIndexHashMap = new HashMap<Integer, Integer>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contains = numIndexHashMap.containsKey(val);
            if (contains) return false;
            numIndexHashMap.put(val, numsArray.size());
            numsArray.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            boolean contains = numIndexHashMap.containsKey(val);
            if (!contains) return false;
            int currentIndex = numIndexHashMap.get(val);
            if (currentIndex < numsArray.size() - 1) {
                int lastNum = numsArray.get(numsArray.size() - 1);
                numsArray.set(currentIndex, lastNum);
                numIndexHashMap.put(lastNum, currentIndex);
            }
            numsArray.remove(numsArray.size() - 1);
            numIndexHashMap.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return numsArray.get(random.nextInt(numsArray.size()));
        }
    }

    public class RandomizedCollection {
        ArrayList<Integer> numsArray;
        HashMap<Integer, HashSet<Integer>> numIndexHashMap;
        Random random;
        /** Initialize your data structure here. */
        public RandomizedCollection() {
            numsArray = new ArrayList<Integer>();
            numIndexHashMap = new HashMap<Integer, HashSet<Integer>>();
            random = new Random();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean contains = numIndexHashMap.containsKey(val);
            HashSet<Integer> set;
            set = contains? numIndexHashMap.get(val): new HashSet<Integer>();
            set.add(val);
            numIndexHashMap.put(val, set);
            numsArray.add(val);
            return contains? false: true;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            boolean contains = numIndexHashMap.containsKey(val);
            if (!contains) return false;
            Integer currentIndex = numIndexHashMap.get(val).iterator().next();
            numIndexHashMap.get(val).remove(currentIndex);
            if (currentIndex < numsArray.size() - 1 ) {
                int lastone = numsArray.get( numsArray.size()-1 );
                numsArray.set( currentIndex , lastone );
                numIndexHashMap.get(lastone).remove( numsArray.size()-1);
                numIndexHashMap.get(lastone).add(currentIndex);
            }
            numsArray.remove(numsArray.size() - 1);

            if (numIndexHashMap.get(val).isEmpty()) numIndexHashMap.remove(val);
            return true;
//            if (currentIndex < numsArray.size() - 1) {
//                int lastNum = numsArray.get(numsArray.size() - 1);
//                numsArray.set(currentIndex, lastNum);
//                numIndexHashMap.get(lastNum).remove(numsArray.size() - 1);
//                numIndexHashMap.get(lastNum).add(currentIndex);
//            }
//            numsArray.remove(numsArray.size() - 1);
//            numIndexHashMap.get(val).remove(currentIndex);
//            if (numIndexHashMap.get(val).isEmpty()) {
//                numIndexHashMap.remove(val);
//            }
//            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return numsArray.get(random.nextInt(numsArray.size()));
        }
    }

    /**
     * 200. Number of Islands
     * DFS, Medium
     * https://leetcode.com/problems/number-of-islands/
     * */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        int numOfIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '0') {
                    sinkIsland(grid, i, j);
                    numOfIslands++;
                }

            }
        }
        return numOfIslands;
    }
    public void sinkIsland(char [][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        sinkIsland(grid, i + 1, j);
        sinkIsland(grid,i, j + 1);
        sinkIsland(grid, i - 1, j);
        sinkIsland(grid, i, j - 1);
    }

    /**
     * 388. Longest Absolute File Path
     * Medium
     * https://leetcode.com/problems/longest-absolute-file-path/
     * */
    public int lengthLongestPath(String input) {
        if (input == null || input.length() < 1) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        int maxLength = 0;
        for (String item: input.split("\\n")) {
            int indentation = item.lastIndexOf("\\t") + 1;
            while (indentation + 1 < stack.size()) stack.pop();
            int lengthSoFar = item.length() + stack.peek() - indentation + 1;
            stack.push(lengthSoFar);
            if (item.contains(".")) maxLength = Math.max(maxLength, lengthSoFar - 1);
        }
        return maxLength;
    }


    /**
     * 218. The Skyline Problem
     * Hard
     * https://leetcode.com/problems/the-skyline-problem/
     * */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<int[]>();
        if (buildings.length < 1) return ret;
        BuildPoint[] buildPoints = new BuildPoint[buildings.length * 2];
        int index = 0;
        for(int [] building: buildings) {
            buildPoints[index] = new BuildPoint(building[0], true, building[2]);
            buildPoints[index + 1] = new BuildPoint(building[1], false, building[2]);
            index+=2;
        }
        Arrays.sort(buildPoints);
        PriorityQueue<Integer> set = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        set.add(0);
        int max = 0;
        for(BuildPoint point: buildPoints) {
            if (point.isStart) {
                set.add(point.height);
                if (set.peek() > max) {
                    max = set.peek();
                    int [] retPoint = new int[2];
                    retPoint[0] = point.x;
                    retPoint[1] = point.height;
                    ret.add(retPoint);
                }
            } else {
                set.remove(point.height);
                if (set.peek() < max) {
                    max = set.peek();
                    int [] retPoint = new int[2];
                    retPoint[0] = point.x;
                    retPoint[1] = max;
                    ret.add(retPoint);
                }
            }
        }
        return ret;
    }
    class BuildPoint implements Comparable<BuildPoint> {
        int x;
        boolean isStart;
        int height;
        public BuildPoint(int x, boolean isStart, int height){
            this.x = x;
            this.isStart = isStart;
            this.height = height;
        }

        @Override
        public int compareTo(BuildPoint o) {
            if (this.x != o.x) return this.x - o.x;
            if (this.isStart != o.isStart) return this.isStart? -1: 1;
            if (this.isStart) return o.height - this.height;
            else return this.height - o.height;
        }
    }
    public void testSkyline(){
        int [][]input = {{0,2,3},{2,5,3}};
        List<int[]> ret = getSkyline(input);
        for(int[] a: ret) {
            System.out.println(a[0] + "," + a[1]);
        }
    }

    /**
     * 89. Gray Code
     * Backtracking, Medium
     * https://leetcode.com/problems/gray-code/
     * */
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i / 2)^i);
        }
        return ret;
    }

    /**
     * 78. Subsets
     * Backtracking, Medium
     * https://leetcode.com/problems/subsets/
     * */
    public List<List<Integer>> subsets(int[] nums) {
        // Bit solution, O(2^n*n)
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        if (nums == null || nums.length < 1) return ret;
//        int numOfSubsets = 1 << nums.length;
//        for (int i = 0; i < numOfSubsets; i++) {
//            ArrayList<Integer> subset = new ArrayList<Integer>();
//            for (int j = 0; j < nums.length; j++) {
//                if (((1 << j) & i) != 0) {
//                    subset.add(nums[nums.length - 1 - j]);
//                }
//            }
//            ret.add(subset);
//        }
//        return ret;

        // Backtracking, O(n^2)
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        backtrack(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }
    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<Integer>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * 90. Subsets II
     * Backtracking, Medium
     * https://leetcode.com/problems/subsets/
     * */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrackDup(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }
    private void backtrackDup(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<Integer>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue;
            tempList.add(nums[i]);
            backtrackDup(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * 46. Permutations
     * Backtracking, Medium
     * https://leetcode.com/problems/permutations/
     * */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        backtrackPermute(nums, ret, list);
        return ret;
    }
    private void backtrackPermute(int []nums, List<List<Integer>> ret, ArrayList<Integer> temp) {
        if (temp.size() == nums.length) {
            ret.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) continue;
            temp.add(nums[i]);
            backtrackPermute(nums, ret, temp);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 47. Permutations II
     * Backtracking, Medium
     * https://leetcode.com/problems/permutations-ii/
     * */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums);
        backtrackPermuteUnipue(nums, ret, list, new boolean[nums.length]);
        return ret;
    }
    private void backtrackPermuteUnipue(int []nums, List<List<Integer>> ret, ArrayList<Integer> temp, boolean []used) {
        if (temp.size() == nums.length) {
            ret.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            temp.add(nums[i]);
            used[i] = true;
            backtrackPermuteUnipue(nums, ret, temp, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 39. Combination Sum
     * Backtracking, Medium
     * https://leetcode.com/problems/combination-sum/
     * */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(candidates);
        backtrackCombinationSum(candidates, target, 0, ret, list);
        return ret;
    }
    private void backtrackCombinationSum(int [] candidates, int target, int start, List<List<Integer>> ret, List<Integer> temp) {
        if (target < 0) return;
        else if (0 == target) {
            ret.add(new ArrayList<Integer>(temp));
            return;
        } else if (0 < target) {
            for (int i = start; i < candidates.length; i++) {
                temp.add(candidates[i]);
                backtrackCombinationSum(candidates, target - candidates[i], i, ret, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * 40. Combination Sum II
     * Backtracking, Medium
     * https://leetcode.com/problems/combination-sum-ii/
     * */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(candidates);
        backtrackCombinationSum2(candidates, target, 0, ret, list);
        return ret;
    }
    private void backtrackCombinationSum2(int [] candidates, int target, int start, List<List<Integer>> ret, List<Integer> temp) {
        if (target < 0) return;
        else if (0 == target) {
            ret.add(new ArrayList<Integer>(temp));
            return;
        } else if (0 < target) {
            for (int i = start; i < candidates.length; i++) {
                if(i > start && candidates[i] == candidates[i-1]) continue;
                temp.add(candidates[i]);
                backtrackCombinationSum2(candidates, target - candidates[i], i + 1, ret, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * 216. Combination Sum III
     * Backtracking, Medium
     * https://leetcode.com/problems/combination-sum-iii/
     * */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        boolean [] marks = new boolean[10];
        backtrackCombinationSum3(k, n, 1, ret, temp, marks);
        return ret;
    }
    private void backtrackCombinationSum3(int k, int n, int start, List<List<Integer>> ret, List<Integer> temp, boolean[]marks) {
        if (n < 0 || temp.size() > k) return;
        else if (n == 0 && temp.size() == k) ret.add(new ArrayList<Integer>(temp));
        else {
            for (int i = start; i <= 9; i++) {
                if (marks[i] == true) continue;
                temp.add(i);
                marks[i] = true;
                backtrackCombinationSum3(k, n - i, i + 1, ret, temp, marks);
                temp.remove(temp.size() - 1);
                marks[i] = false;
            }
        }
    }

    /**
     * 377. Combination Sum IV
     * Backtracking, Medium
     * https://leetcode.com/problems/combination-sum-iv/
     * */
    public int combinationSum4(int[] nums, int target) {
        // Backtracking
//        List<List<Integer>> ret = new ArrayList<List<Integer>>();
//        List<Integer> temp = new ArrayList<Integer>();
//        backtrackCombinationSum4(nums, target, ret, temp);
//        return ret.size();

        // DP
        Arrays.sort(nums);
        int []mem = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] > i) break;
                else if (nums[j] == i) mem[i]++;
                else mem[i] += mem[i - nums[j]];
            }
        }
        return mem[target];
    }
    private void backtrackCombinationSum4(int[] nums, int target, List<List<Integer>> ret, List<Integer> temp) {
        if (target < 0) return;
        else if (target == 0) ret.add(new ArrayList<Integer>(temp));
        else {
            for (int i = 0; i < nums.length; i++) {
                temp.add(nums[i]);
                backtrackCombinationSum4(nums, target - nums[i], ret, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * 77. Combinations
     * Backtracking, Medium
     * https://leetcode.com/problems/combinations/
     * */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        backtrackCombine(n, k, ret, temp, 1);
        return ret;
    }
    private void backtrackCombine(int n, int k, List<List<Integer>> ret, List<Integer> temp, int start) {
        if (temp.size() == k) {
            ret.add(new ArrayList<Integer>(temp));
        } else {
            for (int i = start; i <= n; i++) {
                temp.add(i);
                backtrackCombine(n, k, ret, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /**
     * 17. Letter Combinations of a Phone Number
     * Backtracking, Medium
     * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
     * */
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        if (digits == null || digits.isEmpty()) return ret;
        List<Character> temp = new ArrayList<Character>();
        backtrackLetterCombinations(digits, ret, temp, 0);
        return ret;
    }
    private void backtrackLetterCombinations(String ditgits, List<String> ret, List<Character> temp, int start) {
        if (temp.size() == ditgits.length()) {
            StringBuilder builder = new StringBuilder();
            for(Character c: temp) {
                builder.append(c);
            }
            ret.add(builder.toString());
        }
        else {
            for (int i = start; i < ditgits.length(); i++) {
                char currentDigit = ditgits.charAt(i);
                if (currentDigit == '1') continue;
                else {
                    for (Character currentChar: getCharForDigit(currentDigit)) {
                        temp.add(currentChar);
                        backtrackLetterCombinations(ditgits, ret, temp, i + 1);
                        temp.remove(temp.size() - 1);
                    }
                }
            }
        }
    }
    private List<Character> getCharForDigit(char digit) {
        switch (digit) {
            case '2':
                return new ArrayList<Character>(Arrays.asList('a', 'b', 'c'));
            case '3':
                return new ArrayList<Character>(Arrays.asList('d', 'e', 'f'));
            case '4':
                return new ArrayList<Character>(Arrays.asList('g', 'h', 'i'));
            case '5':
                return new ArrayList<Character>(Arrays.asList('j', 'k', 'l'));
            case '6':
                return new ArrayList<Character>(Arrays.asList('m', 'n', 'o'));
            case '7':
                return new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's'));
            case '8':
                return new ArrayList<Character>(Arrays.asList('t', 'u', 'v'));
            case '9':
                return new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z'));
            case '*':
                return new ArrayList<Character>(Arrays.asList('*'));
            case '#':
                return new ArrayList<Character>(Arrays.asList('#'));
            case '0':
                return new ArrayList<Character>(Arrays.asList(' '));
        }
        return null;
    }

    /**
     * 401. Binary Watch
     * Backtracking, Easy
     * https://leetcode.com/problems/binary-watch/
     * */
    public List<String> readBinaryWatch(int num) {
        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    ret.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return ret;
    }

    /**
     * 131. Palindrome Partitioning
     * Backtracking, Medium
     * https://leetcode.com/problems/palindrome-partitioning/
     * */
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> temp = new ArrayList<String>();
        backtrackPartition(s, ret, temp, 0);
        return ret;
    }
    private void backtrackPartition(String string, List<List<String>> ret, List<String> temp, int start) {
        if (start == string.length()) {
            ret.add(new ArrayList<String>(temp));
        }
        for (int i = start; i < string.length(); i++) {
            if (isPalindromeString(string, start, i)) {
                temp.add(string.substring(start, i + 1));
                backtrackPartition(string, ret, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
    private boolean isPalindromeString(String s, int low, int high) {
        while(low < high)
            if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    /**
     * 51. N-Queens
     * Backtracking, Hard
     * https://leetcode.com/problems/n-queens/
     * */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<List<String>>();
//        List<String> temp = new ArrayList<String>();
        char [][] temp = new char[n][n];
        int []qPosition = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(temp[i], '.');
        }
        backtrackNQueens(n, 0, temp, ret, qPosition);
        return ret;
    }
    public void backtrackNQueens(int n, int row, char [][] temp, List<List<String>> ret, int []qPosition) {
        if (row == n) {
            List<String> solution = new ArrayList<String>();
            for (int i = 0; i < temp.length; i++) {
                solution.add(String.valueOf(temp[i]));
            }
            ret.add(new ArrayList<String>(solution));
        } else {
            for (int col = 0; col < n; col++) {
                boolean canAdd = true;
                for (int j = 0; j < row; j++) {
                    //if break the rule, set to false
                    int qRow = j, qCol = qPosition[qRow];
                    if (qCol == col || qCol + qRow == col + row || qCol - qRow == col - row)
                        canAdd = false;
                }
                if (canAdd) {
                    temp[row][col] = 'Q';
                    qPosition[row] = col;
                    backtrackNQueens(n, row + 1, temp, ret, qPosition);
                    temp[row][col] = '.';
                    qPosition[row] += 1;
                }
            }
        }
    }
    public void testSolveNQueen() {
        List<List<String>> ret = solveNQueens(4);
        System.out.println(ret);
    }

    /**
     * 52. N-Queens II
     * Backtracking, Hard
     * https://leetcode.com/problems/n-queens-ii/
     * */
    public int totalNQueens(int n) {
        List<List<String>> ret = new ArrayList<List<String>>();
        char [][] temp = new char[n][n];
        int []qPosition = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(temp[i], '.');
        }
        backtrackNQueens(n, 0, temp, ret, qPosition);
        return ret.size();
    }













































}
