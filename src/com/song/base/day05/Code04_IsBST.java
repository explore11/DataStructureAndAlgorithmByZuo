package com.song.base.day05;

import java.util.ArrayList;
import java.util.LinkedList;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 如何判断一颗二叉树是否是搜索二叉树
 * 什么是搜索二叉树？
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 并且它的左、右子树也分别为搜索二叉树。
 *
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code04_IsBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 第一种方式 借助其特性 递归
    // 左节点 小于 当前节点
    // 右节点 大于 当前节点
    // 也就是说搜索二叉树的中序遍历是递增的
    public static int pre = Integer.MIN_VALUE;

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean bst = isBST(head.left);
        if (!bst) {
            return false;
        }
        if (pre <= head.value) {
            pre = head.value;
        } else {
            return false;
        }
        return isBST(head.right);
    }

    // 第二种方式 使用数组
    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            // 前一个值咸鱼后一个值
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    //第三种方式  基于套路求解
    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int max = x.value;
        int min = x.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        boolean isBST = true;
        if (leftInfo != null && (!leftInfo.isBST || leftInfo.max >= x.value)) {
            isBST = false;
        }
        if (rightInfo != null && (!rightInfo.isBST || rightInfo.min <= x.value)) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean i, int ma, int mi) {
            isBST = i;
            max = ma;
            min = mi;
        }

    }

    public static void main(String[] args) {
//        Node headNode = new Node(2);
//        headNode.left = new Node(1);
//        headNode.right = new Node(5);
//        headNode.right.left = new Node(3);
//        headNode.right.right = new Node(7);
//        System.out.println(isBST(headNode));
//        System.out.println(isBST2(headNode));
//        System.out.println(process(headNode).isBST);
//        int t = 5 << 3;
        int t = 5 >> 3 ;

        System.out.println(t);

    }

}
