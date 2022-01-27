package com.song.base.day01;

public class Code07_EvenTimesOddTimes {

	// 在一个数组中有一个数出现奇数次，请找出该数字
	public static void printOddTimesNum1(int[] arr) {
		int eO = 0;
		for (int cur : arr) {
			eO ^= cur;
		}
		System.out.println(eO);
	}

	// 在一个数组中有两个数出现奇数次，请找出这两个数字
	public static void printOddTimesNum2(int[] arr) {
		int eor = 0;
		for (int curNum : arr) {
			eor ^= curNum;
		}
		// eor = a^b
		// eor !=0  也就是说a和b肯定有一个二进制位是1，一个二进制位是0
		// 可以暂定比较的二进制位是最右侧
		// 获取最右侧的1
		int rightOne = eor & (~eor + 1);


		int eor1 = 0;
		for (int cur : arr) {
			// 找出最右侧为1的数
			if ((cur & rightOne) != 0) {
				eor1 ^= cur;
			}
		}
		System.out.println(eor1 + " " + (eor ^ eor1));
	}

	public static void main(String[] args) {
		int a = 5;
		int b = 7;

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println(a);
		System.out.println(b);

		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);

	}

}
