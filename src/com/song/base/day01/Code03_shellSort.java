package com.song.base.day01;

import java.util.Arrays;
/* *
 * @program: DataStructureAndAlgorithmByZuo
 * @description 希尔排序 希尔排序(Shell Sort)是插入排序的一种，它是针对直接插入排序算法的改进。
 * 它通过比较相距一定间隔的元素来进行，各趟比较所用的距离随着算法的进行而减小，直到只比较相邻元素的最后一趟排序为止。
 *
 * 希尔排序的基本步骤，在此我们选择增量gap=length/2，
 * 缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，{n/2,(n/2)/2...1}，称为增量序列
 * eg:
 * {8, 9, 1, 7, 2, 3, 5, 4, 6, 0}
 *
 * 第一次步长分组
 * 初始增量 5=10/2   分5组
 * 初始位置 0  步长 5    {8,3}
 * 初始位置 1  步长 5    {9,5}
 * 初始位置 2  步长 5    {1,4}
 * 初始位置 3  步长 5    {7,6}
 * 初始位置 4  步长 5    {2,0}
 *
 * 分组完成之后 对于每一个组都执行直接插入排
 * 排序之前 {8,3} {9,5} {1,4} {7,6} {2,0}
 * 排序之后 {3,8} {5,9} {1,4} {6,7} {0,2}
 *
 * 第一次分组排序之后的数组为
 * {3, 5, 1, 6, 0, 8, 9, 4, 7, 2}
 *
 * 第二次步长分组
 * 初始增量 2=5/2   分两组
 * 初始位置 0  步长 2    {3,1,0,9,7}
 * 初始位置 1  步长 2    {5,6,8,2,4}
 *
 * 分组完成之后 对于每一个组都执行直接插入排
 * 排序之前 {3,1,0,9,7} {5,6,8,2,4}
 * 排序之后 {0,1,3,7,9} {2,4,5,6,8}
 *
 * ......
 * 最后结果
 * {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}

 * @author: swq
 * @create: 2022-01-25 16:51
 **/
public class Code03_shellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        shellSort(arr);
    }

    private static void shellSort(int[] arr) {
        // 步长
        for (int stepLength = arr.length / 2; stepLength > 0; stepLength /= 2) {
            for (int i1 = stepLength; i1 < arr.length; i1++) {
                // 要插入的索引
                int insertIndex = i1;
                // 要插入的数据
                int insertVal = arr[insertIndex];
                // 分组前面的元素 大于后面的元素
                if (arr[insertIndex] < arr[insertIndex - stepLength]) {
                    // 要插入的索引减去步长的索引大于等于0 且对应的值小于前一个值
                    while (insertIndex - stepLength >= 0 && insertVal < arr[insertIndex - stepLength]) {
                        // 把前面一个值得数据赋值给后一个
                        arr[insertIndex] = arr[insertIndex - stepLength];
                        // 索引换到前一个位置
                        insertIndex -= stepLength;
                    }
                    // 将小的值赋值给前面的索引
                    arr[insertIndex] = insertVal;
                }
            }
            System.out.println("分组个数为" + (stepLength) + "的排序是        " + Arrays.toString(arr));
        }
    }
}
