package com.song.base.day04;

/**
 * @program: DataStructureAndAlgorithmByZuo
 * @description
 * 【题目】给定一个单链表的头节点head，节点的值类型是整型，再给定一个整数 pivot。
 * 实现一个调整链表的函数，将链表调整为左部分都是值小于pivot的节点，
 * 中间部分都是值等于pivot的节点，右部分都是值大于pivot的节点。
 *
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code05_SmallerEqualBigger {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 第一种方式  将链表转化为数组 用数组进行分区
	public static Node listPartition1(Node head, int pivot) {
		if (head == null) {
			return head;
		}
		Node cur = head;
		int length = 0;
		// 计算数组的长度
		while (cur != null) {
			length++;
			cur = cur.next;
		}
		//创建数组
		Node[] nodeArr = new Node[length];
		length = 0;
		cur = head;
		// 链表转成数组
		for (length = 0; length != nodeArr.length; length++) {
			nodeArr[length] = cur;
			cur = cur.next;
		}
		// 数组进行分区处理
		arrPartition(nodeArr, pivot);

		// 操作完成的数组转成链表
		for (length = 1; length != nodeArr.length; length++) {
			nodeArr[length - 1].next = nodeArr[length];
		}
		//最后一个置为null
		nodeArr[length - 1].next = null;
		//返回头节点
		return nodeArr[0];
	}

	public static void arrPartition(Node[] nodeArr, int pivot) {
		// 小于区域
		int small = -1;
		//大于区域
		int big = nodeArr.length;
		int index = 0;
		while (index != big) {
			if (nodeArr[index].value < pivot) {
				swap(nodeArr, ++small, index++);
			} else if (nodeArr[index].value == pivot) {
				index++;
			} else {
				swap(nodeArr, --big, index);
			}
		}
	}

	public static void swap(Node[] nodeArr, int a, int b) {
		Node tmp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = tmp;
	}

	// 第二种方式  使用有限的几个变量进行处理
	public static Node listPartition2(Node head, int pivot) {
		Node smallHead = null; // small head
		Node smallTail = null; // small tail
		Node equalHead = null; // equal head
		Node equalTail = null; // equal tail
		Node biggerHead = null; // big head
		Node biggerTail = null; // big tail

		Node next = null; // save next node
		// every node distributed to three lists
		while (head != null) {
			// 获取下一个节点
			next = head.next;
			// 去除后边的连接
			head.next = null;
			// 和给定的值进行比较
			if (head.value < pivot) { // 小于给定的值
				if (smallHead == null) {
					// 如果 smallHead 为空的话 说明 smallHead smallTail 都还没有值
					// 将当前节点赋值为  smallHead smallTail
					smallHead = head;
					smallTail = head;
				} else {
					//如果 smallHead 不为空的话 将当前节点挂在尾节点的下一个节点
					smallTail.next = head;
					// 尾节点下移
					smallTail = head;
				}
			} else if (head.value == pivot) { // 等于给定的值
				if (equalHead == null) {
					// 如果 equalHead 为空的话 说明 equalHead equalTail 都还没有值
					// 将当前节点赋值为  equalHead equalTail
					equalHead = head;
					equalTail = head;
				} else {
					//如果 equalHead 不为空的话 将当前节点挂在尾节点的下一个节点
					equalTail.next = head;
					// 尾节点下移
					equalTail = head;
				}
			} else {// 大于给定的值
				if (biggerHead == null) {
					// 如果 biggerHead 为空的话 说明 biggerHead biggerTail 都还没有值
					// 将当前节点赋值为  biggerHead biggerTail
					biggerHead = head;
					biggerTail = head;
				} else {
					//如果 biggerHead 不为空的话 将当前节点挂在尾节点的下一个节点
					biggerTail.next = head;
					// 尾节点下移
					biggerTail = head;
				}
			}
			//节点向下移动
			head = next;
		}

		// 将小于 等于 大于的区域首尾相连接
		// small and equal reconnect
		if (smallTail != null) {
			smallTail.next = equalHead;
			// 判断等于区域是否存在
			equalTail = equalTail == null ? smallTail : equalTail;
		}
		// all reconnect
		if (equalTail != null) {
			equalTail.next = biggerHead;
		}
		// 判断小于区域、等于区域是否存在
		return smallHead != null ? smallHead : equalHead != null ? equalHead : biggerHead;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 4);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);

	}

}
