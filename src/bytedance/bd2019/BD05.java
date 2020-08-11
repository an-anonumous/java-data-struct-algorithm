package bytedance.bd2019;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * [编程题]毕业旅行问题
 *
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 小明目前在做一份毕业旅行的规划。打算从北京出发，分别去若干个城市，然后再回到北京，每个城市之间均乘坐高铁，且每个城市只去一次。由于经费有限，
 * 希望能够通过合理的路线安排尽可能的省一些路上的花销。给定一组城市和每对城市之间的火车票的价钱，找到每个城市只访问一次并返回起点的最小车费花销。
 *
 * 输入描述:
 * 城市个数n（1<n≤20，包括北京）
 *
 * 城市间的车票价钱 n行n列的矩阵 m[n][n]
 *
 * 输出描述:
 * 最小车费花销 s
 *
 * 输入例子1:
 * 4
 * 0 2 6 5
 * 2 0 4 4
 * 6 4 0 2
 * 5 4 2 0
 *
 * 输出例子1:
 * 13
 *
 * 例子说明1:
 * 共 4 个城市，城市 1 和城市 1 的车费为0，城市 1 和城市 2 之间的车费为 2，城市 1 和城市 3 之间的车费为 6，城市 1 和城市 4 之间的车费为
 * 5，依次类推。假设任意两个城市之间均有单程票可购买，且票价在1000元以内，无需考虑极端情况。
 */
public class BD05 {
    private static int min = Integer.MAX_VALUE;
    
    // 5
    // 0 3 4 2 7
    // 3 0 4 6 3
    // 4 4 0 5 8
    // 2 6 5 0 6
    // 7 3 8 6 0
    //
    //   19
    public static void main( String[] args ) {
        Scanner scanner = new Scanner( System.in );
        int N = scanner.nextInt();
        int[][] grap = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grap[i][j] = scanner.nextInt();
            }
        }
        
        /* *******************************************************************************************/
        //  回溯算法只能通过50%
        // Set<Integer> visited = new HashSet<Integer>();
        // traceBack( grap, 0, 0, visited );
        //
        // System.out.println( min );
        /* *******************************************************************************************/
        
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < N; i++) {
            set.add( i );
        }
        
        int min = Integer.MAX_VALUE;
        for (int m : set) {
            // set.remove( m );
            HashSet<Integer> S = new HashSet<>( set );
            S.remove( m );
            int len = grap[0][m] + g( grap, m, S );
            if (len < min) {
                min = len;
            }
            // set.add( m );
        }
        System.out.println( min );
    }
    
    /**
     * 使用回溯法群举所有的可能
     *
     * @param grap
     * @param curr    当前城市编号
     * @param menoy   已经花费的钱
     * @param visited 已经访问过的节点
     */
    private static void traceBack( int[][] grap, int curr, int menoy, Set<Integer> visited ) {
        
        int N = grap.length;
        
        if (curr == 0 && visited.size() == N) {
            if (menoy < min) {
                min = menoy;
            }
        }
        
        for (int i = 0; i < N; i++) {
            if (i == curr) {
                continue;
            }
            if (!visited.contains( i )) {
                visited.add( i );
                traceBack( grap, i, menoy + grap[curr][i], visited );
                visited.remove( i );
            }
        }
    }
    
    /**
     * 从k出发进过set中所有节点到达0号节点的最短路径
     *
     * @param k
     * @param set
     * @return
     */
    private static int g( int[][] grap, int k, Set<Integer> set ) {
        // System.out.println( "k=" + k + ", set:" + set.toString() );
        
        if (set.isEmpty()) {
            return grap[k][0];
        }
        int min = Integer.MAX_VALUE;
        for (int m : set) {
            // set.remove( m );
            HashSet<Integer> S = new HashSet<>( set );
            S.remove( m );
            int len = grap[k][m] + g( grap, m, S );
            if (len < min) {
                min = len;
            }
            // set.add( m );
        }
        return min;
    }
}
