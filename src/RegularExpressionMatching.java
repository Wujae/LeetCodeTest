import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WujaeSebas on 2018/7/26.
 * <p>
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符。
 * <p>
 * '*' 匹配零个或多个前面的元素。
 * <p>
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * <p>
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * <p>
 * p = "a"
 * <p>
 * 输出: false
 * <p>
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * s = "aa"
 * <p>
 * p = "a*"
 * <p>
 * 输出: true
 * <p>
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * <p>
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * <p>
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * <p>
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * <p>
 * p = "c*a*b"
 * <p>
 * 输出: true
 * <p>
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 示例 5:
 * <p>
 * 输入:
 * <p>
 * s = "mississippi"
 * <p>
 * p = "mis*is*p*."
 * <p>
 * 输出: false
 */
public class RegularExpressionMatching {

    static class Solution {
        public boolean isMatch(String s, String p) {

            if (s == null || p == null) return false;

            char[] patternCharArray = p.toCharArray();
            //确定有几段匹配原则
            List<String> pattens = new ArrayList<String>();
            char lastPc = '*';
            for (char c : patternCharArray) {
                if (c == '*') {
                    if (c == lastPc) return false;
                    else {
                        pattens.set(pattens.size() - 1, pattens.get(pattens.size() - 1) + "*");
                    }
                } else if (c == '.' || c == ' ') {
                    pattens.add(String.valueOf(c));
                } else if (Character.isLowerCase(c)) {
                    pattens.add(String.valueOf(c));
                } else {
                    return false;
                }
                lastPc = c;
            }

            System.out.println(pattens);

            return checkSubPath(s, pattens);
        }

        //递归验证子串
        public boolean checkSubPath(String str, List<String> patterns) {

            if (str.length() == 0 && patterns.size() == 0) {
                return true;
            } else if (patterns.size() == 0) {
                return false;
            }

            String headPattern = patterns.get(0);
            List<String> subPatterns = patterns.subList(1, patterns.size());

            if (headPattern.length() > 1) {
                int max = 0;
                boolean result = false;
                char pattenChar = headPattern.charAt(0);
                if (pattenChar == '.') {//.*
                    max = str.length();
                } else {//[a-z]*
                    while (str.length() > max && pattenChar == str.charAt(max)){
                        max++;
                    }
                }

                //[a-z]{1,}
                int si = 0;
                while (si <= max) {
                    System.out.println(str.substring(si) + "-->" + subPatterns);
                    result = result || checkSubPath(str.substring(si), subPatterns);
                    si++;
                }

                return result;

            } else if (headPattern.equals(".")) {
                if (str.length() == 0 || !Character.isLowerCase(str.charAt(0))) return false;

                return checkSubPath(str.substring(1), subPatterns);

            } else {
                if (str.length() == 0 || headPattern.charAt(0) != str.charAt(0)) return false;

                return checkSubPath(str.substring(1), subPatterns);
            }
        }
    }

    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);
            line = in.readLine();
            String p = stringToString(line);

            boolean ret = new Solution().isMatch(s, p);

            String out = booleanToString(ret);

            System.out.println(out);
        }
    }

}
