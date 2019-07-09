package com.epam.araksa.threadsgrow;

public class InteruptExample {

public static void main(String [] args){
    Task t=new Task();

    Thread th=new Thread(t);
    th.start();

    th.interrupt();

}

}


class Task implements Runnable{
    @Override
    public void run(){

        System.out.println("I am strarting this task");
      /*  try {
        //    Thread.sleep(4000);
        }catch (InterruptedException ex){
            System.out.println(ex);
        }*/
      for(int i=1;i<2000000000;i++){}

        System.out.println("task is completed");

    }

}





