package io.github.yangyouwang.core;


/**
 * 缓存池
 * @author yangyouwang
 */
public class CachePool {

    /**
     * 最大可用的CPU核数 x 2
     */
    public static final int PROCESSORS = Runtime.getRuntime().availableProcessors() << 1;
}
