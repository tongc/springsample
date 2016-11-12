package com.goda5.sample.spring;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private static final StampedLock lock = new StampedLock();
    private static final StampedLock lock2 = new StampedLock();
    private static final ReentrantLock lock3 = new ReentrantLock();

    public static void main(String[] args) {
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
    }

}
