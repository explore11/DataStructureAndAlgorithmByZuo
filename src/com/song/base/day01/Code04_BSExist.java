package com.song.base.day01;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 在一个有序的数组中 查询某一数值是否存在 二分法
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code04_BSExist {

	public static boolean exist(int[] sortedArr, int num) {
		if (sortedArr == null || sortedArr.length == 0) {
			return false;
		}
		int L = 0;
		int R = sortedArr.length - 1;
		int mid = 0;
		while (L < R) {
			mid = L + ((R - L) >> 1);
			if (sortedArr[mid] == num) {
				return true;
			} else if (sortedArr[mid] > num) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return sortedArr[L] == num;
	}

}
