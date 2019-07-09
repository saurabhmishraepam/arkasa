package com.epam.araksa.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void main(String [] args){

        int [] arr={10,7,4,1,6, 5, 97,11,45};

        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

        divide(arr,0,arr.length-1);
        System.out.println("");
        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

    }


    public static void divide(int [] arr, int left, int right){

     if(left<right){

         int mid = left+(right- left)/2;
         divide(arr, left, mid);
         divide(arr,  mid+1, right);
         merge(arr, left, right, mid);

     }


    }

    public static void merge(int [] arr, int left, int right, int mid){

        // iterate over left and right array and keep swapping
        int [] mergeArr=new int[(mid-left)+(right-mid)+1];
        for(int i=left, j=mid; i<mid && j<=right;i++,j++){

            if(arr[i]>arr[j]){
                swap(arr,i, j);
            }
        }
    }

    public static void swap(int [] arr, int i, int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }


}
