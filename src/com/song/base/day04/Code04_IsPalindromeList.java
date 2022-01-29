package com.song.base.day04;

import java.util.Stack;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 给定一个单链表的头节点head，请判断该链表是否为回文结构
 * 【题目】给定一个单链表的头节点head，请判断该链表是否为回文结构。
 * 【例子】
 * 1->2->1，返回true；
 * 1->2->2->1，返回true；
 * 15->6->15，返回true；
 * 1->2->3，返回false。
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code04_IsPalindromeList {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// need n extra space
	// 第一种方法 使用栈 占用N的额外空间
	public static boolean isPalindrome1(Node head) {
		//创建栈 利用栈先进后出的原理 依次和原链表的值做比较
		Stack<Node> stack = new Stack<Node>();
		Node cur = head;
		while (cur != null) {
			// 压入栈
			stack.push(cur);
			cur = cur.next;
		}
		while (head != null) {
			// 让链表的值和栈中弹出的值作比较
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// need n/2 extra space
	// 第二种方法 使用栈 占用N/2的额外空间  使用快慢指针的方式
	public static boolean isPalindrome2(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		// 慢指针 一次走一步  当快指针走到尾部的时候 慢指针走到中点部位
		Node right = head.next;
		// 快指针 一次走两步
		Node cur = head;
		while (cur.next != null && cur.next.next != null) {
			right = right.next;
			cur = cur.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		while (right != null) {
			stack.push(right);
			right = right.next;
		}
		while (!stack.isEmpty()) {
			if (head.value != stack.pop().value) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	// need O(1) extra space
	// 第三种方法
	// 1.使用快慢指针的方式  找到中点位置
	// 2.从中点位置分割进行反转  比较两个链表的值是否相同
	// 3.链表再复原
	public static boolean isPalindrome3(Node head) {
		if (head == null || head.next == null) {
			return true;
		}
		// 慢指针
		Node slow = head;
		// 快指针
		Node pre = head;
		while (pre.next != null && pre.next.next != null) { // find mid node
			slow = slow.next; // n1 -> mid
			pre = pre.next.next; // n2 -> end
		}
		// 第二个链表的起始节点
		pre = slow.next; // n2 -> right part first node
		//中点的下一个节点置为null
		slow.next = null; // mid.next -> null

		// 临时节点
		Node next = null;
		// 第二个链表进行链表反转
		while (pre != null) { // right part convert
			next = pre.next; // next -> save next node
			pre.next = slow;
			slow = pre;
			pre = next;
		}
		// next 是临时存的节点记录作用   slow 是已经转换进行反转完成的链表(中点后截取 反转后的链表)
		next = slow; // n3 -> save last node
		// (中点之前的链表)
		pre = head;// n2 -> left first node
		boolean res = true;
		while (slow != null && pre != null) { // check palindrome
			// 两个链表的值进行比较
			if (slow.value != pre.value) {
				res = false;
				break;
			}
			slow = slow.next; // left to mid
			pre = pre.next; // right to mid
		}
		//再将数据反转回来
		slow = next.next;
		next.next = null;
		while (slow != null) { // recover list
			pre = slow.next;
			slow.next = next;
			next = slow;
			slow = pre;
		}
		return res;
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

		Node head = null;
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(2);
		head.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(2);
		head.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		System.out.print(isPalindrome2(head) + " | ");
		System.out.println(isPalindrome3(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

	}

}
