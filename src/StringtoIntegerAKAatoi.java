import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by WujaeSebas on 2018/7/25.
 * <p>
 * 实现 atoi，将字符串转为整数。
 * <p>
 * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
 * <p>
 * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
 * <p>
 * 若函数不能执行有效的转换，返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2<sup>31</sup>,  2<sup>31</sup> − 1]。如果数值超过可表示的范围，则返回  INT_MAX (2<sup>31</sup> − 1) 或 INT_MIN (−2<sup>31</sup>) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * <p>
 * 输出: 42
 * <p>
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * <p>
 * 输出: -42
 * <p>
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * <p>
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * <p>
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * <p>
 * 输出: 4193
 * <p>
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * <p>
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * <p>
 * 输出: 0
 * <p>
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * <p>
 * 输出: -2147483648
 * <p>
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−2<sup>31</sup>) 。
 */
public class StringtoIntegerAKAatoi {

    static class Solution {
        public int myAtoi(String str) {

            if(str == null || str.length() == 0){
                return  0;
            }

            char[] chars = str.toCharArray();

            int blankIndex = 0; boolean isSmallThan0 = false; boolean signFlag = false;
            int numberStart = chars.length, numberEnd = 0, cursor=0;
            for (char aChar : chars) {

                if(aChar == ' '){
                    if(blankIndex++ == cursor++)
                        continue;
                    else{
                        break;
                    }
                }
                else if(aChar == '+'){ //
                    if(!signFlag) signFlag  = true;
                    else break;
                }
                else if(aChar == '-'){ //
                    if(!isSmallThan0 && !signFlag){
                        isSmallThan0 = true;
                        signFlag = true;
                    }
                    else break;
                }
                else if(Character.isDigit(aChar)){ // '0'-'9'
                    signFlag = true;
                    numberStart = Math.min(numberStart, cursor);
                    numberEnd = Math.max(numberEnd, cursor);
                }else{
                    break;
                }
                cursor++;
            }
            int rev = 0;
            //利用了ReverseInteger的判断是否溢出的方法
            for (int i = numberStart; i <= numberEnd; i++) {

                int pop = chars[i] - '0';
                if(isSmallThan0){
                    if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop > 8)) return Integer.MIN_VALUE;
                    rev = rev * 10 - pop;

                }else{
                    if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return Integer.MAX_VALUE;
                    rev = rev * 10 + pop;
                }

            }

            return rev;
        }

        //思路： 找到第一个数字就可以了
        public int bestMyAtoi(String str) {
            int index = 0;
            long out = 0;
            boolean positive = true;
            while (index < str.length() && str.charAt(index) == ' '){
                ++index;
            }
            if (index < str.length() && str.charAt(index) == '-'){
                positive = false;
                ++index;
            }
            else if (index < str.length() && str.charAt(index) == '+'){
                ++index;
            }
            while (index < str.length() && Character.isDigit(str.charAt(index))){
                out = out * 10 + str.charAt(index) - '0';
                if (out > Integer.MAX_VALUE){
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                ++index;
            }
            return positive ? (int) out : (int) -out;
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
            String str = stringToString(line);

            int ret = new Solution().myAtoi(str);

            String out = String.valueOf(ret);

            System.out.println(out);
        }
    }

}
