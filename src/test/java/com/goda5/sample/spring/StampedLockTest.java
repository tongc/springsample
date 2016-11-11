package com.goda5.sample.spring;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private static final StampedLock lock = new StampedLock();
    private static final StampedLock lock2 = new StampedLock();

    public static void main(String[] args) {
        lock.readLock();
        lock2.writeLock();
        lock.tryUnlockRead();
        lock.tryUnlockWrite();
        lock2.tryUnlockWrite();
    }
}
