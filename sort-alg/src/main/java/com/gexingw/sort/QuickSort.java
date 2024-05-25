package com.gexingw.sort;

import java.util.Arrays;

/**
 * @author GeXingW
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] unSort = new int[]{5, 3, 1, 2, 4, 6, 9, 10, 8, 7};
//        int[] unSort = new int[]{5, 3, 1, 2};


        System.out.println(Arrays.toString(quickSort(unSort, 0, unSort.length - 1)));

    }

    /**
     * 霍尔法
     * 霍尔法单趟过程分析：
     * <p>
     * 先记录下基准值key的位置，让 left 和 right 向两端靠近（直至相遇）。
     * right 小兵先走，直到遇到一个比 key 值要小的数字就停下。
     * right 小兵停下后，left 小兵再走，直到遇到一个比 key 值要大的数字就停下。
     * 交换 left 位置和 right 位置上的值。
     * right 小兵继续走，重复 2，3，4动作，直到 left 小兵与 right 小兵相遇
     * 相遇之后，将相遇位置的值与基准值 key 位置上的值交换，让相遇位置置成新的 key。
     * 注意：基准值 key 在左边， right 小兵先走；基准值 key 在右边，left 小兵先走。
     * <p>
     * 就相遇位置值分析：
     * 若 left 小兵与 right 小兵相遇，又有两种情况：1. left 小兵走的时候，遇到 right 小兵（L遇）；2.right 小兵走的时候，遇到 left 小兵（R遇L）。
     * 1. left 小兵走的时候，遇到 right 小兵
     * 因为是 key 位置在左边， right 小兵先走，所以当 right 小兵停下时，其位置上的值一定是比 key 位置上的值小的。这时，left 小兵来了， 两个小兵相遇，相遇的位置就是 right 小兵停下的位置，即相遇的位置比 key 位置上的值要小。
     * 2. right 小兵走的时候，遇到 left 小兵
     * 因为是 key 位置在左边， right 小兵先走。经过几轮交换之后，相遇的位置就是 left 小兵的位置，此时，因为经过了上一轮 left 位置上的值 与 right 位置上的值 交换。left 位置上的值就是上一轮交换中 right 停下位置上那个比 key 值小的值。即交换之后 left 位置上的值是比 key 位置上的值要小的，所以相遇的位置比 key 位置上的值要小。
     * <b>相遇位置的值一定就是比基准值 key 位置上的值小。</b>
     */
    public static int[] quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return arr;
        }

        int[] unSort = Arrays.copyOf(arr, arr.length);
        int left = low, right = high;
        int pivot = arr[low];

        while (left < right) {
            // 右指针往左移动对比
            while (left < right && arr[right] >= pivot) {
                right--;
            }

            // 左指针往右对比
            while (left < right && arr[left] <= pivot) {
                left++;
            }

            // 如果左、右指针不相等，代表pivot左边left位置的数比pivot大，且pivot右边的right位置数比pivot小，需要交换
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        // 循环结束，left=right，此时将pivot放到left位置，left位置的数就是pivot
        arr[low] = arr[left];
        arr[left] = pivot;
        System.out.println("排序前:" + Arrays.toString(unSort) + "，排序后：" + Arrays.toString(arr));

        // 递归左右子数组
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);

        return arr;


//        if (low > high) {
//            return arr;
//        }
//        int i = low, j = high, temp, t;
//        temp = arr[low];
//        while (i < j) {
//            while (temp <= arr[j] && i < j) {
//                j--;
//            }
//            while (temp >= arr[i] && i < j) {
//                i++;
//            }
//            if (i < j) {
//                t = arr[i];
//                arr[i] = arr[j];
//                arr[j] = t;
//            }
//        }
//        arr[low] = arr[i];
//        arr[i] = temp;
//        quickSort(arr, low, i - 1);
//        quickSort(arr, i + 1, high);
//        return arr;
    }

}
