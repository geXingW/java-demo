package com.gexingw.lock.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: GeXingW
 * @date: 2022/12/3
 * @time: 16:05
 */
public class ReentrantReadWriteLockCase {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        // 获取释放读锁
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        readLock.lock();
        System.out.println("获取读锁");
        readLock.unlock();
        System.out.println("释放读锁");

        // 获取释放写锁
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        System.out.println("获取写锁");
        writeLock.unlock();
        System.out.println("释放写锁");

        // 读锁等待写锁
        Thread thread1 = new Thread("读锁等待写锁") {
            @Override
            public void run() {
                readLock.lock();
                System.out.println("读锁等待写锁，读锁加锁成功！");


                readLock.unlock();
                System.out.println("读锁等待写锁，读锁释放成功！");
            }
        };

        Thread thread2 = new Thread("读锁等待写锁") {
            @Override
            public void run() {
                writeLock.lock();
                System.out.println("读锁等待写锁，写锁加锁成功！");

                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                writeLock.unlock();
                System.out.println("读锁等待写锁，写锁释放成功！");
            }
        };

        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
    }
}
