package io.github.yangyouwang.core;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 调度
 * @author yangyouwang
 */
public class MasterWrapper {

    /**
     * 应该有一个装任务的集合ConcurrentLinkedQueue
     */
    protected ConcurrentLinkedQueue<Object> workQueue= new ConcurrentLinkedQueue<>();
    /**
     * 使用普通的HashMap去承装所有的Worker对象
     */
    protected Map<Integer ,Thread> workers= new HashMap<>();
    /**
     * 要用一个ConcurrentHashMap去承装每一个Worker执行任务的结果集合，并发执行
     */
    protected ConcurrentHashMap<String ,Map<String,Object>> resultMap= new ConcurrentHashMap<>();

    /**
     * 要有一个构造方法
     * @param workerWrapper  任务
     * @param workerCount 多少任务
     */
    public MasterWrapper(BaseWorkerWrapper workerWrapper, Integer workerCount) {
        // 创建worker对Maset中任务队列的引用,用于任务的领取
        workerWrapper.setWorkerQueue(this.workQueue);
        // 创建worker对Maset中结果集合的引用，用于任务的提交
        workerWrapper.setResultMap(this.resultMap);
        // 循环为多个worker创建线程
        for (int i = 0; i < workerCount; i++) {
            workers.put(i, new Thread(workerWrapper));
        }
    }

    /**
     * 提交方法
     * @param job 任务
     */
    public void submit(Object job){
        this.workQueue.add(job);
    }

    /**
     * 开始运行所有的Worker进程，进行处理
     */
    public void execute(){
        for(Map.Entry<Integer , Thread> worker : workers.entrySet()){
            worker.getValue().start();
        }
    }

    /**
     * 判断线程是否执行完毕
     * @return boolean
     */
    public boolean isComplete() {
        // 判断当所有的worker是否都停止
        for (Map.Entry<Integer, Thread> worker : workers.entrySet()) {
            if (worker.getValue().getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }
    /**
     * 获取worker结果
     * @return 结果
     */
    public List<Map<String,Object>> getResult() {
        List<Map<String, Object>> result = new ArrayList<>(16);
        for (Map.Entry<String, Map<String,Object>> map : resultMap.entrySet()) {
            result.add(map.getValue());
        }
        return result;
    }
}
