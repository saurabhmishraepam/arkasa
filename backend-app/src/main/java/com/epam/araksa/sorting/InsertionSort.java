package com.epam.araksa.sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String [] args){

        int [] arr={10,7,4,1,11,45};

        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

        for(int i=0,j=1;j<arr.length;j++,i++){
            findJLocationAndInsert(arr, i,j);   }
        System.out.println("");
        Arrays.stream(arr).forEach(v-> System.out.print(v+", "));

    }

    public static void findJLocationAndInsert(int [] arr, int i, int j){
        int tmp=arr[j];
        int indexTill=-1;
        for(int m=0;m<=i;m++){
            if(tmp<arr[m]){
                indexTill=m;
                break;
            }
        }
        if(indexTill!=-1){
            for(int n=j;n>indexTill;n--){
                arr[n]=arr[n-1];
            }
            arr[indexTill]=tmp;
        }


    }


}
