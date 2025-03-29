import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

class DduoHashLinkedList {
    private static class Node {
        int val;
        Node prev, next;
        Node(int val) { this.val  = val; }
    }

    private final Node head, tail;
    private final Map<Integer, LinkedHashSet<Node>> valueToNodes;
    private final Map<Node, Integer> nodeToValue;

    public DduoHashLinkedList() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next  = tail;
        tail.prev  = head;
        valueToNodes = new HashMap<>();
        nodeToValue = new HashMap<>();
    }

    // 添加元素到链表末尾（初始化使用）
    public void add(int val) {
        Node newNode = new Node(val);
        // 维护链表连接
        newNode.prev  = tail.prev;
        newNode.next  = tail;
        tail.prev.next  = newNode;
        tail.prev  = newNode;
        // 更新哈希表
        valueToNodes.computeIfAbsent(val,  k -> new LinkedHashSet<>()).add(newNode);
        nodeToValue.put(newNode,  val);
    }

    // 在第一个x节点后插入y
    public void insertAfter(int x, int y) {
        if (!valueToNodes.containsKey(x))  return;

        LinkedHashSet<Node> xNodes = valueToNodes.get(x);
        if (xNodes.isEmpty())  return;

        Node xNode = xNodes.iterator().next();  // 取第一个出现的x节点
        Node yNode = new Node(y);

        // 维护链表连接
        yNode.prev  = xNode;
        yNode.next  = xNode.next;
        xNode.next.prev  = yNode;
        xNode.next  = yNode;

        // 更新哈希表
        valueToNodes.computeIfAbsent(y,  k -> new LinkedHashSet<>()).add(yNode);
        nodeToValue.put(yNode,  y);
    }

    // 删除第一个x节点
    public void remove(int x) {
        if (!valueToNodes.containsKey(x))  return;

        LinkedHashSet<Node> xNodes = valueToNodes.get(x);
        if (xNodes.isEmpty())  return;

        Iterator<Node> it = xNodes.iterator();
        Node delNode = it.next();  // 取第一个出现的x节点
        it.remove();

        // 维护链表连接
        delNode.prev.next  = delNode.next;
        delNode.next.prev  = delNode.prev;

        // 清理哈希表
        nodeToValue.remove(delNode);
        if (xNodes.isEmpty())  {
            valueToNodes.remove(x);
        }
    }

    // 转换为输出字符串
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = head.next;
        while (curr != tail) {
            sb.append(curr.val).append(" ");
            curr = curr.next;
        }
        return sb.toString().trim();
    }
}