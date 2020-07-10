package com.sebin.interview.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ProducerConsumer {

    Queue<String> queue = new LinkedList<>();
    int stackSize = 10;
    Object lock = new Object();

    public void startProduce() throws InterruptedException {
        Runnable producerRunnable = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while(true){

                    synchronized (lock){

                        while(queue.size() >= stackSize){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        queue.add("work "+count);
                        System.out.println("Producer added to the stack "+count);
                        lock.notify();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                }
            }
        };

        Runnable consumerRunnable = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while(true){

                    synchronized (lock){

                        while(queue.size() == 0){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        String result = queue.poll();
                        System.out.println("Consumer consumed "+result);
                        lock.notify();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;

                }
            }
        };

        Thread producer = new Thread(producerRunnable);
        Thread consumer = new Thread(consumerRunnable);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    public static void main(String[] args) throws InterruptedException {

        ProducerConsumer producerConsumer = new ProducerConsumer();
        producerConsumer.startProduce();
    }
}
