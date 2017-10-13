package com.thread;

import java.util.Random;

/**
 * Created by waitupon17 on 2017/8/26.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    public static void main(String[] args) {

        //启动两个线程
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //创建每个线程私有的变量
                    int data = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName()+" has put data: "+data);
                    //往local里面设置值
                    threadLocal.set(data);
                    //获取自己线程的MyThreadLocalScopeDate实例对象
                    MyThreadLocalScopeDate myData = MyThreadLocalScopeDate.getThreadInstance();
                    myData.setName("name"+data);
                    myData.setAge(data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A{
        public void get(){
            int data =threadLocal.get();
            System.out.println("A from "+Thread.currentThread().getName()+" has get data: "+data);
            MyThreadLocalScopeDate myData = MyThreadLocalScopeDate.getThreadInstance();
            System.out.println("A from "+Thread.currentThread().getName()+" has get MyThreadLocalScopeDate name: "+myData.getName()+" , age: "+myData.getAge());
        }
    }


    static class B{
        public void get(){
            int data =threadLocal.get();
            System.out.println("B from "+Thread.currentThread().getName()+" has get data: "+data);
            MyThreadLocalScopeDate myData = MyThreadLocalScopeDate.getThreadInstance();
            System.out.println("B from "+Thread.currentThread().getName()+" has get MyThreadLocalScopeDate name: "+myData.getName()+" , age: "+myData.getAge());
        }
    }
}

class MyThreadLocalScopeDate{//单例模式

    private MyThreadLocalScopeDate(){};//构造方法私有化
    private static ThreadLocal<MyThreadLocalScopeDate> map = new ThreadLocal<MyThreadLocalScopeDate>();//封装MyThreadLocalScopeDate是线程实现范围内共享

    //思考AB两个线程过来的情况 自己分析 AB都需要的自己的对象 没有关系 所以不需要同步 如果有关系就需要同步了
    public static /*synchronized*/MyThreadLocalScopeDate getThreadInstance(){
        MyThreadLocalScopeDate instance =map.get();
        if(instance==null){
            instance = new MyThreadLocalScopeDate();
            map.set(instance);
        }
        return instance;
    }

    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}