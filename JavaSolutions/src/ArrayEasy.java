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
        int result[] = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
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












































































}
