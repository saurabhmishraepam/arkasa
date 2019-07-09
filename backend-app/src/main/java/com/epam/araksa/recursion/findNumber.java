package com.epam.araksa.recursion;

public class findNumber {


    public static void main(String [] args){

        int [] arr=new int [] {1,3,4,6,9,2,5};

        int find=9;

        System.out.println(findX(find, arr, 0, arr.length-1));


    }

    public static boolean findX(int x, int [] arr, int l, int r){

        if(r<l)
            return false;
        if(arr[l]==x)
            return true;
        if(arr[r]==x)
            return true;
        return findX(x, arr, l+1,r-1);



    }



}
