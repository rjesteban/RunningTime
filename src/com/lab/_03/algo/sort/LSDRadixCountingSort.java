/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab._03.algo.sort;

/**
 * Radix sort with counting sort as subroutine
 * @author rj
 */
public class LSDRadixCountingSort {

    public LSDRadixCountingSort() {
        super();
    }

    public static void main(String[] args) {
        LSDRadixCountingSort sorter = new LSDRadixCountingSort();

        int a[] = {12, 5, 23, 176, 142, 22, 345345};

        sorter.sort(a);

        for (int i = 0, n = a.length; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public int findMin(int[] arr, int power) {
        int min = ((arr[0]/power)%10);
        for (int i = 1, n = arr.length; i < n ; i++) {
            if (((arr[i]/power)%10) < min) {
                min = ((arr[i]/power)%10);
            }
        }
        return min;
    }

    public int findMax(int[] arr, int power) {
        int max = ((arr[0]/power)%10);
        for (int i = 1, n = arr.length; i < n ; i++) {
            if (max < ((arr[i]/power)%10)) {
                max = ((arr[i]/power)%10);
            }
        }
        return max;
    }

    public int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 1, n = arr.length;  i < n ; i++) {
            if (arr[i] < min)
                min = arr[i];
        }
        return min;
    }

    public int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1, n = arr.length; i < n; i++) {
            if (max < arr[i])
                max = arr[i];
        }
        return max;
    }


    public void sort (int a[]) {
        int pass = this.findMax(a);
        int pow = 1;
        int[] arr = new int[a.length];

        while (pass/pow != 0) {
            // do subroutine counting sort
            int max = this.findMax(a, pow);
            int min = findMin(a, pow);
            int size = max - min + 1;
            int count[] = new int[size];


            for (int i = 0, n = a.length; i < n; i++) {
                count[(a[i]/pow)%10 - min]++;
            }

            for (int i = 1; i < size; i++)
                count[i] += count[i - 1];

            for (int i = arr.length - 1; i >= 0; i--) {
                arr[--count[((a[i])/pow)%10 - min]] = a[i];
            }


            for (int i = 0, n = a.length; i < n; i++) {
                a[i] = arr[i];
            }
            pow *= 10;
        }
    }
}
