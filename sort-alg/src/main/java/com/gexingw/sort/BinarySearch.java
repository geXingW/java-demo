package com.gexingw.sort;

/**
 * 二分查找
 *
 * @author GeXingW
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] unSort = {2, 6, 3, 7, 1, 4, 9, 10, 5, 8};
//        int[] unSort = {2, 6, 3, 7};
//        int[] unSort = {1, 2};
        System.out.println(binarySearch(unSort, 10));
    }

    public static int binarySearch(int[] arr, int target) {
        // 如果只有一个数，直接比较
        if (arr.length == 1) {
            return arr[0] == target ? 0 : -1;
        }

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            // 计算中间坐标
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                // 找到目标值
                return mid;
            } else if (arr[mid] < target) {
                // 目标值在右边
                left = mid + 1;
            } else {
                // 目标值在左边
                right = mid - 1;
            }
        }

        return -1;
    }
}
