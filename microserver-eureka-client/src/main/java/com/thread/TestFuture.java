package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by waitupon17 on 2017/9/1.
 */
public class TestFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future> taskResults = new ArrayList<Future>();
        taskResults.add(executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " haved!");
                return "1";
            }
        }));

        taskResults.add(executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {

                System.out.println(Thread.currentThread().getName() + " exetor!");
                return "1";
            }
        }));

        taskResults.add(executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {

                System.out.println(Thread.currentThread().getName() + " pull!");
                return "1";
            }
        }));

        while (true) {
            boolean isAllDone = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Future<String> taskResult : taskResults) {
                isAllDone &= (taskResult.isDone() || taskResult.isCancelled());
                System.out.println("now isDone:" + taskResult.isDone() );
                System.out.println("now isAllDone:" + isAllDone);
            }
            if (isAllDone) {
                // 任务都执行完毕，跳出循环
                break;
            }
           /* try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
       System.out.println("done!");
    }
}
