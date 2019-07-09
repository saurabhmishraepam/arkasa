package com.epam.araksa.assignment1;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CustomList<T> {

    private T [] tArray;
    private int maxCapacity;

    public CustomList(int maxCapacity){
        //max Capacity validate
        if(maxCapacity <=0){
            throw new IllegalArgumentException("Bad input");
        }
        this.maxCapacity=maxCapacity;
        Type sooper = getClass().getGenericSuperclass();
      //  Type t = ((ParameterizedType)sooper).getActualTypeArguments()[ 0 ];
        System.out.println(sooper);

       // tArray=new T[maxCapacity];
    }




}
