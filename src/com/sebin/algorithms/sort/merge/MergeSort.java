package com.sebin.interview.sort.merge;

public class MergeSort {

    public static void mergeSort(int[] input, int start, int end){
        if(start < end){
            int mid = (start+end)/2;
            mergeSort(input,start,mid);
            mergeSort(input,mid+1,end);
            merge(input,start,mid,end);
        }
    }

    private static void merge(int[] input, int start, int mid ,int end){
        int len1 = mid+1 - start;
        int len2 = end - mid;

        int[] arr1 = new int[len1];
        int[] arr2 = new int[len2];

        for(int i=0;i<len1;i++){
            arr1[i] = input[start+i];
        }

        for(int i=0;i<len2;i++){
            arr2[i] = input[mid+1+i];
        }

        int l = 0;
        int r = 0;
        int k = start;
        while(l < len1 && r < len2){
            if(arr1[l] < arr2[r]){
                input[k] = arr1[l];
                l++;
            }else{
                input[k] = arr2[r];
                r++;
            }
            k++;
        }

        while(l < len1 ){
            input[k] = arr1[l];
            l++;k++;
        }

        while(r < len2 ){
            input[k] = arr2[r];
            r++;k++;
        }
    }

    public static void main(String[] args){
        int[] input = {38,27,43,3,9,82,10};
        MergeSort.mergeSort(input,0,input.length-1);

        for(int i = 0;i< input.length;i++){
            System.out.print(input[i]+" ,");
        }
    }


}
