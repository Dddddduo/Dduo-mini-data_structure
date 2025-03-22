public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        // 插入键值对
        map.put("apple",  1);
        map.put("banana",  2);
        map.put("cherry",  3);

        // 获取值
        System.out.println(map.get("apple"));  // 输出: 1

        // 删除键值对
        map.remove("banana");
        System.out.println(map.get("banana"));  // 输出: null

        // 获取元素数量
        System.out.println(map.size());  // 输出: 2
    }
}