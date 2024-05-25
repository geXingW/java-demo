package com.gexingw.sort;

import java.util.Arrays;

/**
 * @author GeXingW
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] unSort = {2, 6, 3, 7, 1, 4, 9, 10, 5, 8};
//        int[] unSort = {2, 6, 3, 7};
        for (int i = 0; i < unSort.length; i++) {
            for (int j = 0; j < unSort.length - i - 1; j++) {
                if (unSort[j] > unSort[j + 1]) {
                    int temp = unSort[j];
                    unSort[j] = unSort[j + 1];
                    unSort[j + 1] = temp;
                }
//                System.out.println(Arrays.toString(unSort));
//                System.out.println("i=" + i + ",j=" + j);
            }
            System.out.println(Arrays.toString(unSort));
            System.out.println();
        }

//        System.out.println(Arrays.toString(unSort));
//        System.out.println();

    }

}
