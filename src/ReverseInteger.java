import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by WujaeSebas on 2018/7/25.
 * <p>
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * <p>
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * <p>
 * 注意:
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 */
public class ReverseInteger {

    static class Solution {
        //这个解法本身其实是不符合题目要求的！
        public int reverse(int x) {


            try {
                if (x < 0) {

                    return Integer.parseInt("-" + new StringBuilder(String.valueOf(-x)).reverse().toString());

                } else {

                    return Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString());

                }
            }catch (NumberFormatException e){
                return 0;
            }

        }

        public int bestReverse(int x) {
            int rev = 0;
            while (x != 0) {
                int pop = x % 10;
                x /= 10;
                if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
                if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
                rev = rev * 10 + pop;
            }
            return rev;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);

            int ret = new Solution().reverse(x);

            String out = String.valueOf(ret);

            System.out.print(out);
        }

    }
}
