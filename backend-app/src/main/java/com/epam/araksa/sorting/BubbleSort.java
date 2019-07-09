package com.epam.araksa.sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String [] args){

        int [] arr={10,7,4,1,11,45};

        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

        for(int i=0;i<arr.length-1;i++){

            for(int j=0;j<arr.length-1;j++){

                if(arr[j]>arr[j+1]){
                    int tmp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }

            }

        }
        System.out.println("");
        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

    }


}
