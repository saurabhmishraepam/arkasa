package com.epam.araksa.threadsgrow;

import java.util.concurrent.*;

public class CompletableFuturePlay {


    public static void main(String [] args){


        TaskUpdate tup=new TaskUpdate();
        TaskUpdate tup1=new TaskUpdate();
        TaskUpdate tup2=new TaskUpdate();
        TaskUpdate tup3=new TaskUpdate();
        TaskUpdate tup4=new TaskUpdate();
        TaskUpdate tup5=new TaskUpdate();

        CompletableFuture cmF=CompletableFuture.supplyAsync(()->{
           return tup.call();

        });
        cmF.thenApply((v)->{
            System.out.println(v);
            return v.toString();
        });
      /*  try {
            cmF.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        //  cmF.
       // cmF.
        CompletableFuture cmF1=CompletableFuture.runAsync(()->{
            tup1.call();

        });
        cmF1.thenAccept(v-> System.out.println(v));

    }

}


class TaskUpdate  {
    public String call(){
        try{
            Thread.sleep(4000);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("I am getting invoked");
        return "processed string";
    }
}

