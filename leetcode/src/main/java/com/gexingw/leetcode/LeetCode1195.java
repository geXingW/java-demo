package com.gexingw.leetcode;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GeXingW
 */
public class LeetCode1195 {

    private static ReentrantLock lock = new ReentrantLock(true);

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int i = 0;
            while (i < 100) {
                lock.lock();
                System.out.println("A");
                lock.unlock();
                i++;
            }
        });

        Thread thread2 = new Thread(() -> {
            int i = 0;
            while (i < 100) {
                lock.lock();
                System.out.println("B");
                lock.unlock();
                i++;
            }
        });

        thread1.start();
        thread2.start();
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread 线程！");
        }
    }

    static class MyCallable implements Callable<Object> {
        @Override
        public Object call() throws Exception {

            Thread.sleep(5000);

            return new HashMap<String, String>() {
                {
                    put("name", "wsf");
                }
            };
        }
    }

}
