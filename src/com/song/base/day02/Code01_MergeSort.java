package com.song.base.day02;

import java.util.Arrays;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 归并排序
 * 求出数组的中点  先让索引 (0到中点)和(中点+1到n-1) 上的位置有序
 *
 * 在准备一个空数组  两个索引 0 和中点+1
 * 比较两个索引对应值的大小  谁小谁拷贝到数组中 索引+1
 * 两个索引 谁先越界 则把另一个剩余的拷贝进数组
 * 再放回原来的数组中去
 *
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code01_MergeSort {


	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	// 归并排序
	public static void mergeSort(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		//找中点
		int mid = l + ((r - l) >> 1);
		mergeSort(arr, l, mid);
		mergeSort(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}

	public static void merge(int[] arr, int l, int m, int r) {
		//临时数组
		int[] help = new int[r - l + 1];
		// 临时数组开始存储数据的索引
		int i = 0;
		// 准备的两个索引
		int p1 = l;
		int p2 = m + 1;
		// 都不越界的情况下 谁的索引对应的数值小 谁先拷贝 索引+1
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 如果p2先越界 则把p1剩余的拷贝到数组中去
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		// 如果p1先越界 则把p2剩余的拷贝到数组中去
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		// 把组装好的数据重新拷贝到原来的数组中去
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			mergeSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		mergeSort(arr);
		printArray(arr);

	}

}
