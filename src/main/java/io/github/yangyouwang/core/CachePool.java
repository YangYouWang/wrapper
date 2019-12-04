package io.github.yangyouwang.core;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 缓存池
 * @author yangyouwang
 */
public class CachePool {

    /**
     * 最大可用的CPU核数 x 2
     */
    protected static final int PROCESSORS = Runtime.getRuntime().availableProcessors() << 1;
    /**
     * 排序字段队列
     */
    protected static ConcurrentLinkedQueue<String> FIELD_NAME_QUEUE = new ConcurrentLinkedQueue();
}
