package com.epam.araksa.sorting;

import java.util.Arrays;

public class QuickSort {


    public static void main(String [] args){

        int [] arr ={3,1,4,6,9, 11, 13,23,2};


        Arrays.stream(arr).forEach(v->{
            System.out.print(v+" ");
        });

         quickSort(arr, 0, arr.length-1);
        System.out.println("");

        Arrays.stream(arr).forEach(v->{
            System.out.print(v+" ");
        });


    }

    public static void quickSort(int [] arr, int left, int right){
    if(left<right) {
        int p = findPivot(arr, left, right);
        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    }

    public static int findPivot(int [] arr, int left , int right){

        int pivot= right;

        int i =left;
        int j=right-1;
        while(i<j){

            if(arr [i]>arr[pivot] && arr[j]<arr[pivot]){
                int tmp =arr[i];
                arr[i]=arr[j];
                arr[j]=tmp;
                i++; --j;
            }
            else{
                if (arr[i] < arr[pivot]) {
                    i++;
                }
                if (arr[j] > arr[pivot]) {
                    --j;
                }
            }
        }
        if(arr[i]>arr[pivot]){
            int tmp=arr[i];
            arr[i]=arr[pivot];
            arr[pivot]=tmp;

        }

        else {
            int tmp=arr[j];
            arr[j]=arr[pivot];
            arr[pivot]=tmp;

        }

        return pivot;

    }






}
