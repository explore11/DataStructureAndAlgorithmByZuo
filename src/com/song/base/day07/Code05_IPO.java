package com.song.base.day07;

import java.util.Comparator;
import java.util.PriorityQueue;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description  获得的最大钱数
 * 输入： 正数数组costs 正数数组profits
 * 正数k
 * 正数m
 * 含义：
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * k表示你只能串行的最多做k个项目
 * m表示你初始的资金
 * 说明： 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
 * 输出： 你最后获得的最大钱数
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code05_IPO {

    // 最多K个项目
    // W是初始资金
    // Profits[] Capital[] 一定等长
    // 返回最终最大的资金
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
    	// 定义一个最小堆 以花费最少排序
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		// 定义一个最大堆 以花利润最多排序
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
        	//将所有的放到最小堆中
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }
		// 最多K个项目
		// W是初始资金
        for (int i = 0; i < K; i++) {
        	//minCostQ 不为空 且 最少的花费小于等于初始资金
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= W) {
            	//放入最大堆
                maxProfitQ.add(minCostQ.poll());
            }
            //自己起始资金 不够项目的启动资金  导致maxProfitQ没有项目 就停止
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            // 起始资金和利润相加
            W += maxProfitQ.poll().profit;
        }
        return W;
    }

    public static class Program {
        public int profit;
        public int cost;

        public Program(int p, int c) {
            this.profit = p;
            this.cost = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }

    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }

    }

}
