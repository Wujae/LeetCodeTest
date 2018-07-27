import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by WujaeSebas on 2018/7/24.
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例：
 * <p>
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * <p>
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * <p>
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    static class Solution {
        public int lengthOfLongestSubstring(String s) {

            if(s == null || s.length() == 0) return 0;

            int maxSize = 0;

            //ohomm
            LinkedHashSet<Character> charSet = new LinkedHashSet<Character>();

            char[] chars = s.toCharArray();
            for (char achar: chars) {
                int orgSize = charSet.size();
                charSet.add(achar);
                int newSize = charSet.size();

                //变大表示无重复
                if(newSize > orgSize){
                    maxSize = Math.max(newSize, maxSize);

                }else{
                    boolean isNfe = false;
                    Iterator<Character> charSetIter = charSet.iterator();
                    while(charSetIter.hasNext()){
                        char j= charSetIter.next();

                        if(isNfe) continue;

                        if(j == achar){
                            isNfe = true;
                        }
                        charSetIter.remove();
                    }
                    charSet.add(achar);
                }
            }

            return maxSize;
        }

        public int simple10(String s){
            int n = s.length(), ans = 0;
            int[] index = new int[10]; // current index of character
            // try to extend the range [i, j]
            //832132324
            // c j i ans index
            // 8 0 0 1   [0,0,0,0,0,0,0,0,1,0]
            // 3 1 0 2   [0,0,0,2,0,0,0,0,1,0]
            // 2 2 0 3   [0,0,3,2,0,0,0,0,1,0]
            // 1 3 0 4   [0,4,3,2,0,0,0,0,1,0]
            // 3 4 2 4   [0,4,3,5,0,0,0,0,1,0]
            // 2 5 3 4   [0,4,6,5,0,0,0,0,1,0]
            // 3 6 5 4   [0,4,6,7,0,0,0,0,1,0]
            // 2 7 6 4   [0,4,8,7,0,0,0,0,1,0]
            // 4 8 6 4   [0,4,8,7,9,0,0,0,1,0]

            for (int j = 0, i = 0; j < n; j++) {
                i = Math.max(index[Integer.parseInt(Character.toString(s.charAt(j)))], i);
                ans = Math.max(ans, j - i + 1);
                index[Integer.parseInt(Character.toString(s.charAt(j)))] = j + 1;
            }
            return ans;
        }

        //index表记录该字符上次出现的下标
        public int best(String s) {
            int n = s.length(), ans = 0;
            int[] index = new int[128]; // current index of character
            // try to extend the range [i, j]
            //83213232431
            //[0,0,0,0,0,0,0,0,0,0]

            for (int j = 0, i = 0; j < n; j++) {
                i = Math.max(index[s.charAt(j)], i);
                ans = Math.max(ans, j - i + 1);
                index[s.charAt(j)] = j + 1;
            }
            return ans;
        }

        public int bestHashMap(String s) {
            int n = s.length(), ans = 0;
            Map<Character, Integer> map = new HashMap<>(); // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(map.get(s.charAt(j)), i);
                }
                ans = Math.max(ans, j - i + 1);
                map.put(s.charAt(j), j + 1);
            }
            return ans;
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

            int ret = new Solution().lengthOfLongestSubstring(s);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

}
