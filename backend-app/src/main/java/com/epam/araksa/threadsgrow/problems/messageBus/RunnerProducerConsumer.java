package com.epam.araksa.threadsgrow.problems.messageBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class RunnerProducerConsumer {

    public static void main(String [] args){
        MessageBus bus=new MessageBus();
        ExecutorService exConsumer=Executors.newFixedThreadPool(10);
        ExecutorService exProducer=Executors.newFixedThreadPool(10);


        //exProducer.submit(new Producer(bus));
       /* exProducer.submit(new Producer(bus));
        exProducer.submit(new Producer(bus));
        exProducer.submit(new Producer(bus));

        exProducer.submit(new Producer(bus));
        exProducer.submit(new Producer(bus));
        exProducer.submit(new Producer(bus));
        exProducer.submit(new Producer(bus));*/
       // exConsumer.submit(new Consumer(bus));
       /* exConsumer.submit(new Consumer(bus));
        exConsumer.submit(new Consumer(bus));
        exConsumer.submit( new Consumer(bus));
        exConsumer.submit(new Consumer(bus));
        exConsumer.submit( new Consumer(bus));
        exConsumer.submit( new Consumer(bus));
        exConsumer.submit(new Consumer(bus));
        exConsumer.submit(new Consumer(bus));
        exConsumer.submit(new Consumer(bus));*/


    }

}

class ProducerConsumer implements Runnable{
    private MessageBus messageBus;
    public ProducerConsumer(MessageBus bus){
           this.messageBus=bus;
    }

    @Override
    public void run(){
      while(true){

      }

    }

    private void produce(){

        synchronized (this.messageBus) {
            int t = 100;
            while (t > 0) {
                if (!messageBus.isEmpty()) {
                    try {
                        System.out.println("Not yet consumed waiting for someone to consume before I produce");
                        wait();
                    } catch (InterruptedException ex) {
                        System.out.println("I am getting Interrupted ");
                        return;
                    }
                } else {
                    messageBus.enqueMessage("Message NO: " + ThreadLocalRandom.current().nextInt());
                    notify();
                }
                t--;
            }
        }


    }

    private void consume(){  synchronized (this.messageBus) {
        int t = 100;
        while (t > 0) {
            if (messageBus.isEmpty()) {
                try {
                    System.out.println("Nothing to consumer going to wait releasing the lock");
                    wait();
                } catch (InterruptedException ex) {
                    return;
                }

            } else {
                System.out.println(messageBus.dequeMessage());
                notify();
            }
            t--;
        }
    }}



}

class Producer implements Runnable{
    private MessageBus messageBus;
    public Producer(MessageBus bus){
        this.messageBus=bus;
    }
    @Override
    public void run() {

    }

}

