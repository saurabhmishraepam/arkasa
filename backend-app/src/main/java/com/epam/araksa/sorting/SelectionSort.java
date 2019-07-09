package com.epam.araksa.sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String [] args){

        int [] arr={10,7,4,1,11,45};

        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

        for(int i=0, j=arr.length-1;i<j;j--){

           int index= findMaxBetween(arr, i,j);
           if(index!=-1) {
               int tmp = arr[j];
               arr[j] = arr[index];
               arr[index] = tmp;
           }


        }
        System.out.println("");
        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

    }

    public static int findMaxBetween(int [] arr, int i, int j){
        int index=-1;
        int maxVal=Integer.MIN_VALUE;
        if(i<j){
            while(i<=j){
                if(arr[i]>maxVal){
                    maxVal=arr[i];
                    index=i;

                }
                i++;
            }

        }
        return index;
    }

}
