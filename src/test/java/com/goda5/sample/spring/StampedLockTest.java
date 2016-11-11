package com.goda5.sample.spring;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private static final StampedLock lock = new StampedLock();
    private static final StampedLock lock2 = new StampedLock();

    public static void main(String[] args) {
        lock.readLock();
        lock2.writeLock();
        System.out.println(lock.tryUnlockRead());
        System.out.println(lock.tryReadLock());
        System.out.println(lock.tryWriteLock());
        System.out.println(lock2.tryUnlockWrite());
        System.out.println(lock.isReadLocked());
        System.out.println(lock.isWriteLocked());
        System.out.println(lock2.isReadLocked());
        System.out.println(lock2.isWriteLocked());
    }
}
