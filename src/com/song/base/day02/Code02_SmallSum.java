package com.song.base.day02;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 小和问题
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和
 * [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1,3
 * 2左边比2小的数：1
 * 5左边比5小的数：1,3,4,2
 * 所以小和为1+1+3+1+1+3+4+2=16
 *
 * 第一种方法 暴力求解 双重循环 O(N^2)
 *
 * 第二种方法
 *
 *题目要求的是每个数左边有哪些数比自己小，也就是求右边有多少个数比自己大，
 * 那么产生的小和就是当前值乘以右边比自己大的值的个数。
 * [1,3,4,2,5]
 * 还是以上面的样例举例，
 * 1右边有4个比1大的数，所以产生小和1*4；
 * 3右边有2个比3大的数，所以产生小和3*2；
 * 4右边有一个比4大的数，所以产生小和4*1；
 * 2右边没有比2大的数，所以产生小和为2*0；
 * 5右边也没有比5大的数，所以产生小和5*0
 *
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code02_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return mergeSort(arr, l, mid)
                + mergeSort(arr, mid + 1, r)
                + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
		//临时数组
        int[] help = new int[r - l + 1];
		// 临时数组开始存储数据的索引
        int i = 0;
		// 准备的两个索引
        int p1 = l;
        int p2 = m + 1;

        // 小和结果
        int res = 0;
		// 都不越界的情况下 谁的索引对应的数值小 谁先拷贝 索引+1
        while (p1 <= m && p2 <= r) {
        	// 产生小和的条件  当左边的索引对应的值 小于右边索引对应的值才产生小和
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            // 两个数值进行比较  如果两个数值相等则拷贝右边的数值
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
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
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
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
