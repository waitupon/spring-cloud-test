package com.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by waitupon17 on 2017/8/29.
 */
public class CacheDemo {
    private  Map<String,Object> map = new HashMap<String,Object>();
    volatile boolean cacheValid;
    public static void main(String[] args) {
      final   CacheDemo demo = new CacheDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(int i=0;i<10;i++){
            final  int tep = i;
            executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(simpleDateFormat.format(new Date()) + "   "+ Thread.currentThread().getName() + demo.get("aa"));
                    return "1";
                }
            });

        }
        try {
            Thread.sleep(2000);
            System.out.println(demo.get("aa"));
            System.out.println(demo.get("aa"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private  ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public  Object get(String key){
        lock.readLock().lock();
        Object value = null;
        try{
            value = map.get(key);
            if(!cacheValid){
                lock.readLock().unlock();
                lock.writeLock().lock();
                if(!cacheValid && value==null){
                    value = "aa" +Thread.currentThread().getName();
                    map.put(key,value);
                    cacheValid= true;
                }
                lock.readLock().lock();
                lock.writeLock().unlock();

            }
        }finally {
            lock.readLock().unlock();
        }
        return value;
    }
}
