package io.github.yangyouwang.core;

import java.util.List;
import java.util.Map;

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
        // 获取结果
        while (true) {
            //判断当所有线程都结束后打印结果
            if (this.isComplete()) {
                return this.getResult();
            }
        }
    }
}
