package com.gexingw.lock.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/11/22
 * @time: 10:40
 */
public class ReentrantLockCase {
    public static void main(String[] args) {

        // 非公平锁
        ReentrantLock unfairReentrantLock = new ReentrantLock();
        for (int i = 0; i < 50; i++) {
            Thread unfairThread = new Thread("UnfairLock-" + i) {

                @Override
                public void run() {
                    unfairReentrantLock.lock();
                    System.out.println(Thread.currentThread().getName());
                    unfairReentrantLock.unlock();
                }
            };

            unfairThread.start();
        }

        // 公平锁
        ReentrantLock fairReentrantLock = new ReentrantLock(true);

        for (int i = 0; i < 50; i++) {
            Thread fairThread = new Thread("FairLock-" + i) {
                @Override
                public void run() {
                    fairReentrantLock.lock();
                    System.out.println(Thread.currentThread().getName());
                    fairReentrantLock.unlock();
                }
            };

            fairThread.start();
        }

    }
}
