package com.song.base.day02;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 荷兰国旗问题
 * 给定一个整数数组，给定一个值K，这个值在原数组中一定存在，要求把数组中小于K的元素放到数组的左边，
 * 大于K的元素放到数组的右边，等于K的元素放到数组的中间，最终返回一个整数数组，
 * 其中只有两个值，分别是等于K的数组部分的左右两个下标值。
 *
 * 例如，给定数组：[2, 3, 1, 9, 4, 7, 6, 1, 4, 5]，给定一个值4，
 * 那么经过处理原数组可能得一种情况是：
 * [2, 3, 1, 1, 4, 4, 9, 7, 6, 5]，
 * 需要注意的是，小于4的部分不需要有序，大于4的部分也不需要有序
 * 回等于4部分的左右两个下标，即[4, 5]
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code05_NetherlandsFlag {

	public static int[] partition(int[] arr, int l, int r, int p) {
		// 小于p值区域的初始索引
		int less = l - 1;
		// 大于p值区域的初始索引
		int more = r + 1;

		while (l < more) {
			// 小于给定的值 将当前值与 小于p值区域的前一个值做交换  小于p值区域+1   l+1
			if (arr[l] < p) {
//				swap(arr, less + 1, l);
//				less++;
//				l++;
				// 简写
				swap(arr, ++less, l++);
			} else if (arr[l] > p) {// 大于给定的值 将当前值与 大于p值区域的前一个值做交换 大于p值区域+1

				swap(arr, --more, l);
			} else { // 等于给定的值 l++
				l++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	// for test
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
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

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
