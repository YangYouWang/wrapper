package io.github.yangyouwang.core;

import java.util.*;
/**
 * Wrapper包装类
 *
 * @author yangyouwang
 */
public class ControllerWrapper {
    /**
     * 最大可用的CPU核数 x 2
     */
    protected static final int PROCESSORS = Runtime.getRuntime().availableProcessors() << 1;

    private static ControllerWrapper instance;

    private ControllerWrapper(){}

    public static ControllerWrapper getInstance() {
        if (instance == null) {
            instance = new ControllerWrapper();
        }
        return instance;
    }

    public List<Map<String, Object>> wrap(List<?> objs) {
        ResultWrapper resultWrapper = new ResultWrapper(new ArrayWrapper(), PROCESSORS);
        for (Object obj : objs) {
            resultWrapper.submit(obj);
        }
        return resultWrapper.getResultWrapper();
    }

    public Map<String, Object> wrap(Object obj) {
        ResultWrapper resultWrapper = new ResultWrapper(new ArrayWrapper(), PROCESSORS);
        resultWrapper.submit(obj);
        return resultWrapper.getResultWrapper().get(resultWrapper.getResult().size() - 1);
    }
}
