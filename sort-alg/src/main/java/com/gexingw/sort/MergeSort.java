package com.gexingw.sort;

import java.util.Arrays;

/**
 * @author GeXingW
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] unSort = {8, 2, 3, 1, 5, 6, 7, 4, 9, 0, 10};
//        int[] unSort = {8, 2, 3, 1};

        int[] unSortNms = Arrays.copyOfRange(unSort, 0, unSort.length);
//
//        int middleIdx = left + (ri);
//        int[] left = Arrays.copyOfRange(unSort, 0, middleIdx);
//        int[] right = Arrays.copyOfRange(unSort, middleIdx + 1, unSort.length);
//
        int[] sortNums = merge(unSortNms, 0, unSortNms.length - 1);
        System.out.println(Arrays.toString(sortNums));
    }

    public static int[] merge(int[] unSortNums, int left, int right) {
        System.out.println("merge参数,left:" + left + ",right:" + right);

        if (left == right) {
            System.out.println("left=right:" + unSortNums[left]);
            return new int[]{unSortNums[left]};
        }

        int middleIdx = left + (right - left) / 2;
        int[] sortLeft = merge(unSortNums, left, middleIdx);
        int[] sortRight = merge(unSortNums, middleIdx + 1, right);

        System.out.println("sortLeft:" + Arrays.toString(sortLeft));
        System.out.println("sortRight:" + Arrays.toString(sortRight));

//        return new int[0];
        int[] sort = new int[sortLeft.length + sortRight.length];
        int sortIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < sortLeft.length && rightIndex < sortRight.length) {
            sort[sortIndex++] = sortLeft[leftIndex] <= sortRight[rightIndex] ? sortLeft[leftIndex++] : sortRight[rightIndex++];
        }

        while (leftIndex < sortLeft.length) {
            sort[sortIndex++] = sortLeft[leftIndex++];
        }

        while (rightIndex < sortRight.length) {
            sort[sortIndex++] = sortRight[rightIndex++];
        }

        System.out.println("排序结果：" + Arrays.toString(sort));
        System.out.println();
        System.out.println();
        return sort;
    }

}
