package com.zhurong.platform.custom.clientimport.handler;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同一 Windows 客户端上的 Lantek 自动化进程必须串行执行。
 */
@Component
public class XyLantekExecutionLock {

    private final ReentrantLock lock = new ReentrantLock(true);

    public <T> T execute(Callable<T> action) throws Exception {
        lock.lockInterruptibly();
        try {
            return action.call();
        } finally {
            lock.unlock();
        }
    }
}
