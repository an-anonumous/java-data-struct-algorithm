package bytedance.bd2019;

import java.util.Scanner;

/**
 * [编程题]雀魂启动！
 *
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 小包最近迷上了一款叫做雀魂的麻将游戏，但是这个游戏规则太复杂，小包玩了几个月了还是输多赢少。
 * 于是生气的小包根据游戏简化了一下规则发明了一种新的麻将，只留下一种花色，并且去除了一些特殊和牌方式（例如七对子等），具体的规则如下：
 *       总共有36张牌，每张牌是1~9。每个数字4张牌。
 *       你手里有其中的14张牌，如果这14张牌满足如下条件，即算作和牌
 *       14张牌中有2张相同数字的牌，称为雀头。
 *       除去上述2张牌，剩下12张牌可以组成4个顺子或刻子。顺子的意思是递增的连续3个数字牌（例如234,567等），刻子的意思是相同数字的3个数字牌（例如111,777）
 *
 * 例如：
 *      1 1 1 2 2 2 6 6 6 7 7 7 9 9 可以组成1,2,6,7的4个刻子和9的雀头，可以和牌
 *      1 1 1 1 2 2 3 3 5 6 7 7 8 9 用1做雀头，组123,123,567,789的四个顺子，可以和牌
 *      1 1 1 2 2 2 3 3 3 5 6 7 7 9 无论用1 2 3 7哪个做雀头，都无法组成和牌的条件。
 *
 * 现在，小包从36张牌中抽取了13张牌，他想知道在剩下的23张牌中，再取一张牌，取到哪几种数字牌可以和牌。
 *
 * 输入描述:
 * 输入只有一行，包含13个数字，用空格分隔，每个数字在1~9之间，数据保证同种数字最多出现4次。
 *
 * 输出描述:
 * 输出同样是一行，包含1个或以上的数字。代表他再取到哪些牌可以和牌。若满足条件的有多种牌，请按从小到大的顺序输出。若没有满足条件的牌，请输出一个数字0
 *
 * 输入例子1:
 * 1 1 1 2 2 2 5 5 5 6 6 6 9
 *
 * 输出例子1:
 * 9
 *
 * 例子说明1:
 * 可以组成1,2,6,7的4个刻子和9的雀头
 *
 * 输入例子2:
 * 1 1 1 1 2 2 3 3 5 6 7 8 9
 *
 * 输出例子2:
 * 4 7
 *
 * 例子说明2:
 * 用1做雀头，组123,123,567或456,789的四个顺子
 *
 * 输入例子3:
 * 1 1 1 2 2 2 3 3 3 5 7 7 9
 *
 * 输出例子3:
 * 0
 *
 * 例子说明3:
 * 来任何牌都无法和牌
 */
public class BD03 {
    
    /**
     * 本题考查的是回溯法，由于数据的规模非常小(n<=9)，所以递归不会很深。
     * 思路： 已有13张牌，我们从剩余的牌中依次从1到9选择一张牌作为第14张牌，然后判断是否已经构成胡牌。
     * 判断胡牌思路：从1到9中选择一个数字作为雀头，然后判断剩余的数字是否包含4个三张牌
     *
     * @param args
     */
    public static void main( String[] args ) {
        Scanner scanner = new Scanner( System.in );
        
        int[] data = new int[13];
        int[] map = new int[10];
        
        for (int i = 0; i < 13; i++) {
            data[i] = scanner.nextInt();
            map[data[i]]++;
        }
        
        
        boolean flag = false;
        
        // 再添加一张牌，看能否完成
        for (int j = 1; j <= 9; j++) {
            if (map[j] < 4) {  // 每个数字最多只有4张
                ++map[j];
                
                if (canWin( map )) {
                    flag = true;
                    System.out.print( "" + j + " " );
                }
                
                --map[j];
            }
        }
        
        if (!flag) {
            System.out.println( 0 );
        }
        
    }
    
    /**
     * 判断添加一张牌后的14张牌能否和牌
     *
     * @param map
     * @return
     */
    private static boolean canWin( int[] map ) {
        // 先确定雀头
        for (int i = 1; i <= 9; i++) {
            if (map[i] >= 2) {
                map[i] -= 2;
                if (hasTriples( map, 4 )) {
                    map[i] += 2;
                    return true;
                }
                map[i] += 2;
            }
        }
        return false;
    }
    
    /**
     * 根据map数组判断是否有n个刻子和顺子
     *
     * @param map
     * @param n
     * @return
     */
    private static boolean hasTriples( int[] map, int n ) {
        if (n == 0) {
            return true;
        }
        
        // 先群举 刻子，111,222
        for (int i = 1; i <= 9; i++) {
            if (map[i] >= 3) {
                map[i] -= 3;
                if (hasTriples( map, n - 1 )) {
                    map[i] += 3;
                    return true;
                }
                map[i] += 3;
            }
        }
        
        // 在群举 顺子
        for (int i = 3; i <= 9; i++) {
            if (map[i] >= 1 && map[i - 1] >= 1 && map[i - 2] >= 1) {
                --map[i];
                --map[i - 1];
                --map[i - 2];
                
                if (hasTriples( map, n - 1 )) {
                    
                    ++map[i];
                    ++map[i - 1];
                    ++map[i - 2];
                    
                    return true;
                }
                
                ++map[i];
                ++map[i - 1];
                ++map[i - 2];
            }
            
        }
        
        return false;
    }
    
    
}
