import java.util.*;

/**
 * Created by Scott on 25/09/2016.
 */
public class Coursera {



























    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a;
        a = in.nextInt();
        int b;
        b = in.nextInt();
        int sum;
        sum = solveMeFirst(a, b);
        System.out.println(sum);
    }
    static int solveMeFirst(int a, int b) {
        // Hint: Type return a+b; below
        return a + b;
    }

//    public class Solution {
//
//        static int countSum(int [] nums) {
//            int sum = 0;
//            for(int i = 0; i < nums.length; i++) {
//                sum+=nums[i];
//            }
//            return sum;
//        }
//
//        public static void main(String[] args) {
//            Scanner in = new Scanner(System.in);
//            int size;
//            size = in.nextInt();
//            int [] b = new int[size];
//            for(int i = 0; i < size; i++) {
//                b[i] = in.nextInt();
//            }
//            int sum;
//            sum = countSum(b);
//            System.out.println(sum);
//        }
//    }








    /**
     * 输入：
     int[] numbers, int k
     输出：
     numbers乘积小于k的subarray个数
     * */
    public int numOfSubarraysProductLessThanK(int [] numbers, int k) {
        // O(n2) solution
//        if (numbers == null || numbers.length < 1) return 0;
//        int ret = 0;
//        long [] products = new long[numbers.length];
//        long product = 1;
//        for (int i = 0; i < numbers.length; i++) {
//            product *= numbers[i];
//            products[i] = product;
//            if (product < k) ret++;
//            for (int j = 0; j < i; j++) {
//                long divid = products[i] / products[j];
//                if ((k - divid) > 0) ret++;
//            }
//        }
//        return ret;

        // O(nlogn) solution
        if (numbers == null || numbers.length < 1) return 0;
        int ret = 0;
        TreeSet<Long> set = new TreeSet<Long>();
        long product = 1;
        for (int i = 0; i < numbers.length; i++) {
            product *= numbers[i];
            if (product < k) ret++;
            long divid = (long) Math.floor(product*1.0 / k*1.0);
            Set<Long> lessThanSet = set.tailSet(divid, false);
            ret += lessThanSet.size();
            set.add(product);
        }
        return ret;
    }
    public void testNumOfSubarraysProductLessThanK() {
        int []test = {1, -2, 3, -4, 5};
        int ret = numOfSubarraysProductLessThanK(test, 7);
        System.out.println(ret);
    }





    /**
     * Royal Names
     *
     * */
    public String[] getSortedList(String [] nameStrings) {
        ArrayList<RoyalName> royalNames = new ArrayList<RoyalName>();
        for (int i = 0; i < nameStrings.length; i++) {
            royalNames.add(new RoyalName(nameStrings[i]));
        }
        royalNames.sort(new Comparator<RoyalName>() {
            @Override
            public int compare(RoyalName o1, RoyalName o2) {
                if (o1.firstName.contentEquals(o2.firstName)) {
                    return o1.getRomanNumber() - o2.getRomanNumber();
                } else {
                    return o1.firstName.compareTo(o2.firstName);
                }
            }
        });

        String [] ret = new String[nameStrings.length];
        for (int i = 0; i < royalNames.size(); i++) {
            ret[i] = royalNames.get(i).toString();
        }
        return ret;
    }
    private class RoyalName {
        String firstName;
        String romanNumberString;
        String royalName;
        public RoyalName(String royalName) {
            this.royalName = royalName;
            String []split = royalName.split(" ");
            this.firstName = split[0];
            if (split.length > 1) {
                this.romanNumberString = split[1];
            } else {

            }
        }

        @Override
        public String toString() {
            return royalName;
        }

        public int getRomanNumber() {
            if (this.romanNumberString == null || this.romanNumberString.isEmpty()) return 0;
            int result = 0;
            String s = this.romanNumberString;
            s = s.toUpperCase();
            s = s.replaceAll("IV", "IIII");
            s = s.replaceAll("IX", "VIIII");
            s = s.replaceAll("XL", "XXXX");
            s = s.replaceAll("XC", "LXXXX");
            s = s.replaceAll("CD", "CCCC");
            s = s.replaceAll("CM", "DCCCC");

            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case 'M':
                        result+= 1000;
                        break;
                    case 'D':
                        result+= 500;
                        break;
                    case 'C':
                        result+= 100;
                        break;
                    case 'L':
                        result+= 50;
                        break;
                    case 'X':
                        result+= 10;
                        break;
                    case 'V':
                        result+= 5;
                        break;
                    case 'I':
                        result+= 1;
                        break;
                    default:
                        break;
                }
            }

            return result;
        }
    }
    public void testRoyalName() {
        String[] test = {"Louis IX", "A", "Louis VIII"};
        String [] ret = getSortedList(test);
        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }
    }

    /**
     * Array Game
     * */
    public int countMoves(int []nums) {
        if (nums == null || nums.length < 1) return 0;
        Arrays.sort(nums);
        int ret = 0;
        while (nums[0] != nums[nums.length - 1]) {
            for (int i = 0; i < nums.length - 1; i++) {
                nums[i] += 1;
            }
            int last = nums.length - 1;
            while (nums[last] < nums[last - 1]) {
                int temp = nums[last];
                nums[last] = nums[last - 1];
                nums[last - 1] = temp;
                last--;
            }
            ret++;
        }
        return ret;
    }
    public void testCountMoves() {
        int[] test = {5,5,6,8,8,5};
        int ret = countMoves(test);
        System.out.println(ret);
    }

    /**
     * Task Master
     * */
    public int tasks(int n, int[] a, int[] b) {
       return 0;
    }

    /**
     * Split the Pixels
     * */
    public String findClosestPureColor(String pixel) {
        if (pixel == null || pixel.length() != 24) return null;
        String red = pixel.substring(0, 8);
        String green = pixel.substring(8, 16);
        String blue = pixel.substring(16);
        boolean []rgb = new boolean[3];
        rgb[0] = red.startsWith("1")? true: false;
        rgb[1] = green.startsWith("1")? true: false;
        rgb[2] = blue.startsWith("1")? true: false;
        String ret;
        if (rgb[0] && rgb[1] && rgb[2]) ret = "White";
        else if (!rgb[0] && !rgb[1] && !rgb[2]) ret = "Black";
        else if (true) return "";
        return "";
    }




























































}
