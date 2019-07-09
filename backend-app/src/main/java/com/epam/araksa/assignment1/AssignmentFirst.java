package com.epam.araksa.assignment1;

public class AssignmentFirst {


    public  <T> void swap(T [] values , int i , int j){
        // array is not valid
        if(values ==null ||values.length==0){
            throw new IllegalArgumentException("Empty Array");
        }
        // swap index not valid
        if(i<0 || i>values.length-1 || j <0 || j>values.length-1){
            throw new IllegalArgumentException("Invalid swap arrgument exception");
        }
        T tmp=values[i];
        values[i]=values[j];
        values[j]=tmp;
    }


public static void main(String [] args){

    Integer [] intARR=new Integer[] {1,4,5,6,9};
    String [] strArr=new String[]{"a", "b", "c", "d", "e"};

    AssignmentFirst as=new AssignmentFirst();
    as.swap(intARR, 0, 2);
    try {
        as.swap(intARR, -1, 2);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }


    try {
        as.swap(null, 0, 4);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    as.swap(strArr, 0, 2);
    try {
        as.swap(strArr, -1, 2);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }


    try {
        as.swap(null, 0, 4);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }

}



}
