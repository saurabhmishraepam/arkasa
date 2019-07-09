package com.epam.araksa.generics;

import java.util.Collections;

public class GenericsOne {

    public <T extends Comparable> T getValue(T va, T [] array){
        T maxVal=null;
        for(T t: array){
            if(maxVal==null ||t.compareTo(va)>0){
                maxVal=t;
            }
        }
        return maxVal;
    }

    public static void main(String [] args){

        Box bx=new Box(new Integer(10), new String ("saurabh"));
        bx.display();
    }



}

class Box<T extends Number, U extends Comparable >{
    private T id; //
    private U name;

    public Box(T id, U name){
        this.id=id;
        this.name=name;
    }
    public void display(){
        System.out.println(this.id+"  "+this.name);
    }
}








