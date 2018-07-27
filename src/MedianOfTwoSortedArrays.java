import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * Created by WujaeSebas on 2018/7/24.
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * <p>
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 中位数是 2.0
 * <p>
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 中位数是 (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int result[];
            int m = nums1.length, n = nums2.length;
            int count = m + n - 1;
            result = new int[m+n];
            System.arraycopy(nums1,0, result,0,m);
            --m; --n;

            while (m >= 0 && n >= 0) result[count--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
            while (n >= 0) result[count--] = nums2[n--];

            if(result.length % 2 == 1){
                return result[result.length / 2];
            }else{
                return (result[result.length/2-1] + result[result.length/2]) / 2.0;
            }
        }

        //[1,3,4,5,6,23,35] [2,4,6,7,9]
        //1,2,3,3,4,5,6,6,7,9,23,35
    }

    public static String integerArrayToString(int[] nums) {

        String result = "";
        for(int index = 0; index < nums.length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String doubleToString(Double input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);

            double ret = new Solution().findMedianSortedArrays(nums1, nums2);

            String out = doubleToString(ret);

            System.out.print(out);
        }
    }

}
