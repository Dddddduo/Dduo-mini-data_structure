import java.util.Objects;

// 多多的迷你哈希表
class DduoHashMap<K, V> {

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 4;
    // 默认负载因子，当 size > capacity * loadFactor 时触发扩容
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 存储链表的数组
    private Entry<K, V>[] table;
    // 当前元素数量
    private int size;
    // 扩容阈值 = capacity * loadFactor
    private int threshold;

    // 链表节点定义
    static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next; // 单向链表

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public DduoHashMap() {
        table = new Entry[DEFAULT_CAPACITY];
        threshold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
    }

    /**
     * 哈希计算
     * @param key
     * @return
     */
    private int hash(K key) {
        if (key == null) return 0;
        // 取绝对值并映射到数组范围内
        return (key.hashCode() & 0x7FFFFFFF) % table.length;
    }

    /**
     * 插入键值对（尾插法）
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        // 插入前先检查是否需要扩容
        if (size >= threshold) {
            resize();
        }

        int index = hash(key);
        Entry<K, V> newNode = new Entry<>(key, value, null);

        // 如果数组位置为空，直接插入新节点
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            // 尾插法：遍历链表到尾部插入
            Entry<K, V> current = table[index];
            while (current.next != null) {
                // 如果发现重复键，更新值并返回
                if (Objects.equals(current.key, key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            // 检查尾节点是否重复
            if (Objects.equals(current.key, key)) {
                current.value = value;
            } else {
                current.next = newNode; // 尾部插入新节点
            }
        }
        size++;
    }

    /**
     * 扩容机制
     */
    private void resize() {
        int oldCapacity = table.length;
        int newCapacity = oldCapacity << 1; // 容量翻倍
        threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);

        Entry<K, V>[] newTable = new Entry[newCapacity];
        // 遍历旧数组，重新哈希所有节点到新数组
        for (int i = 0; i < oldCapacity; i++) {
            Entry<K, V> entry = table[i];
            while (entry != null) {
                Entry<K, V> next = entry.next;  // 保存下一个节点
                int newIndex = (entry.key.hashCode() & 0x7FFFFFFF) % newCapacity;

                // 头插法插入新数组（JDK 1.7 的方式，但此处仅演示逻辑）
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;

                entry = next; // 处理下一个节点
            }
        }
        table = newTable; // 更新数组引用
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * 删除键值对
     * @param key
     * @return
     */
    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;  // 删除头节点
                } else {
                    prev.next = current.next;  // 删除中间或尾部节点
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }
}
