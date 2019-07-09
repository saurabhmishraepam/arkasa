package com.epam.araksa.lambdas;


public class LambdasDiff {

    public static void main(String [] args){

        //seperation of implementation
        Runnable r=()-> System.out.println("Test threads");
        Thread th=new Thread(r);

        // anonymous thread
        new Thread (()->{
            System.out.println("anonymous thread");
        }).start();
        //Just running nothing
        Runnable r1=()->{};
        Runnable r2=()->{return;};
        Runnable r3= new MainWorkRunnable()::run;
        new Thread(r3).start();
    }


}
@FunctionalInterface
interface OtherWork{
    void workMethod();
}

class MainWorkRunnable
        implements Runnable, OtherWork{
    public void run(){
        workMethod();
    }
    @Override
    public void workMethod() {
        System.out.println("running other work ");
    }
}
