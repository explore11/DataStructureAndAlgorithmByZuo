package com.song.base.day04;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 两个单链表相交的一系列问题
 * 【题目】给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，
 * 请返回相交的 第一个节点。
 * 如果不相交，返回null
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code07_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 获取两个节点的相交节点
	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		// 判断是否是带环的节点
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		// 两个链表都没有环
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		// 两个链表都有环
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		//一个有环 一个没有环 节点不会相交
		return null;
	}

	//判断节点是否有环 使用快慢指针
	//快慢指针 慢指针一次走一步  快指针一次走两步
	//当他们相交的时候说明该链表说明有环    慢指针不动 快指针返回头节点
	// 快慢指针同时走，一次走一步，再次相遇的点就是入环的点
	public static Node getLoopNode(Node head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		//快慢指针 慢指针一次走一步  快指针一次走两步
		Node slow = head.next; // n1 -> slow
		Node fast = head.next.next; // n2 -> fast
		while (slow != fast) {
			// 慢指针走到空 或者 快指针走到空 说明没有环
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head; // n2 -> walk again from head
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		// 返回入环点
		return slow;
	}

	// 两个都没有环 判断相交的节点
	public static Node noLoop(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}

		Node cur1 = head1;
		Node cur2 = head2;
		int length = 0;
		// 第一个链表的长度和最后一个节点
		while (cur1.next != null) {
			length++;
			cur1 = cur1.next;
		}
		// 第二个链表的长度和最后一个节点
		while (cur2.next != null) {
			length--;
			cur2 = cur2.next;
		}

		// 判断最后一个节点是否相等
		// 如果没有环的点相交 最后一个节点的值一定相等
		if (cur1 != cur2) {
			return null;
		}

		// 选出最长的链表
		cur1 = length > 0 ? head1 : head2;
		cur2 = cur1 == head1 ? head2 : head1;

		// 获取两个链表的长度差值
		length = Math.abs(length);

		//长链表先走length
		while (length != 0) {
			length--;
			cur1 = cur1.next;
		}
		// 两个节点相等的为相交节点
		while (cur1 != cur2) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		// f返回相交节点
		return cur1;
	}

	// 两个都带有环
	//  三种可能性
	// 1. 两个链表不相交
	// 2. 两个链表的入环节点相同
	// 3. 两个链表的入环节点不相同
	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node cur1 = null;
		Node cur2 = null;
		if (loop1 == loop2) {
			// 两个链表的入环节点相同
			// 求相交节点的方式和两个无环链表求相交节点一样
			cur1 = head1;
			cur2 = head2;
			int length = 0;
			while (cur1 != loop1) {
				length++;
				cur1 = cur1.next;
			}
			while (cur2 != loop2) {
				length--;
				cur2 = cur2.next;
			}
			cur1 = length > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			length = Math.abs(length);
			while (length != 0) {
				length--;
				cur1 = cur1.next;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		} else {
			//一个链表的入环节点 在另外的一个链表的环中无法相遇 则没有相交节点
			// 一个链表的入环节点 在另外的一个链表的环中可以相遇 则有相交节点
			// 两个链表的入环节点都是相交节点
			cur1 = loop1.next;
			while (cur1 != loop1) {
				if (cur1 == loop2) {
					return loop1;
				}
				cur1 = cur1.next;
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}

}
