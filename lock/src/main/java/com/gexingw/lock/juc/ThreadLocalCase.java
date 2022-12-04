package com.gexingw.lock.juc;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/12/4
 * @time: 22:55
 */
public class ThreadLocalCase {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        threadLocal.set("Main-thread");
        Thread thread1 = new Thread("thread-1"){
            @Override
            public void run() {
                System.out.println(threadLocal.get());

                threadLocal.set("thread-1");

                System.out.println(Thread.currentThread().getName() + threadLocal.get());
            }
        };

        Thread thread2 = new Thread("thread-2"){
            @Override
            public void run() {
                System.out.println(threadLocal.get());

                threadLocal.set("thread-2");

                System.out.println(Thread.currentThread().getName() + threadLocal.get());
            }
        };

        Thread thread3 = new Thread("thread-3"){
            @Override
            public void run() {
                System.out.println(threadLocal.get());

                threadLocal.set("thread-3");

                System.out.println(Thread.currentThread().getName() + threadLocal.get());
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(4000);
        System.out.println(threadLocal.get());
    }
}
