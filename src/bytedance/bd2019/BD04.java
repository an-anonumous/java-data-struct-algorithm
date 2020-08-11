package bytedance.bd2019;

import java.util.*;

/**
 * [编程题]特征提取
 *
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 *
 * 小明是一名算法工程师，同时也是一名铲屎官。某天，他突发奇想，想从猫咪的视频里挖掘一些猫咪的运动信息。为了提取运动信息，他需要从视频的每一帧
 * 提取“猫咪特征”。一个猫咪特征是一个两维的vector<x, y>。如果x_1=x_2 and y_1=y_2，那么这俩是同一个特征。
 * 因此，如果喵咪特征连续一致，可以认为喵咪在运动。也就是说，如果特征<a, b>在持续帧里出现，那么它将构成特征运动。比如，特征<a, b>在第2/3/4/
 * 7/8帧出现，那么该特征将形成两个特征运动2-3-4 和7-8。
 *
 * 现在，给定每一帧的特征，特征的数量可能不一样。小明期望能找到最长的特征运动。
 *
 * 输入描述:
 * 第一行包含一个正整数N，代表测试用例的个数。
 * 每个测试用例的第一行包含一个正整数M，代表视频的帧数。
 * 接下来的M行，每行代表一帧。其中，第一个数字是该帧的特征个数，接下来的数字是在特征的取值；比如样例输入第三行里，2代表该帧有两个猫咪特征，
 * <1，1>和<2，2>
 *
 * 所有用例的输入特征总数和<100000
 *
 * N满足1≤N≤100000，M满足1≤M≤10000，一帧的特征个数满足 ≤ 10000。
 * 特征取值均为非负整数。
 *
 * 输出描述:
 * 对每一个测试用例，输出特征运动的长度作为一行
 *
 * 输入例子1:
 * 1
 * 8
 * 2 1 1 2 2
 * 2 1 1 1 4
 * 2 1 1 2 2
 * 2 2 2 1 4
 * 0
 * 0
 * 1 1 1
 * 1 1 1
 *
 * 输出例子1:
 * 3
 *
 * 例子说明1:
 * 特征<1,1>在连续的帧中连续出现3次，相比其他特征连续出现的次数大，所以输出3
 */
public class BD04 {
    
    private static int maxlen = 0;
    
    // 1
    // 3
    // 2 0 0 1 1
    // 2 0 1 3 3
    // 2 3 3 4 4
    //
    // 2
    
    public static void main( String[] args ) {
        Scanner scanner = new Scanner( System.in );
        List<List<Map<Integer, List<Integer>>>> samples = new LinkedList<>();// 存放所有的测试用例
        
        // 测试用例的个数
        final int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            // 当前测试用例中视频帧的个数
            int M = scanner.nextInt();
            List<Map<Integer, List<Integer>>> sample = new LinkedList<>();// 一个测试用例里面的所有帧
            for (int j = 0; j < M; j++) {
                // 当前帧的特征数
                int num = scanner.nextInt();
                Map<Integer, List<Integer>> map = new HashMap<>();// 每一个帧的特征值
                for (int k = 0; k < num; k++) {
                    int index = scanner.nextInt();
                    int value = scanner.nextInt();
                    if (map.containsKey( index )) {
                        map.get( index ).add( value );
                    } else {
                        ArrayList<Integer> l = new ArrayList<>();
                        l.add( value );
                        map.put( index, l );
                    }
                }
                sample.add( map );
            }
            samples.add( sample );
        }
        
        // 处理每一个测试用例
        for (List<Map<Integer, List<Integer>>> sample : samples) {
            for (int i = 0; i < sample.size(); i++) {
                for (Map.Entry<Integer, List<Integer>> entry : sample.get( i ).entrySet()) {
                    Integer key = entry.getKey();
                    List<Integer> values = entry.getValue();
                    for (Integer value : values) {
                        maxMovementLen( sample, i + 1, key, value, 1 );
                    }
                }
            }
            // System.out.println( maxMovementLen( sample ) );
        }
        System.out.println( maxlen );
    }
    
    /**
     * 处理每一个测试 用例，输出其中最长的运动路径长度
     *
     * @param sample
     * @return
     */
    private static void maxMovementLen( List<Map<Integer, List<Integer>>> sample, int findex, final int key,
                                        final int value, int len ) {
        // 帧索引越界
        if (findex > sample.size() - 1) {
            if (len > maxlen) {
                maxlen = len;
            }
            return;
        }
        
        // 每一个帧的特征值
        Map<Integer, List<Integer>> map = sample.get( findex );
        if (map.containsKey( key ) && map.get( key ).contains( value )) {
            maxMovementLen( sample, findex + 1, key, value, len + 1 );
        } else {
            if (len > maxlen) {
                maxlen = len;
            }
            return;
        }
        
    }
    
}
