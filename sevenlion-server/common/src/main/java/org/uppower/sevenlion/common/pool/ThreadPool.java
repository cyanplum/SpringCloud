package org.uppower.sevenlion.common.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/29 7:27 下午
 */
public class ThreadPool {

    public static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("sevenlion-thread-%s").build();

    public static final ScheduledExecutorService SCHEDULE = new ScheduledThreadPoolExecutor(1, threadFactory);


    public static void registerDelayTask(Runnable runnable, long delay, TimeUnit timeUnit) {
        SCHEDULE.schedule(runnable, delay, timeUnit);
    }
}
