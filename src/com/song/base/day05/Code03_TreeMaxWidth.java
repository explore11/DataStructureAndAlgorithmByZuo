package com.song.base.day05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 如何完成二叉树的宽度优先遍历(常见题目：求一棵二叉树的宽度)
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code03_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 二叉树的宽度优先遍历  利用队列
    public static void widthTraverse(Node head) {
        if (head == null) {
            return;
        }
        //设置一个队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }


    //第一种方式 求一棵二叉树的宽度 使用map
    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key是节点 value在哪一层
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
        	//弹出节点
            Node cur = queue.poll();
            // 获取节点的层级
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
            	// 增加节点时候 添加层级
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
				// 增加节点时候 添加层级
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            // 判断 当前层和节点的层数是否是在同一级
			// 是的话 节点个数加一
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
            // 不是的话 比较最大节点数 层级+1	 当前层节点个数为1
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        // 最后一层作比较 返回节点数最大值
        max = Math.max(max, curLevelNodes);
        return max;
    }

	//TODO 2022-01-29 第二种方式 求一棵二叉树的宽度
    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // 当前层，最右节点是谁
        Node nextEnd = null; // 下一层，最右节点是谁
        int max = 0;
        int curLevelNodes = 0; // 当前层的节点数
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
//        int maxLevel = 10;
//        int maxValue = 100;
//        int testTimes = 1000000;
//        for (int i = 0; i < testTimes; i++) {
//            Node head = generateRandomBST(maxLevel, maxValue);
//            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("finish!");
//

        Node headNode = new Node(1);
        headNode.left = new Node(4);
        headNode.right = new Node(7);
		headNode.left.left = new Node(2);
		headNode.left.right = new Node(8);
        widthTraverse(headNode);

    }

}
