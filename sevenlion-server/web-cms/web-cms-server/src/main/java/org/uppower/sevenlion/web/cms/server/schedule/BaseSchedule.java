package org.uppower.sevenlion.web.cms.server.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/9 3:29 下午
 */
@Slf4j
public class BaseSchedule {

    private static final ThreadFactory BASE_SCHEDULE_THREAD_FACTORY = new ThreadFactoryBuilder().setNameFormat("base-schedule-%s").build();

    private static  final ScheduledExecutorService SCHEDULE_EXECUTOR_SERVICE_POOL = new ScheduledThreadPoolExecutor(2, BASE_SCHEDULE_THREAD_FACTORY);

    public static void register(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        SCHEDULE_EXECUTOR_SERVICE_POOL.scheduleAtFixedRate(runnable, initialDelay, period, unit);
    }
}
