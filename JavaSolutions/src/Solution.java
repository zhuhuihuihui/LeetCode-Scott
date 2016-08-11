import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Scott on 1/14/16
 */
public class Solution {

    public static void main(String[] args) {
        ArrayEasy solution = new ArrayEasy();
        char [][] testData = {".87654321".toCharArray(),"2........".toCharArray(),"3........".toCharArray(),
                "4........".toCharArray(),"5........".toCharArray(),"6........".toCharArray()
                ,"7........".toCharArray(),"8........".toCharArray(),"9........".toCharArray()};
        String ret = solution.convertToTitle(26);
        System.out.println(ret);
    }
}
