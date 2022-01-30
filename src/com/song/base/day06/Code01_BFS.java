package com.song.base.day06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 图的宽度优先遍历
 * 1，利用队列实现
 * 2，从源节点开始依次按照宽度进队列，然后弹出
 * 3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
 * 4，直到队列变空
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code01_BFS {

	// 图的宽度优先遍历
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        // 队列
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        // 将源节点添加到集合和set中
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
        	// 弹出源节点
            Node cur = queue.poll();
            // 输出值
            System.out.println(cur.value);
            // 获取该节点的所有邻接节点  判断集合中是否存在该邻接节点 如果不存在则把该节点添加到队列和集合中
            for (Node next : cur.nexts) {
            	// 判断是否存在
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
