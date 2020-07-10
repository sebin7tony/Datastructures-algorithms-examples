package com.sebin.interview.sort.quick;

import com.sebin.interview.sort.merge.MergeSort;

public class QuickSort {

    public static void quickSort(int[] input , int start, int end){
        if(start < end){
            int pivot = partition(input,start,end);
            quickSort(input,start,pivot-1);
            quickSort(input,pivot+1,end);
        }
    }

    private static int partition(int[] input , int start, int end){
        int high = end,low = start;
        for(int i = start; i<end; i++){
            if(input[high] > input[i]) {
                swap(input, low, i);
                low++;
            }
        }
        swap(input, low, high);
        return low;
    }

    private static void swap(int[] input , int start, int end){
        int temp = input[start];
        input[start] = input[end];
        input[end] = temp;
    }

    public static void main(String[] args){
        int[] input = {38,5,27,43,11,3,9,82,10};
        QuickSort.quickSort(input,0,input.length-1);

        for(int i = 0;i< input.length;i++){
            System.out.print(input[i]+" ,");
        }
    }
}
