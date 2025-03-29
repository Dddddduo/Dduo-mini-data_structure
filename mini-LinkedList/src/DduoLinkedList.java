import java.util.NoSuchElementException;

// 多多的迷你双向链表
public class DduoLinkedList<E> {
    // 节点内部类
    private class Node {
        E data;
        Node prev;
        Node next;

        public Node(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;  // 头节点
    private Node tail;  // 尾节点
    private int size;   // 链表长度

    // 构造函数
    public DduoLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 头部插入节点
     *
     * @param data 要插入的数据
     */
    public void addFirst(E data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * 尾部插入节点
     *
     * @param data 要插入的数据
     */
    public void addLast(E data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * 指定位置插入节点
     *
     * @param index 插入位置
     * @param data  要插入的数据
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node current = getNode(index);
            Node newNode = new Node(data);

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    /**
     * 删除头节点
     *
     * @return 被删除节点的数据
     */
    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        E removedData = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return removedData;
    }

    /**
     * 删除尾节点
     *
     * @return 被删除节点的数据
     */
    public E removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        E removedData = tail.data;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return removedData;
    }

    /**
     * 删除指定元素
     *
     * @param data 要删除的数据
     * @return 是否删除成功
     */
    public boolean remove(E data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current == head) return removeFirst() != null;
                if (current == tail) return removeLast() != null;

                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * 获取链表长度
     *
     * @return 当前链表长度
     */
    public int size() {
        return size;
    }

    /**
     * 判断链表是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空链表
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 获取指定位置节点（优化遍历策略）
     *
     * @param index 目标位置
     * @return 对应节点
     */
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        // 根据位置选择遍历方向
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    /**
     * 链表转字符串（正序）
     *
     * @return 链表内容字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(",  ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 链表转字符串（逆序）
     *
     * @return 逆序链表内容字符串
     */
    public String reverseToString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = tail;
        while (current != null) {
            sb.append(current.data);
            if (current.prev != null) sb.append(",  ");
            current = current.prev;
        }
        sb.append("]");
        return sb.toString();
    }


    /**
     * 查找某个元素在链表中的索引，如果找不到返回 -1
     *
     * @param data 要查找的元素
     * @return 元素在链表中的索引，如果找不到返回 -1
     */
    public int indexOf(E data) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data))  {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

}