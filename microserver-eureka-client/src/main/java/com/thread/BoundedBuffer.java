package com.thread;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by waitupon17 on 2017/8/31.
 */
public class BoundedBuffer {
    final private Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition(); //写线程条件
    final Condition notEmpty = lock.newCondition(); //读线程条件

    final Object[]objects = new Object[100];
    int putptr = 0/*写索引*/, takeptr = 0/*读索引*/, count = 0/*队列中存在的数据个数*/;

    public void put(Object x){
        try {
            lock.lock();
            if(objects.length == count){
               notFull.await();
            }
            objects[putptr] = x;
            if(++putptr == objects.length){
                putptr=0;
            }
            count ++;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Object take(){
        lock.lock();
        Object obj = null;
        try {
            if(count == 0){
               notEmpty.await();
            }
            obj = objects[takeptr];
            if(++takeptr == objects.length){
                takeptr =0;
            }
            --count;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return obj;
    }

    public static void main(String[] args) {
        final BoundedBuffer buffer = new BoundedBuffer();
            for(int i=0;i<2;i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Random random = new Random();
                        while (true){
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int o = random.nextInt(10);
                            buffer.put(o);
                            System.out.println(Thread.currentThread().getName() + "放入" + o);
                        }

                    }
                }).start();
            }

        for(int i=0;i<1;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Random random = new Random();
                    while (true){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Object o = buffer.take();
                        System.out.println(Thread.currentThread().getName() + "拿走" + o);
                    }

                }
            }).start();
        }
    }
}
