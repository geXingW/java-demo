package com.gexingw.sort;

import java.util.Arrays;

/**
 * @author GeXingW
 */
public class QuickSort2 {

    public static void main(String[] args) {
//        int[] arr = {5, 3, 6, 2, 1, 9, 8, 7, 4};
        int[] arr = {5, 3, 6, 2};

        System.out.println(Arrays.toString(quickSort(arr, 0, arr.length - 1)));
    }

    public static int[] quickSort(int[] unSort, int low, int high) {
        if (low >= high) {
            return unSort;
        }

        int pivot = unSort[low];

        int left = low;
        int right = high;

        int[] arr = Arrays.copyOf(unSort, unSort.length);

        while (left < right) {

            while (left < right && unSort[right] >= pivot) {
                right--;
            }

            while (left < right && unSort[left] <= pivot) {
                left++;
            }

            if (left < right) {
                int temp = unSort[left];
                unSort[left] = unSort[right];
                unSort[right] = temp;
            }
        }

        unSort[low] = unSort[left];
        unSort[left] = pivot;
        System.out.println("排序前:" + Arrays.toString(arr) + "，排序后：" + Arrays.toString(unSort));


        quickSort(unSort, low, left - 1);
        quickSort(unSort, left + 1, high);

        return unSort;
    }

}
