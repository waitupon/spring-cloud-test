package com.thread;

import java.util.concurrent.CountDownLatch;

class Driver { // ...
    public static void main(String[] args) throws InterruptedException{
     Driver d = new Driver();
     int N = 5;
     CountDownLatch startSignal = new CountDownLatch(1);
     CountDownLatch doneSignal = new CountDownLatch(N);

     for (int i = 0; i < N; ++i) // create and start threads
       new Thread(new Worker(startSignal, doneSignal)).start();

     d.doSomethingElse();            // don't let run yet
     startSignal.countDown();      // let all threads proceed
     d.doSomethingElse();
     doneSignal.await();           // wait for all to finish
        System.out.println("done!");
   }

     void doSomethingElse() {
        System.out.println(Thread.currentThread().getName() + " do");
    }
}

 class Worker implements Runnable {
   private final CountDownLatch startSignal;
   private final CountDownLatch doneSignal;
   Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
      this.startSignal = startSignal;
      this.doneSignal = doneSignal;
   }
   public void run() {
      try {
        startSignal.await();
        doWork();
        doneSignal.countDown();
      } catch (InterruptedException ex) {} // return;
   }

   void doWork() {
       System.out.println(Thread.currentThread().getName() + " do");
   }
 }