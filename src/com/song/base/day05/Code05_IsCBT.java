package com.song.base.day05;

import java.util.LinkedList;
import java.util.Queue;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 如何判断一颗二叉树是否是完全二叉树
 *
 *
 * 判断一棵树是否是完全二叉树的思路
 * 1>如果树为空，则直接返回true
 * 2>如果树不为空：层序遍历二叉树
 * 2.1>如果一个结点左右孩子都不为空，则pop该节点，将其左右孩子入队列；
 * 2.1>如果遇到一个结点，左孩子为空，右孩子不为空，则该树一定不是完全二叉树；
 * 2.2>如果遇到一个结点，左孩子不为空，右孩子为空；或者左右孩子都为空，
 * 且则该节点之后的队列中的结点都为叶子节点，该树才是完全二叉树，否则就不是完全二叉树；
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/

public class Code05_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //判断一颗二叉树是否是完全二叉树
    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        // 创建队列
        Queue<Node> queue = new LinkedList<>();
        //  如果一个结点，左孩子不为空，右孩子为空；或者左右孩子都为空 leaf置为true
        boolean leaf = false;
        Node l = null;
        Node r = null;
        // 添加头节点到队列中去
        queue.add(head);
        while (!queue.isEmpty()) {
            // 弹出头节点
            head = queue.poll();
            l = head.left;
            r = head.right;
            // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
            // 当遇到了节点的左右孩子不双全（左右孩子都是null或者有左孩子没有右孩子）,之后再出现左孩子不为空或者右孩子不为空 则不是完全二叉树
            // 当出现左孩子为空并且右孩子不为空 则不是完全二叉树
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node headNode = new Node(1);
        headNode.left = new Node(2);
        headNode.right = new Node(4);
        headNode.left.left = new Node(1);
        headNode.left.right = new Node(7);
        headNode.right.right = new Node(8);
        headNode.right.right.left = new Node(2);
        headNode.right.right.right = new Node(4);
        System.out.println(isCBT(headNode));
    }
}
