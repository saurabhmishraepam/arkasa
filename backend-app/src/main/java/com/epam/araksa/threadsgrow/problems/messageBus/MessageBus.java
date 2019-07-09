package com.epam.araksa.threadsgrow.problems.messageBus;

import java.util.LinkedList;
import java.util.Queue;

public class MessageBus {

    private Queue<String> messages=new LinkedList<>();

    public boolean isEmpty(){
        return messages.isEmpty();
    }

    public Queue<String> getMessages() {
        return messages;
    }


    public synchronized void enqueMessage(String message){

        this.messages.add(message);
    }

    public synchronized String dequeMessage() {
        return messages.poll();
    }


}
