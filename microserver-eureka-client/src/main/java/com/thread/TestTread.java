package com.thread;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by waitupon17 on 2017/8/25.
 */
public class TestTread {
    public static void main(String[] args) {

       final Bussin bussin = new Bussin();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50;i++){
                    bussin.sub(i);
                }
            }
        }).start();

        for(int i=0;i<50;i++){
            bussin.main(i);
        }
        System.out.println("sd");
    }


}
class Bussin{
    BlockingQueue queue1 = new ArrayBlockingQueue(1);
    BlockingQueue queue2 = new ArrayBlockingQueue(1);
    {
        try {
            queue2.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sub(int i){
     // this.notify();
       /*   try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       /* try {
            queue1.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for(int j=0;j<100;j++){
            System.out.println("sub i="+ i +" loop of j=" +j);
        }
      /*  try {
            queue2.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
    public synchronized void main(int i){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      /*  this.notify();
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
      /*  try {
            queue2.put("1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for(int j=0;j<10;j++){
            System.out.println("main i="+ i +" loop of j=" +j);
        }
        /*try {
            queue1.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}