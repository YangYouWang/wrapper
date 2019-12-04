package io.github.yangyouwang.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 任务
 * @author yangyouwang
 */
public abstract class BaseWorkerWrapper implements Runnable {

    /**
     * 定义每一个ConcurrentLinkedQueue队列去引用Master中的ConcurrentLinkedQueue
     */
    protected ConcurrentLinkedQueue<Object> workQueue;
    /**
     * 定义一个ConcurrentHashMap去引用Master中的ConcurrentHashMap承装结果集
     */
    protected ConcurrentHashMap<String ,Map<String,Object>> resultMap;

    /**
     * 创建ConcurrentHashMap引用
     * @param workQueue 任务队列
     */
    public void setWorkerQueue(ConcurrentLinkedQueue<Object> workQueue){
        this.workQueue= workQueue;
    }

    /**
     * 创建ConcurrentLinkedQueue引用
     * @param resultMap 子任务
     */
    public void setResultMap(ConcurrentHashMap<String ,Map<String,Object>> resultMap){
        this.resultMap=resultMap;
    }

    /**
     * 子任务处理的逻辑，在子类中实现具体逻辑
     * @param input  Object
     * @return Map<String, Object>
     */
    public abstract Map<String, Object> wrapTheMap(Object input) ;


    @Override
    public void run() {
        while(true){
            //获取子任务
            Object input = workQueue.poll();
            if(null == input){
                break;
            }
            //处理子任务
            Map<String, Object> output = this.wrapTheMap(input);
            this.resultMap.put(Integer.toString(input.hashCode()), output);
        }
    }
}
