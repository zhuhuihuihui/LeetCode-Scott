/**
 * Created by Scott on 1/14/16.
 */
public class Solution {

    public static void main(String[] args) {


    }

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
}
