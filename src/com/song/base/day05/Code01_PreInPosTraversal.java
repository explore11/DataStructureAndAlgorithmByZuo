package com.song.base.day05;

import java.util.Stack;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 二叉树
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code01_PreInPosTraversal {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 前序递归遍历
	public static void preOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	// 中序递归遍历
	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	// 后序递归遍历
	public static void posOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	// 前序非递归   头左右           使用栈 入栈顺序 头右左
	public static void preOrderUnRecur(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			// 头节点进栈
			stack.add(head);
			while (!stack.isEmpty()) {
				// 弹出头节点
				head = stack.pop();
				System.out.print(head.value + " ");
				// 压入右节点
				if (head.right != null) {
					stack.push(head.right);
				}
				// 压入左节点
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}

	// 中序非递归 先压入所有的左节点 弹出最后一个左节点
	// 如果该节点没有右节点 继续向上弹出； 如果有右节点，则压入进栈，找有没有左节点....
	public static void inOrderUnRecur(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			while (!stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					System.out.print(head.value + " ");
					head = head.right;
				}
			}
		}
		System.out.println();
	}

	// 后序非递归 使用两个栈结构 左右头  逆序为   头右左
	// 入栈顺序 头左右
	public static void posOrderUnRecur1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();
			// 搜集栈
			Stack<Node> collectionStack = new Stack<Node>();
			// 压入头节点
			s1.push(head);
			while (!s1.isEmpty()) {
				// 弹出头节点
				head = s1.pop();
				// 添加到搜集栈中
				collectionStack.push(head);
				// 压入左节点
				if (head.left != null) {
					s1.push(head.left);
				}
				// 压入右节点
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			// 从搜集栈中再次弹出，顺序为后续遍历
			while (!collectionStack.isEmpty()) {
				System.out.print(collectionStack.pop().value + " ");
			}
		}
		System.out.println();
	}

	public static void posOrderUnRecur2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		preOrderUnRecur(head);
		inOrderUnRecur(head);
		posOrderUnRecur1(head);
		posOrderUnRecur2(head);

	}

}
