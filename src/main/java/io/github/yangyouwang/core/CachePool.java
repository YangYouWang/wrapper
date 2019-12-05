package io.github.yangyouwang.core;

import java.util.HashSet;
import java.util.Set;

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
     * 排序字段
     */
    protected static volatile Set<String> FIELD_NAME_SET = new HashSet<>();
}
