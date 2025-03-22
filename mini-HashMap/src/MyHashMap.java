import java.util.Objects;

// 自定义 HashMap 类 
class MyHashMap<K, V> {
    
    // 默认初始容量 
    private static final int DEFAULT_CAPACITY = 16;
    // 存储元素的数组 
    private Entry<K, V>[] table;
    // 元素数量 
    private int size;

    // 内部类，用于表示键值对节点 
    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key  = key;
            this.value  = value;
            this.next  = next;
        }
    }

    // 构造函数，初始化数组 
    public MyHashMap() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    // 计算哈希值 
    private int hash(Object key) {
        return (key == null) ? 0 : (key.hashCode()  & 0x7FFFFFFF) % table.length;
    }

    // 插入键值对 
    public void put(K key, V value) {
        int index = hash(key);
        // 遍历链表 
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next)  {
            if (Objects.equals(entry.key,  key)) {
                // 如果键已存在，更新值 
                entry.value  = value;
                return;
            }
        }
        // 键不存在，插入新节点 
        table[index] = new Entry<>(key, value, table[index]);
        size++;
    }

    // 获取键对应的值 
    public V get(K key) {
        int index = hash(key);
        // 遍历链表 
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next)  {
            if (Objects.equals(entry.key,  key)) {
                return entry.value;
            }
        }
        return null;
    }

    // 删除键值对 
    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> prev = null;
        for (Entry<K, V> entry = table[index]; entry != null; entry = entry.next)  {
            if (Objects.equals(entry.key,  key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next  = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
        }
        return null;
    }

    // 获取元素数量 
    public int size() {
        return size;
    }
}
