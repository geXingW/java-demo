package com.gexingw.sort;

import java.util.Arrays;

/**
 * @author GeXingW
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] unSortArr = {2, 5, 6, 1, 16, 4, 3, 10, 8, 12, 9};

        int[] sortedArr = selectSort(unSortArr);
        System.out.println(Arrays.toString(sortedArr));
    }

    public static int[] selectSort(int[] unSort) {

        for (int i = 0; i < unSort.length; i++) {
            // 每一趟选出最小的数
            int minIndex = i;
            for (int j = i + 1; j < unSort.length; j++) {
                if (unSort[minIndex] > unSort[j]) {
                    minIndex = j;
                }
            }

            int temp = unSort[i];
            unSort[i] = unSort[minIndex];
            unSort[minIndex] = temp;
        }

        return unSort;
    }

}
