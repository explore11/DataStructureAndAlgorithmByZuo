package com.song;

/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 异或操作
 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code3_XOR {

    public static void printOddTimesNum1(int[] arr) {
        int eO = arr[0];
        for (int i = 1; i < arr.length; i++) {
            eO ^=arr[i];
        }

        System.out.println(eO);
    }

    public static void main(String[] args) {
        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);
    }
}
