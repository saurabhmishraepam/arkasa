package com.epam.araksa.lambdas;

import java.util.Objects;

public class FunctionalInterfaceSandBox {

}

@FunctionalInterface
interface PredicateCustom<T>{
    boolean test(T t);
    default void CheckNull(T t){
       if( Objects.isNull(t)){
           //throw your exception
       }
    }
}
@FunctionalInterface
interface ConsumerCustom<T>{
    void accept(T t);
}
@FunctionalInterface
interface SupplierCustom< T>{
    T get();
}

@FunctionalInterface
interface FunctionCustom<T,R>{
    R apply(T t);
}






