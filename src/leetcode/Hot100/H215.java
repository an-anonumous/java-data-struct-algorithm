package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 */
public class H215 {
    
    @Test
    public void test1() {
        H215 h215 = new H215();
        Assert.assertEquals( 4, h215.findKthLargest( new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4 ) );
    }
    
    public int findKthLargest( int[] nums, int k ) {
        int start = 0, end = nums.length - 1;
        
        int m = start;
        while (true) {
            m = partition( nums, start, end );
            if (end - m + 1 == k) {
                return nums[m];
            } else {
                if (end - m + 1 > k) {
                    start = m + 1;
                } else {
                    k -= ( end - m + 1 );
                    end = m - 1;
                }
            }
        }
    }
    
    private int partition( int[] nums, final int start, final int end ) {
        int temp = nums[start];
        int i = start, j = end;
        while (i < j) {
            
            // 此时i位置空闲
            while (i < j && nums[j] >= temp) {
                --j;
            }
            
            // 此时找到右边第一个小于temp的元素nums[j];
            nums[i] = nums[j];
            
            // 此时j位置空闲
            while (i < j && nums[i] < temp) {
                ++i;
            }
            
            // 此时遇到左边第一个大于temp的元素nums[i]
            nums[j] = nums[i];
        }
        nums[i] = temp;
        return i;
    }
    
    /**
     * 使用小根堆将所有元素逐个插入堆中，在插入过程中如果发现对的大小超过了k，则删除堆顶元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestByPriorityQueue( int[] nums, int k ) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add( num );
            if (heap.size() > k) {
                heap.poll();
            }
        }
    
        return heap.peek();
    }
    
    @Test
    public void test2() {
        H215 h215 = new H215();
        Assert.assertEquals( 5, h215.findKthLargest( new int[]{3, 2, 1, 5, 6, 4}, 2 ) );
    }
    
    @Test
    public void test3() {
        H215 h215 = new H215();
        Assert.assertEquals( 5, h215.largestByHeap( new int[]{3, 2, 1, 5, 6, 4}, 2 ) );
    }
    
    /**
     * 通过自己实现的大根堆寻找第k大的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestByHeap( int[] nums, int k ) {
        MaxHeap maxHeap = new MaxHeap( nums );
        for (int i = 0; i < k - 1; i++) {
            maxHeap.removeMax();
        }
        return maxHeap.removeMax();
    }
    
    protected static class MaxHeap {
        // 使用数组存储一颗完全二叉树，节点i的左孩子下标为2*i+1,右孩子下标为2*i+2;双亲为(i-1)/2
        private final int[] tree;
        
        // 当前堆大小
        private int size;
        
        /**
         * 使用数组nums中的元素建堆
         *
         * @param nums
         */
        public MaxHeap( int[] nums ) {
            tree = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                tree[i] = nums[i];
            }
            
            size = nums.length;
            
            // 从最后一个元素的双亲开始向下调整堆
            for (int i = ( size - 1 - 1 ) / 2; i >= 0; --i) {
                siftDown( i );
            }
        }
        
        /**
         * 向下调整，前提条件是左右子树都已经是大根堆。
         *
         * @param index
         */
        private void siftDown( int index ) {
            int child = 2 * index + 1;
            int temp = tree[index];
            
            while (child < size) {
                
                // child 指向左右孩子中较大者
                if (child + 1 < size && tree[child] < tree[child + 1]) {
                    ++child;
                }
                
                // if (tree[index] < tree[child]) {
                if (temp < tree[child]) {
                    tree[index] = tree[child];
                } else {
                    break;
                }
                index = child;
                child = 2 * child + 1;
            }
            tree[index] = temp;
        }
        
        /**
         * 删除堆顶元素
         *
         * @return
         */
        public int removeMax() {
            int result = tree[0];
            tree[0] = tree[size - 1];
            --size;
            siftDown( 0 );
            return result;
        }
        
    }
}
