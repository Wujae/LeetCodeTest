import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by WujaeSebas on 2018/7/24.
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestPalindromicSubstring {

    static class Solution {
        public String longestPalindrome(String s) {

            int resultMiddle = 0, delta = 0;
            boolean oddMatch = true;
            for (int middle = 0; middle < s.length(); middle++) {

                //奇
                int i = 0;
                while (middle - i >= 0 && middle + i < s.length()) {
                    if (s.charAt(middle - i) == s.charAt(middle + i)) {
                        i++;
                    } else {

                        break;
                    }
                }

                //偶
                int j = 0;
                while (middle - j >= 0 && middle + 1 + j < s.length()) {
                    if (s.charAt(middle - j) == s.charAt(middle + 1 + j)) {
                        j++;
                    } else {

                        break;
                    }
                }


                if (2 * (i - 1) + 1 > 2 * j) {
                    if (oddMatch) {
                        if (delta < i - 1) {
                            delta = i - 1;
                            resultMiddle = middle;
                        }

                    } else {
                        if ((delta + 1) * 2 < 2 * (i - 1) + 1) {
                            delta = i - 1;
                            resultMiddle = middle;
                            oddMatch = true;
                        }
                    }

                } else {

                    if (oddMatch) {
                        if (delta * 2 + 1 < 2 * j) {
                            delta = j - 1;
                            resultMiddle = middle;
                            oddMatch = false;
                        }


                    } else {
                        if (delta < j - 1) {
                            delta = j - 1;
                            resultMiddle = middle;
                        }
                    }

                }
            }

            System.out.println(oddMatch);
            System.out.println(resultMiddle);
            System.out.println(delta);
            return s.substring(resultMiddle - delta, delta + resultMiddle + 1 + (oddMatch ? 0 : 1));
        }
    }

    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            String ret = new Solution().longestPalindrome(s);

            String out = (ret);

            System.out.println(out);
        }
    }

}
