package com.song.base.day01;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 找局部最小值
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code06_BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        // 如果 数组长度为1 则最局部小值就是该值
        // 如果 数组长度为2  且前一个数小于后一个数  则前一个数就是局部最小值
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
		// 如果 数组长度为n  且倒数第一个数小于倒数第二个数  则倒数第一个数就是局部最小值
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }


        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
        	// 找中点
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

}
