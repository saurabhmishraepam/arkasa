package com.epam.araksa.bitwise;

public class Rope {

    public static void main(String [] args){

        String val="";
        System.out.println(ropebreak(12, 6,4,3, val));
        System.out.println("==="+val);

    }

    public static int ropebreak(int n, int a, int b, int c, String val){

            if(n<0)
                return -1;
            if(n==0)
                return 0;

            int aCount= ropebreak (n - a, a, b, c,val+"a");
            int bCount=ropebreak(n - b, a, b, c, val+"b");
            int cCount=ropebreak(n - c, a, b, c,val+"c");


            int valM= Math.max(Math.max(aCount,bCount), cCount);

            if(valM==-1)
                return -1;
            else return  valM+1;



    }




}
