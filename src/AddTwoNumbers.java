import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by WujaeSebas on 2018/7/24.
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AddTwoNumbers {


    private static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode ptr1 = l1;
            ListNode ptr2 = l2;

            int sumVal = 0;

            sumVal = ptr1.val + ptr2.val;

            ListNode dummyRoot = new ListNode(0);
            ListNode ptr = dummyRoot;

            while(ptr1.next != null || ptr2.next != null){

                ptr.next = new ListNode(sumVal%10);
                ptr = ptr.next;

                if(sumVal >= 10){
                    sumVal = 1;
                }else{
                    sumVal= 0;
                }

                if(ptr1.next != null){

                    ptr1 = ptr1.next;
                    sumVal += ptr1.val;
                }

                if(ptr2.next != null){
                    ptr2 = ptr2.next;
                    sumVal += ptr2.val;
                }


            }

            ptr.next = new ListNode(sumVal%10);

            if(sumVal >= 10){
                ptr = ptr.next;
                ptr.next = new ListNode(1);

            }

            return dummyRoot.next;
        }
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

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = stringToListNode(line);
            line = in.readLine();
            ListNode l2 = stringToListNode(line);

            ListNode ret = new Solution().addTwoNumbers(l1, l2);

            String out = listNodeToString(ret);

            System.out.print(out);
        }

    }

    public static class ListNode
    {
        int val;
        ListNode next;

        public ListNode(int x){
            val=x;
        }

    }
}
