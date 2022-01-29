package com.song.base.day02;

import java.util.PriorityQueue;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 最小堆扩展
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，
 * 并且k相对于数组来说比较小。请选择一个合适的 排序算法针对这个数据进行排序。
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code04_SortArrayDistanceLessK {

	public void sortedArrDistanceLessK(int[] arr, int k) {
		if (k == 0) {
			return;
		}
		// 默认小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int index = 0;
		for (; index < Math.min(arr.length, k); index++) {
			heap.add(arr[index]);
		}
		int i = 0;
		for (; index < arr.length; i++, index++) {
			// 往最小堆中添加元素
			heap.add(arr[index]);
			// 取出堆中的最小值
			arr[i] = heap.poll();
		}
		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}
}
