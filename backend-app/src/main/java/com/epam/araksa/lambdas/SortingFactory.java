package com.epam.araksa.lambdas;

import java.util.Comparator;

public class SortingFactory {

    public Comparator<Person> getComparatorByName(){
        return (p1, p2)->p1.getName().compareTo(p2.getName());
    }

    public Comparator<Person> getComparatorByAge(){
        return (p1, p2)->p1.getAge().compareTo(p2.getAge());
    }
}
