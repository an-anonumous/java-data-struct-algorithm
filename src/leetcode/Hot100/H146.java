package leetcode.Hot100;

/**
 * 146. LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 * LRUCache cache = new LRUCache( 2 );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

import java.util.HashMap;

/**
 *
 * LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
 *
 * 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
 * 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
 *
 * 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1) 的时间内完成 get 或者 put 操作。
 * 具体的方法如下：
 *
 * 对于 get 操作，首先判断 key 是否存在：
 *      如果 key 不存在，则返回 −1；
 *      如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
 *
 * 对于 put 操作，首先判断 key 是否存在：
 *      如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。
 *      然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
 *      如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
 *
 * 上述各项操作中，访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)。而将一个节点移到双向链表的头部，
 * 可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在 O(1) 时间内完成。
 *
 * 小贴士
 * 在双向链表的实现中，使用一个伪头部（dummy head）和伪尾部（dummy tail）标记界限，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在。
 *
 */
public class H146 {
    private final HashMap<Integer, Node> index = new HashMap<>();
    private final int capacity;
    private final Node head = new Node();
    private final Node tail = new Node();
    
    public H146( int capacity ) {
        this.capacity = capacity;
        
        // 初始化链表
        head.pre = null;
        head.next = tail;
        
        tail.pre = head;
        tail.next = null;
    }
    
    public static void main( String[] args ) {
        H146 h146 = new H146( 2 );
        
        h146.put( 1, 1 );
        h146.put( 2, 2 );
        System.out.println( h146.get( 1 ) == 1 );
        h146.put( 3, 3 );    // 该操作会使得关键字 2 作废
        System.out.println( h146.get( 2 ) == -1 );
        h146.put( 4, 4 );    // 该操作会使得关键字 1 作废
        System.out.println( h146.get( 1 ) == -1 );
        System.out.println( h146.get( 3 ) == 3 );
        System.out.println( h146.get( 4 ) == 4 );
    }
    
    /**
     * 如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。
     * 然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
     * 如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
     *
     * @param key
     * @param value
     */
    public void put( int key, int value ) {
        Node node = index.get( key );
        if (node == null) {
            
            // 添加新节点
            node = new Node( key, value );
            index.put( key, node );
            addNodeToHead( node );
            
            // 如果添加后节点数超过最大容量，则删除链表尾部最后一个节点，即最长时间没有访问过的节点
            if (index.size() > capacity) {
                index.remove( tail.pre.key );
                removeNode( tail.pre );
            }
            
            return;
        } else {
            node.val = value;
            removeNode( node );
            addNodeToHead( node );
        }
        
    }
    
    /**
     * 如果 key 不存在，则返回 −1；
     * 如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
     *
     * @param key
     * @return
     */
    public int get( int key ) {
        if (!index.containsKey( key )) {
            return -1;
        }
        Node node = index.get( key );
        
        // 将访问节点移动到链表头部
        removeNode( node );
        addNodeToHead( node );
        
        return node.val;
    }
    
    /**
     * 添加节点到链表的头部
     *
     * @param node
     */
    private void addNodeToHead( Node node ) {
        Node next = head.next;
        
        node.next = head.next;
        head.next = node;
        
        node.pre = head;
        next.pre = node;
    }
    
    /**
     * 删除链表中指定的节点
     */
    private void removeNode( Node node ) {
        Node pre = node.pre;
        Node next = node.next;
        
        pre.next = next;
        next.pre = pre;
    }
    
    private static class Node {
        public int key, val;
        public Node pre = null, next = null;
        
        public Node( int key, int val ) {
            this.val = val;
            this.key = key;
        }
        
        public Node() {
        }
        
        public Node( int key, int val, Node pre, Node next ) {
            this.val = val;
            this.pre = pre;
            
            this.next = next;
            this.key = key;
        }
    }
    
}
