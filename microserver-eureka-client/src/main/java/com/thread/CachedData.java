package com.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by waitupon17 on 2017/8/29.
 */
public class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private Map<String,Object> map = new HashMap<String,Object>();
    public static void main(String[] args) {
        final   CachedData demo = new CachedData();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            final  int tep = i;
            executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + demo.processCachedData("aa"));
                    return "1";
                }
            });

        }
        try {
            Thread.sleep(5000);
            System.out.println(demo.processCachedData("aa"));
            System.out.println(demo.processCachedData("aa"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    Object processCachedData(String key) {
        rwl.readLock().lock();
        Object data = null;
        data = map.get(key);
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid ) {
                    data = "aaa" + Thread.currentThread().getName();
                    cacheValid = true;
                    map.put(key,data);
                }
                // Downgrade by acquiring read lock before releasing write lock
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
        } finally {
            rwl.readLock().unlock();
        }
        return data;
    }

}
