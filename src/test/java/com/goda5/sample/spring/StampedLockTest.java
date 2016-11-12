package com.goda5.sample.spring;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private static final StampedLock lock = new StampedLock();
    private static final StampedLock lock2 = new StampedLock();
    private static final ReentrantLock lock3 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(lock.readLock());
        System.out.println(lock.tryUnlockRead());
        System.out.println(lock.tryReadLock());
        System.out.println(lock.tryConvertToWriteLock(257));
        System.out.println(lock.tryWriteLock());
        System.out.println(lock.isReadLocked());
        System.out.println(lock.isWriteLocked());
        System.out.println(lock.tryUnlockWrite());

        System.out.println(lock2.tryWriteLock());
        System.out.println(lock2.tryReadLock());
        System.out.println(lock2.tryWriteLock());
        System.out.println(lock2.isReadLocked());
        System.out.println(lock2.isWriteLocked());

        lock3.tryLock();
        String a = null;
        try {
            a.length();
        } catch(Exception ex) {

        } finally {
            lock3.unlock();
        }

        Thread thread = new Thread(() -> {
            try {
                System.out.println("start1");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("exception1");
            } finally {
                System.out.println("finally1");
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.stop();

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("start2");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("exception2");
            } finally {
                System.out.println("finally2");
            }
        });
        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();


        Thread thread4 = new Thread(() -> {
            try {
                System.out.println("start4");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("exception4");
            } finally {
                System.out.println("finally4");
            }
        });
        thread4.start();
        Thread.sleep(1000);
        thread4.suspend();

        Thread thread3 = new Thread(() -> {
            try {
                System.out.println("start3");
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("exception3");
            } finally {
                System.out.println("finally3");
            }
        });
        thread3.start();
        Thread.sleep(1000);
        System.exit(0);
    }

}
