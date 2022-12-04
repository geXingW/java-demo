package com.gexingw.lock.juc;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/12/4
 * @time: 22:08
 */
public class CountDownLatchCase {
    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch();

        // 使用Join的实现
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread-1 finished");
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread-2 finished.");
            }
        };

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Main thread finished");


        CountDownLatch count = new CountDownLatch(100);

        // countDownLatch操作
        ExecutorService poolExecutor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            poolExecutor.submit(() -> {
                count.countDown();
                System.out.println(count.getCount());
            });
        }

        count.await();
        System.out.println("执行完毕");
        System.out.println(count.getCount());
    }
}
