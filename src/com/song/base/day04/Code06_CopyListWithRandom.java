package com.song.base.day04;

import java.util.HashMap;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 复制含有随机指针节点的链表
 *
 * 【题目】一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val)
 * { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，
 * 并返回复制的新链表的头节点。
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code06_CopyListWithRandom {

	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}

	//第一种方式 使用hashMap结构
	public static Node copyListWithRand1(Node head) {
		// key是 旧节点 value是新的节点
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			// map中添加节点
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		// 重新赋值头节点
		cur = head;
		while (cur != null) {
			// 当前节点 对应的新节点的下一个节点  map.get(cur).next
			// 当前节点下一个节点 的对应的新节点  map.get(cur.next)
			// (当前新节点的下一个节点) 的值为 (当前节点下一个节点对应的新节点)
			map.get(cur).next = map.get(cur.next);

			// (当前新节点的下一个随机节点) 的值为 (当前节点下一个随机节点对应的新节点)
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		// 返回新的节点
		return map.get(head);
	}

	//第二种方式 生成新的节点 插入到老节点的后一个位置
	public static Node copyListWithRand2(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		// copy node and link to every node
		while (cur != null) {
			next = cur.next;
			//创建新的节点 放到点节点的后面
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}

		cur = head;
		Node curCopy = null;
		// set copy node rand
		while (cur != null) {
			// 获取下一个旧节点
			next = cur.next.next;
			//获取新节点
			curCopy = cur.next;
			//设置新节点的随机节点
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}

		//返回的新链表的头节点
		Node res = head.next;
		cur = head;
		// split
		while (cur != null) {
			//获取下一个旧节点
			next = cur.next.next;
			// 获取新节点
			curCopy = cur.next;
			// 将就节点和新节点拆开
			cur.next = next;
			curCopy.next = next != null ? next.next : null;

			cur = next;
		}
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}

}
