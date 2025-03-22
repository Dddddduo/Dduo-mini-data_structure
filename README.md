# 🔥 Dduo-mini-data_structure Dduo的Java迷你数据结构

📦 从零实现的核心数据结构库 | 适用于算法学习与教学演示 | 每个实现<300行代码

## 📚 内容导览
```sh
.
├── src
│   ├── LinkedList         # 链表全家桶
│   │   ├── SinglyLinkedList.java
│   │   └── DoublyLinkedList.java
│   ├── HashStructures     # 哈希系列
│   │   ├── MyHashMap.java      # 开放寻址法
│   │   └── LinkedHashMap.java  # 哈希链表
│   ├── TreeStructures     # 树形结构
│   │   ├── BinaryTree.java
│   │   └── SegmentTree.java    # 线段树
│   └── Graph              # 图论基础
│       ├── AdjacencyList.java  # 邻接表
│       └── MatrixGraph.java    # 矩阵实现
└── test                   # JUnit测试用例
```

## 🧩 数据结构巡礼
### 🔹 链表（Linked List）
- **单向链表**：`SinglyLinkedList.java` 实现增删O(1)头插法
- **双向链表**：支持快速反向遍历，内置迭代器实现
```java
// 示例：创建双向链表
DoublyLinkedList<String> list = new DoublyLinkedList<>();
list.addFirst("Head");
list.addLast("Tail");
```

### 🔹 哈希结构（Hash Structures）
- **基础哈希表**：线性探测解决冲突，负载因子超过0.75自动扩容
- **哈希链表**：LinkedHashMap.java 实现LRU缓存淘汰策略
```java
MyHashMap<Integer, String> map = new MyHashMap<>();
map.put(1, "One");  // 自动扩容机制
```

### 🌳 树形结构（Tree）
- **二叉树**：支持前/中/后序递归与非递归遍历
- **线段树**：区间查询优化实现，解决RMQ问题
```java
SegmentTree segTree = new SegmentTree(new int[]{1,3,5,7});
segTree.query(1, 3);  // 输出15
```

### 🕸️ 图论结构（Graph）
- **邻接表**：支持DFS/BFS遍历，拓扑排序实现
- **邻接矩阵**：适合稠密图，Floyd算法最短路实现
```java
MatrixGraph graph = new MatrixGraph(5);
graph.addEdge(0,1,10);  // 添加带权边
```

## 🚀 快速开始
1. 克隆仓库
```bash
git clone https://github.com/yourname/Dduo-mini-data_structure.git
```

2. 运行测试用例
```java
// 在IDE中执行 test/ 目录下的JUnit测试
@Test
public void testLinkedList() {
    // 验证链表操作逻辑
}
```

## 🤝 贡献指南
欢迎通过Issue或PR参与改进：
1. 提交bug报告时请附带测试用例
2. 新数据结构实现需包含：  
   ✅ 核心操作实现  
   ✅ JUnit测试覆盖  
   ✅ 方法注释文档
3. 代码风格遵循Google Java Style

---

> 📧 教学合作/问题反馈：your.email@example.com  
> 持续更新中，点亮⭐不迷路！
``` 

**设计亮点**：
1. 使用Unicode图标构建视觉层次
2. 文件树结构直观展示代码组织
3. 每个数据结构模块包含：
   - 实现要点说明
   - 典型应用场景
   - 可运行的代码片段
4. 贡献指南明确质量要求
5. 徽章系统增强可信度

可以根据实际项目结构调整文件路径，添加更多代码示例或复杂度分析图表。
