package meituan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * [编程题]图的遍历
 *
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 64M，其他语言128M
 *
 * 给定一张包含N个点、N-1条边的无向连通图，节点从1到N编号，每条边的长度均为1。假设你从1号节点出发并打算遍历所有节点，那么总路程至少是多少？
 *
 * 输入描述:
 * 第一行包含一个整数N，1≤N≤10^5。
 * 接下来N-1行，每行包含两个整数X和Y，表示X号节点和Y号节点之间有一条边，1≤X，Y≤N。
 *
 * 输出描述:
 * 输出总路程的最小值。
 *
 * 输入例子1:
 * 4
 * 1 2
 * 1 3
 * 3 4
 *
 * 输出例子1:
 * 4
 */
public class MT201901 {
    private static int min = Integer.MAX_VALUE;
    private static int len = 0;
    
    public static void main( String[] args ) {
        Scanner scanner = new Scanner( System.in );
        final int N = scanner.nextInt();
        
        List<List<Integer>> grap = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            grap.add( new ArrayList<>() );
        }
        
        for (int i = 0; i <= N; i++) {
            grap.get( scanner.nextInt() ).add( scanner.nextInt() );
        }
        
        // 输入完成
        
        for (int i = 1; i <= N; i++) {
            len = 0;
            traceBack( grap, i, new HashSet<Integer>() );
        }
        System.out.println( min );
    }
    
    private static void traceBack( List<List<Integer>> grap, int curr, HashSet<Integer> used ) {
        // 防止环路
        if (used.contains( curr )) {
            return;
        }
        
        List<Integer> list = grap.get( curr );
        for (Integer n : list) {
            used.add( n );
            if (used.size() == grap.size() - 1 && min < len + 1) {
                min = len + 1;
            }
            ++len;
            traceBack( grap, n, used );
            // used.remove( n );
        }
        
        
    }
}
