package leetcode.SwordFingerOffer;

/**
 * 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 */
public class MS05 {
    
    public static void main(String[] args) {
        System.out.println(new MS05().replaceSpace("We are happy."));
    }
    
    public String replaceSpace(String s) {
        StringBuilder result = new StringBuilder((int) (3 * s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                result.append("%20");
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
