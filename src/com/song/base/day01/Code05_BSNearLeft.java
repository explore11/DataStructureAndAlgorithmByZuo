package com.song.base.day01;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 在一个有序数组中，找>=某个数最左侧的位置  二分法
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code05_BSNearLeft {

	// 在arr上，找满足>=value的最左位置
	public static int nearestIndex(int[] arr, int value) {
		int L = 0;
		int R = arr.length - 1;
		// 标记
		int index = -1;
		while (L < R) {
			// 找中位数
			int mid = L + ((R - L) >> 1);
			// 和中位数比较大小 并标记
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

}
