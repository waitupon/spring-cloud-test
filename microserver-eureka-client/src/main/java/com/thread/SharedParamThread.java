package com.thread;

import com.netflix.appinfo.MyDataCenterInfo;

import java.util.Random;

/**
 * Created by waitupon17 on 2017/8/26.
 */
public class SharedParamThread {
    private static ThreadLocal<Integer>intLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    intLocal.set(data);
                    MyData data1 = MyData.newInstance();
                    data1.setAge(data+ "");
                    data1.setName("name" + data);
                   new A().get();
                   new B().get();

                }
            }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int data = new Random().nextInt();
                intLocal.set(data);
                MyData data1 = MyData.newInstance();
                data1.setAge(data+ "");
                data1.setName("name" + data);
                new A().get();
                new B().get();

            }
        }).start();
    }

    static class A{
        void get(){
            System.out.println(" 111" + Thread.currentThread().getName() + " "+ intLocal.get());
            MyData data = MyData.newInstance();
            System.out.println(Thread.currentThread().getName() + data.getName());
        }
    }

    static class B{
        void get(){
            System.out.println(" 111" + Thread.currentThread().getName() + " "+ intLocal.get());
            System.out.println(Thread.currentThread().getName() + MyData.newInstance().getName());
        }
    }


}
class MyData{
    private MyData(){}

    public static ThreadLocal<MyData>threadLocal = new ThreadLocal<MyData>();

    public static  MyData newInstance(){
        MyData myData = threadLocal.get();
        if(myData==null){
            myData = new MyData();
        }
        threadLocal.set(myData);

        return myData;
    }

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}