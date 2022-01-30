package com.song.base.day06;

import java.util.HashSet;
import java.util.Stack;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 广度优先遍历
 * 1，利用栈实现
 * 2，从源节点开始把节点按照深度放入栈，然后弹出
 * 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 * 4，直到栈变空
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code02_DFS {

	// 广度优先遍历
	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		// 创建栈
		Stack<Node> stack = new Stack<>();
		// 创建集合
		HashSet<Node> set = new HashSet<>();
		// 将源节点添加到栈和集合
		stack.add(node);
		set.add(node);
		//输出节点的值
		System.out.println(node.value);

		while (!stack.isEmpty()) {
			// 弹出元素
			Node cur = stack.pop();
			//获取该节点的所有邻接节点  判断集合中是否存在该邻接节点
			for (Node next : cur.nexts) {
				// 如果集合中的不存在该邻接节点 则把当前节点和当前节点的邻接节点重新放入到栈中
				// 并且往集合中添加该邻接节点
				if (!set.contains(next)) {
					stack.push(cur);
					stack.push(next);
					set.add(next);
					// 输出该邻接节点的值
					System.out.println(next.value);
					break;
				}
			}
		}
	}

}
