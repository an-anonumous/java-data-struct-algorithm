package leetcode.hot100;

/**
 * 208. 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 *
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 */

public class H208_Trie {
    
    private final TireNode root = new TireNode();
    
    /** Initialize your data structure here. */
    public H208_Trie() {
    
    }
    
    /** Inserts a word into the trie. */
    public void insert( String word ) {
        TireNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt( i );
            if (!curr.containsChild( ch )) {
                curr.addChild( ch );
            }
            curr = curr.getChild( ch );
        }
        curr.increaseNum();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search( String word ) {
        TireNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt( i );
            if (!curr.containsChild( ch )) {
                return false;
            }
            curr = curr.getChild( ch );
        }
        return curr.getCounter() > 0;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith( String prefix ) {
        TireNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt( i );
            if (!curr.containsChild( ch )) {
                return false;
            }
            curr = curr.getChild( ch );
        }
        return true;
    }
    
    /**
     * 前缀树的节点
     *
     */
    protected static class TireNode {
        private final static int R = 26;
        private final TireNode childs[];
        private int counter = 0;
        
        /**
         * 初始化一个空节点
         */
        public TireNode() {
            childs = new TireNode[R];
            for (TireNode child : childs) {
                child = null;
            }
        }
        
        /**
         * 当前节点是否包含有ch孩子
         *
         * @param ch
         * @return
         */
        public boolean containsChild( char ch ) {
            return childs[ch - 'a'] != null;
        }
        
        /**
         * 在当前节点上添加孩子ch
         *
         * @param ch
         */
        public void addChild( char ch ) {
            childs[ch - 'a'] = new TireNode();
        }
        
        /**
         * 返回值为ch的孩子
         *
         * @param ch
         * @return
         */
        public TireNode getChild( char ch ) {
            return childs[ch - 'a'];
        }
        
        /**
         * 以当前字符结尾的串计数加一
         *
         */
        public void increaseNum() {
            counter++;
        }
        
        /**
         * 获取以当前字符结尾的单词数量
         *
         * @return
         */
        public int getCounter() {
            return counter;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */