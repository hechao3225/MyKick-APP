package com.lj.mykicklj23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LJ on 2016/6/20.
 */
public class ThreadPoolManagerlj23 {
    private static ThreadPoolManagerlj23 managerlj23;
    private static ExecutorService servicelj23;

    private ThreadPoolManagerlj23() {
        int i = Runtime.getRuntime().availableProcessors();
        servicelj23 = Executors.newFixedThreadPool(i * 4);
    }

    public static ThreadPoolManagerlj23 getInstancelj23() {
        if (managerlj23 == null) {
            managerlj23 = new ThreadPoolManagerlj23();
        }
        return managerlj23;
    }

    public void executelj23(Runnable runnable) {
        servicelj23.execute(runnable);
    }
}
