package com.gexingw.sort;

import java.util.Arrays;

/**
 * @author GeXingW
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] unSort = {2, 6, 3, 7, 4, 9, 10, 1};
        for (int i = 0; i < unSort.length; i++) {
            for (int j = i + 1; j < unSort.length; j++) {

                if (unSort[i] > unSort[j]) {
                    int temp = unSort[i];
                    unSort[i] = unSort[j];
                    unSort[j] = temp;
                }
//                System.out.println("i=" + i + ",j=" + j);
            }
            System.out.println(Arrays.toString(unSort));

        }

//        System.out.println(Arrays.toString(unSort));
//        System.out.println();

    }

}
