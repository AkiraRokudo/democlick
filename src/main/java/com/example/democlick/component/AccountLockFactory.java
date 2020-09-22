package com.example.democlick.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author AkiraRokudo on 23.09.2020 in one of sun day
 */
@Component
public class AccountLockFactory {

    private volatile ConcurrentHashMap<Long, Lock> lockFactory;

    public ConcurrentHashMap<Long, Lock> getLockFactory() {
        if (lockFactory == null) {
            synchronized (this) {
                if (lockFactory == null) {
                    lockFactory = new ConcurrentHashMap<>();
                }
            }
        }
        return lockFactory;
    }

    public Lock getLockByAccountId(Long id) {
        if (getLockFactory().containsKey(id)) {
            return getLockFactory().get(id);
        } else {
            synchronized (this) {
                if (!getLockFactory().containsKey(id)) {
                    ReentrantLock lock = new ReentrantLock();
                    getLockFactory().put(id, lock);
                    return lock;
                } else {
                    return getLockFactory().get(id);
                }
            }

        }

    }
}
