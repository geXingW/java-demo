package com.gexingw.sort;

import java.util.Arrays;

/**
 * @author GeXingW
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] unSort = {1, 3, 5, 2, 4, 6, 3, 15, 0};

        System.out.println(Arrays.toString(insertSort(unSort)));
    }

    public static int[] insertSort(int[] unSort) {

        for (int i = 0; i < unSort.length; i++) {

            for (int j = i; j > 0; j--) {

                if (unSort[j] < unSort[j - 1]) {
                    int temp = unSort[j];
                    unSort[j] = unSort[j - 1];
                    unSort[j - 1] = temp;
                }
            }
        }

        return unSort;
    }

}
