package com.epam.araksa.bitwise;

import java.time.Instant;

public class Factorial {

    public static void main(String [] args){
        Instant start=Instant.now();


        System.out.println(factorial(5));
        Instant end= Instant.now();

        System.out.println(end.toEpochMilli()-start.toEpochMilli());

    }

    public static int factorial(int n){

        return factTail(n,1);

    }

    public static int fact(int i, int n){

        if(i<=n){

            return i* fact(i+1,n);
        }
        return 1;
    }


public static int factTail(int n, int res){

        if(n>0){

            return factTail(n-1,res*n);

        }
        return res;

}




}
