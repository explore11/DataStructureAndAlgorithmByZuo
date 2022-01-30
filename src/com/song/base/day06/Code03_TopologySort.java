package com.song.base.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 拓扑排序算法
 * 适用范围：要求有向图，且有入度为0的节点，且没有环
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code03_TopologySort {

	// directed graph and no loop
	public static List<Node> sortedTopology(Graph graph) {
		// 创建map key是节点node value是入度的个数
		HashMap<Node, Integer> inMap = new HashMap<>();
		// 入度为0的节点队列
		Queue<Node> zeroInQueue = new LinkedList<>();
		// 把所有节点放到map中
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			//入度等于0的节点放到队列中
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		// 返回的集合
		List<Node> result = new ArrayList<>();
		while (!zeroInQueue.isEmpty()) {
			// 获取节点
			Node cur = zeroInQueue.poll();
			result.add(cur);
			//获取该节点的所有邻接节点
			for (Node next : cur.nexts) {
				//把该节点的所有邻接节点的关系  再找入度为0的节点放入到队列中
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		// 返回最终结果数组
		return result;
	}
}
