package bytedance.bd2019;

import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * [编程题]万万没想到之抓捕孔连顺
 *
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 128M，其他语言256M
 *
 * 我叫王大锤，是一名特工。我刚刚接到任务：在字节跳动大街进行埋伏，抓捕恐怖分子孔连顺。和我一起行动的还有另外两名特工，我提议
 *              1. 我们在字节跳动大街的N个建筑中选定3个埋伏地点。
 *              2. 为了相互照应，我们决定相距最远的两名特工间的距离不超过D。
 *
 * 我特喵是个天才! 经过精密的计算，我们从X种可行的埋伏方案中选择了一种。这个方案万无一失，颤抖吧，孔连顺！
 * ……
 * 万万没想到，计划还是失败了，孔连顺化妆成小龙女，混在cosplay的队伍中逃出了字节跳动大街。只怪他的伪装太成功了，就是杨过本人来了也发现不了的！
 *
 * 请听题：给定N（可选作为埋伏点的建筑物数）、D（相距最远的两名特工间的距离的最大值）以及可选建筑的坐标，计算在这次行动中，大锤的小队有多少种埋伏选择。
 * 注意：
 * 1. 两个特工不能埋伏在同一地点
 * 2. 三个特工是等价的：即同样的位置组合(A, B, C) 只算一种埋伏方法，不能因“特工之间互换位置”而重复使用
 *
 * 输入描述:
 * 第一行包含空格分隔的两个数字 N和D(1<=N<=1000000,1<=D<=1000000)
 * 第二行包含N个建筑物的的位置，每个位置用一个整数（取值区间为[0, 1000000]）表示，从小到大排列（将字节跳动大街看做一条数轴）
 *
 * 输出描述:
 * 一个数字，表示不同埋伏方案的数量。结果可能溢出，请对 99997867 取模
 *
 * 输入例子1:
 * 4 3
 * 1 2 3 4
 *
 * 输出例子1:
 * 4
 *
 * 例子说明1:
 * 可选方案 (1, 2, 3), (1, 2, 4), (1, 3, 4), (2, 3, 4)
 *
 *
 * 输入例子2:
 * 5 19
 * 1 10 20 30 50
 *
 * 输出例子2:
 * 1
 *
 * 例子说明2:
 * 可选方案 (1, 10, 20)
 */
public class BD02 {
    
    /**
     *  C++ 参考
     *
     * #include <bits/stdc++.h>
     * using namespace std;
     * int main()
     * {
     *     long long n,d,res=0;
     *     cin>>n>>d;
     *     long long a[n];
     *     for (long long i = 0, j = 0; i < n; i++)
     *     {
     *         cin>>a[i];
     *         while (i >= 2 && (a[i] - a[j]) > d)
     *             j++;
     *         res +=(i - j-1) * (i - j) / 2;
     *     }
     *     cout<<res%99997867;
     *     return 0;
     * }
     */
    
    private static Map<Integer, Integer> mem = new HashMap<>();
    
    /**
     *
     * 首先一遍循环要找到比当前位置距离刚好超过最大值的位置，由于是升序排列，所以可以用二分查找，我试过顺序查找超时了；
     * 然后是把第一个距离大于最大值的位置与当前位置的点之间建筑物的个数算出来记作X
     * 然后用组合数C(X,2)累加，由于数字比较大，计算C(X,2)必须用long long,要不然会溢出，得出结果后还有求模，加上已有值继续求模
     *
     * 通过率80%
     *
     * @param args
     */
    public static void main( String[] args ) {
        Scanner scanner = new Scanner( System.in );
        
        final int N = scanner.nextInt();
        final int D = scanner.nextInt();
        int[] arr = new int[N];
        
        for (int n = 0; n < N; n++) {
            arr[n] = scanner.nextInt();
        }
        
        long result = 0;
        for (int i = 0; i < N; i++) {
            int j = search( arr, i + 1, N - 1, Math.min( arr[i] + D, arr[N - 1] ) );
            if (!mem.containsKey( j - i )) {
                mem.put( j - i, C2( j - i ) );
            }
            result = ( result + mem.get( j - i ) ) % 99997867;
        }
        System.out.println( result );
    }
    
    /**
     * 在数组arr指定范围内查找小于等于v的最大数
     *
     * @param arr
     * @param start
     * @param end
     * @param v
     * @return
     */
    private static int search( int[] arr, int start, int end, int v ) {
        if (arr[end] <= v) {
            return end;
        }
        while (start < end) {
            int mid = ( start + end ) / 2;
            if (arr[mid] > v) {
                end = mid - 1;
                if (end >= 0 && arr[end] < v) {
                    return end;
                }
            } else if (arr[mid] < v) {
                start = mid;
            } else {
                return mid;
            }
        }
        return start;
    }
    
    /**
     * 计算从n个元素中选取两个元素的组合数
     *
     * @param n
     * @return
     */
    private static int C2( int n ) {
        return n * ( n - 1 ) / 2;
    }
    
    @Test
    public void test() {
        count();
    }
    
    public void count() {
        Scanner scanner = new Scanner( System.in );
        BigInteger result = new BigInteger( String.valueOf( 0 ) );
        
        final int N = scanner.nextInt();
        final int D = scanner.nextInt();
        
        int[] arr = new int[N];
        for (int i = 0, j = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
            while (arr[i] - arr[j] > D) {
                ++j;
            }
            long temp = ( i - j ) * ( i - j - 1 ) / 2;
            result = result.add( new BigInteger( String.valueOf( temp ) ) );
        }
        System.out.println( result.remainder( new BigInteger( String.valueOf( 99997867 ) ) ) );
    }
    
}
