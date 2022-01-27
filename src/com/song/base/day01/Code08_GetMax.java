package com.song.base.day01;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 求整个数组中的最大值
 *
 * 求解递归的时间复杂度
 * master公式的使用
 * T(N) = a*T(N/b) + O(N^d)
 * 1) log(b,a) > d -> 复杂度为O(N^log(b,a))
 * 2) log(b,a) = d -> 复杂度为O(N^d * logN)
 * 3) log(b,a) < d -> 复杂度为O(N^d)
 *
 *
 * eg:
 * T(N) = 2 * T(N/2) + O(1)
 *
 * log(b,a) > d
 * log(2,2) > 0 时间复杂度为O(N)
 *
 *
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code08_GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

}
