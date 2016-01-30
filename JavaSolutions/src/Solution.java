import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Scott on 1/14/16.
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.romanToInt("MDCCC");
        System.out.println(result);
    }

    /**14. Longest Common Prefix
     * String, Easy
     * https://leetcode.com/problems/longest-common-prefix/
     * */
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs.length > 1) {
            long shortestStringLength = Long.MAX_VALUE;
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() < shortestStringLength) {
                    shortestStringLength = strs[i].length();
                }
            }
            if (shortestStringLength < 1) return result;

            for (int i = 0; i < shortestStringLength; i++) {
                char sameChar = strs[0].charAt(i);
                for (int j = 0; j < strs.length; j++) {
                    if (strs[j].charAt(i) != sameChar) {
                        return result;
                    }
                }
                result+=sameChar;
            }
        } else if (strs.length == 1) {
            return strs[0];
        }
        return result;
    }

    /**
     * 67. Add Binary
     * String, Easy
     * https://leetcode.com/problems/add-binary/
     * */
    public String addBinary(String a, String b) {
        String result = "";
        if ((a == null || a.isEmpty()) && (b == null || b.isEmpty())) return result;
        if (a == null || a.isEmpty()) return b;
        else if (b == null || b.isEmpty()) return a;

        int aLength = a.length();
        int bLength = b.length();
        int maxLength = aLength;
        if (aLength > bLength) {
            for (int i = 0; i < Math.abs(aLength - bLength); i++) {
                b = '0' + b;
            }
        } else {
            maxLength = bLength;
            for (int i = 0; i < Math.abs(aLength - bLength); i++) {
                a = '0' + a;
            }
        }


        int add = 0;
        for (int i = 0; i < a.length(); i++) {
            char charAtThisIndex = '0';
            int sumAtThisIndex = a.charAt(maxLength - 1 - i) - 48 + b.charAt(maxLength - 1 - i) - 48 + add;
            if (sumAtThisIndex > 2) {
                add = 1;
                charAtThisIndex = '1';
            } else if (sumAtThisIndex > 1) {
                add = 1;
                charAtThisIndex = '0';
            } else if (sumAtThisIndex > 0) {
                add = 0;
                charAtThisIndex = '1';
            } else {
                add = 0;
                charAtThisIndex = '0';
            }
            result = charAtThisIndex + result;
        }
        if (add > 0) {
            result = "1" + result;
        }
        return result;
    }

    /**
     * 20. Valid Parentheses
     * String, Easy
     * https://leetcode.com/problems/valid-parentheses/
     **/
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) return false;

        Stack<Character> charStack = new Stack<Character>();
        charStack.push('0');
        for (int i = 0; i < s.length(); i++) {
            Character charAtThisIndex = s.charAt(i);
            switch (charStack.peek()) {
                case '(':
                    if (charAtThisIndex == ')') charStack.pop();
                    else charStack.push(charAtThisIndex);
                    break;
                case '{':
                    if (charAtThisIndex == '}') charStack.pop();
                    else charStack.push(charAtThisIndex);
                    break;
                case '[':
                    if (charAtThisIndex == ']') charStack.pop();
                    else charStack.push(charAtThisIndex);
                    break;
                default:
                    charStack.push(charAtThisIndex);
                    break;
            }
        }
        return charStack.peek() == '0';
    }

    /**
     * 165. Compare Version Numbers
     * String, Easy
     * https://leetcode.com/problems/compare-version-numbers/
     **/
    public int compareVersion(String version1, String version2) {
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int minLength = version1Array.length > version2Array.length ? version2Array.length : version1Array.length;

        for (int i = 0; i < minLength; i++) {
            if (Integer.valueOf(version1Array[i]) > Integer.valueOf(version2Array[i])) {
                return 1;
            } else if (Integer.valueOf(version1Array[i]) < Integer.valueOf(version2Array[i])) {
                return -1;
            }
        }

        if (version1Array.length > version2Array.length) {
            for (int i = minLength; i < version1Array.length; i++) {
                if (Integer.valueOf(version1Array[i]) != 0) return 1;
            }
        } else {
            for (int i = minLength; i < version2Array.length; i++) {
                if (Integer.valueOf(version2Array[i]) != 0) return -1;
            }
        }
        return 0;
    }

    /**
     * 38. Count and Say
     * String, Easy
     * https://leetcode.com/problems/count-and-say/
     * */
    public String countAndSay(int n) {
        if (n < 1) return "";

        String currentString = "1";
        for (int j = 2; j <= n; j++) {
            String result = "";
            Character currentDigit = currentString.charAt(0);
            int numOfCurrentDigit = 1;

            for (int i = 1; i < currentString.length(); i++) {
                if (currentDigit != currentString.charAt(i)) {
                    result += (String.valueOf(numOfCurrentDigit) + currentDigit);
                    currentDigit = currentString.charAt(i);
                    numOfCurrentDigit = 1;
                } else {
                    numOfCurrentDigit++;
                }
            }
            result += (String.valueOf(numOfCurrentDigit) + currentDigit);
            currentString = result;
        }
        return currentString;
    }

    /**
     * 6. ZigZag Conversion
     * String, Easy
     * https://leetcode.com/problems/zigzag-conversion/
     * */
    public String convert(String s, int numRows) {
        String result = "";
        int stringLength = s.length();
        int indexInTheRow = 0;
        while ((2 * indexInTheRow * (numRows - 1)) < stringLength) {
            result += s.charAt(2 * indexInTheRow * (numRows - 1));
            indexInTheRow++;
        }
//        for (int i = 0; i < numRows; i++) {
//            while ()
//        }
        return result;
    }

    /**
     * 125. Valid Palindrome
     * String, Easy
     * https://leetcode.com/problems/valid-palindrome/
     * */
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        int leftCursor = 0, rightCursor = s.length() - 1;
        while (leftCursor < rightCursor) {
            char leftChar = s.charAt(leftCursor), rightChar = s.charAt(rightCursor);
            if (isAlphanumeric(leftChar) && isAlphanumeric(rightChar)) {
                if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                    return false;
                }
                leftCursor++;
                rightCursor--;
            }

            if (!isAlphanumeric(leftChar)) {
                leftCursor++;
            }
            if (!isAlphanumeric(rightChar)) {
                rightCursor--;
            }
        }
        return true;
    }

    public boolean isAlphanumeric(char character) {
        return Character.isAlphabetic(character) || Character.isDigit(character);
    }

    /**
     * 8. String to Integer (atoi)
     * String, Easy
     * https://leetcode.com/problems/string-to-integer-atoi/
     * */
    public int myAtoi(String str) {
        int p = 0, ret = 0;
        while(p <str.length() && Character.isWhitespace(str.charAt(p))) ++p;
        if(p == str.length()) return 0;
        boolean negFlag = (str.charAt(p) == '-');
        if(str.charAt(p) == '+'  || str.charAt(p) == '-') ++p;
        for(; p<str.length(); ++p) {
            if(str.charAt(p) > '9' || str.charAt(p) < '0') {
                break;
            }else {
                int digit = str.charAt(p) - '0';
                if(!negFlag && ret > (Integer.MAX_VALUE - digit) /10) return Integer.MAX_VALUE;
                else if(negFlag && ret < (Integer.MIN_VALUE + digit)/10) return Integer.MIN_VALUE;
                ret = ret * 10 + (negFlag? -digit: digit);
            }
        }
        return ret;

    }

    /**
     * 13. Roman to Integer
     * String, Easy
     * https://leetcode.com/problems/roman-to-integer/
     * */
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) return 0;

        int result = 0;
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

    /**
     * 28. Implement strStr()
     * String, Easy
     * https://leetcode.com/problems/implement-strstr/
     * */
    public int strStr(String haystack, String needle) {

    }
}
