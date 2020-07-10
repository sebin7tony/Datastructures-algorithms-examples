package com.sebin.interview.sort.heap;

import java.nio.file.LinkPermission;

public class HeapSort {

    private void heapify(int[] input,int n,int index){
        int largest = index;
        int left = index*2 +1;
        int right = index*2 +2;

        if(left < n && input[left] > input[largest]){
            largest = left;
        }

        if(right < n && input[right] > input[largest]){
            largest = right;
        }

        if(index != largest){
            int tmp = input[index];
            input[index] = input[largest];
            input[largest] = tmp;
            heapify(input,n,largest);
        }
    }

    public void heapSort(int[] input){
        for(int i=(input.length/2)-1;i>=0;i--){
            heapify(input,input.length,i);
        }

        for(int i=input.length-1 ; i>=0 ; i--){
            int tmp = input[0];
            input[0] = input[i];
            input[i] = tmp;
            heapify(input,i,0);
        }
    }

    private void printArray(int[] input){
        for(int i=0 ;i<input.length ;i++){
            System.out.print(input[i]+" ");
        }
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] input = {12, 11, 13, 5, 6, 7};
        heapSort.heapSort(input);
        heapSort.printArray(input);
    }
}
