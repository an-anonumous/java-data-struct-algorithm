package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1: *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class H03 {
    
    @Test
    public void test1() {
        H03 h03 = new H03();
        Assert.assertEquals( 3, h03.lengthOfLongestSubstring( "abcabcbb" ) );
    }
    
    /**
     * 方法一：滑动窗口
     * 思路和算法
     *
     * 我们不妨以示例一中的字符串 abcabcbb 为例，找出从每一个字符开始的，不包含重复字符的最长子串，那么其中最长的那个字符串即为答案。
     * 对于示例一中的字符串，我们列举出这些结果，其中括号中表示选中的字符以及最长的字符串：
     *
     * 以 (a)bcabcbb 开始的最长字符串为(abc)abcbb；
     * 以 a(b)cabcbb 开始的最长字符串为a(bca)bcbb；
     * 以 ab(c)abcbb 开始的最长字符串为ab(cab)cbb；
     * 以 abc(a)bcbb 开始的最长字符串为abc(abc)bb；
     * 以 abca(b)cbb 开始的最长字符串为abca(bc)bb；
     * 以 abcab(c)bb 开始的最长字符串为abcab(cb)b；
     * 以 abcabc(b)b 开始的最长字符串为abcabc(b)b；
     * 以 abcabcb(b) 开始的最长字符串为abcabcb(b)。
     *
     * 发现了什么？如果我们依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的！这里的原因在于，假设我们选择字符串中的第 k 个字符作
     * 为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 r 。那么当我们选择第 k+1 个字符作为起始位置时，首先从 k+1 到 r 的字符显
     * 然是不重复的，并且由于少了原本的第 k 个字符，我们可以尝试继续增大 r，直到右侧出现了重复字符为止。这样以来，我们就可以使用「滑动窗口」
     * 来解决这个问题了：
     *
     * 我们使用两个指针表示字符串中的某个子串（的左右边界）。其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 r；在每一步的操
     * 作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，但需要保证这两个指针对应的子
     * 串中没有重复的字符。在移动结束后，这个子串就对应着以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度；在枚举结束后，我们找
     * 到的最长的子串的长度即为答案。
     *
     * 判断重复字符
     * 在上面的流程中，我们还需要使用一种数据结构来判断 是否有重复的字符，常用的数据结构为哈希集合（即 C++ 中的 std::unordered_set，Java 中的
     * HashSet，Python 中的 set, JavaScript 中的 Set）。在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往
     * 哈希集合中添加一个字符。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有
     * ASCII 码在 [0, 128)内的字符，即∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有 ∣Σ∣ 个，因此空间复杂度为 O(∣Σ∣)。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring( String s ) {
        int front = 0, tail = 0, maxlen = 0;
        
        Set<Character> set = new HashSet<>();// 存储在窗口中的字符
        
        while (tail < s.length()) {
            char ch = s.charAt( tail );
            while (!set.contains( ch ) && tail < s.length()) {
                set.add( ch );
                tail++;
                if (tail < s.length()) {
                    ch = s.charAt( tail );
                }
            }
            
            int currlen = tail - 1 - front + 1;
            if (currlen > maxlen) {
                maxlen = currlen;
            }
            
            set.remove( s.charAt( front ) );
            front++;
        }
        return maxlen;
    }
    
    @Test
    public void test2() {
        H03 h03 = new H03();
        Assert.assertEquals( 1, h03.lengthOfLongestSubstring( "bbbbb" ) );
    }
    
    @Test
    public void test3() {
        H03 h03 = new H03();
        Assert.assertEquals( 3, h03.lengthOfLongestSubstring( "pwwkew" ) );
    }
}
