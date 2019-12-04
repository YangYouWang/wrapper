package io.github.yangyouwang.core;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 包装返回结果
 * @author yangyouwang
 */
public class ResultWrapper extends MasterWrapper {

    /**
     * 构造方法
     *
     * @param workerWrapper 任务
     * @param workerCount   多少任务
     */
    protected ResultWrapper(BaseWorkerWrapper workerWrapper, Integer workerCount) {
        super(workerWrapper, workerCount);
    }

    protected List<Map<String,Object>> getResultWrapper() {
        // 执行
        this.execute();
        // 获取队列
        ConcurrentLinkedQueue<String> queue = CachePool.FIELD_NAME_QUEUE;
        // 去重缓存
        HashSet fieldNameCache = new HashSet();
        // 获取结果
        while (true) {
            //判断当所有线程都结束后打印结果
            if (this.isComplete()) {
                while (!queue.isEmpty()){
                    fieldNameCache.add(queue.poll());
                }
                List<Map<String, Object>> result = this.getResult();
                for (Object fieldName : fieldNameCache){
                    Collections.sort(result, (v1, v2) -> {
                        Integer f1 = Integer.valueOf(v1.get(fieldName).toString()) ;
                        Integer f2 = Integer.valueOf(v2.get(fieldName).toString()) ;
                        return f1.compareTo(f2);
                    });
                }
                return result;
            }
        }
    }
}
