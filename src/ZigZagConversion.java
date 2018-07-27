import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by WujaeSebas on 2018/7/25.
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 * <pre>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * </pre>
 * 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 * <p>
 * 实现一个将字符串进行指定行数变换的函数:
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "PAYPALISHIRING", numRows = 3
 * <p>
 * 输出: "PAHNAPLSIIGYIR"
 * <p>
 * 示例 2:
 * <p>
 * 输入: s = "PAYPALISHIRING", numRows = 4
 * <p>
 * 输出: "PINALSIGYAHRPI"
 * <p>
 * 解释:
 * <pre>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * </pre>
 */
public class ZigZagConversion {

    static class Solution {
        public String convert(String s, int numRows) {

            if (numRows < 2 || s == null || s.length() == 0) {
                return s;
            }

            StringBuilder sb = new StringBuilder("");

            int coefficient = (numRows - 1) * 2;
            int sLength = s.length();

            //考录到numRows = 5 这种结尾翘边的情况多加出1
            int nMax = sLength / coefficient + 1;
            System.out.println(coefficient);
            System.out.println(nMax);

            for (int i = 0; i < numRows; i++) {
                int j = 0;
                while (j <= nMax) {

                    if (!(i == 0 || i == numRows - 1) && j * coefficient - i > 0 && j * coefficient - i < sLength)
                        sb.append(s.charAt(j * coefficient - i));
                    if (j * coefficient + i < sLength)
                        sb.append(s.charAt(j * coefficient + i));

                    j++;

                }
            }

            return sb.toString();


            //x = 2 * (numRows - 1)
            //    2 * (numRows - 1) +/- i
            //numRows = 2
            //PAYPALISHIRING
            //P Y A I H R N   2n
            // A P L S I I G

            //numRows = 3
            //PAYPALISHIRING
            //P   A   H   N   4n
            // A P L S I I G
            //  Y   I   R

            //numRows = 4
            //PAYPALISHIRING
            //P     I     N   6n
            // A   L S   I G
            //  Y A   H R
            //   P     I

            //numRows = 5
            //PAYPALISHIRING
            //P       H       8n
            // A     S I
            //  Y   I   R
            //   P L     I G
            //    A       N

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
            line = in.readLine();
            int numRows = Integer.parseInt(line);

            String ret = new Solution().convert(s, numRows);

            System.out.println(ret);
        }
    }

}
