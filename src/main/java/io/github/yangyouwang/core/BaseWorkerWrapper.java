package io.github.yangyouwang.core;

import io.github.yangyouwang.annotion.Wrapper;
import io.github.yangyouwang.consts.ConfigConsts;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
     *
     * 反射获取注解属性
     * @param obj 对象
     * @return Map
     */
    public final Map<String, Object> wrapTheMap(Object obj) {
        Map<String, Object> result = new HashMap<>(16);
        // 获取Field
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                // 获取字段相关属性
                String fieldName = field.getName();
                if (!ConfigConsts.SERIAL_VERSION_UID.equals(fieldName)) {
                    field.setAccessible(true);
                    String fieldValue = field.get(obj).toString();
                    if (field.isAnnotationPresent(Wrapper.class)) {
                        Wrapper annotation = field.getAnnotation(Wrapper.class);
                        fieldValue = this.wrapTheType(annotation,fieldName,fieldValue);
                        // 判斷字典键值对是否有值
                        if (!StringUtils.isEmpty(annotation.name())) {
                            result.put(annotation.name(), fieldValue);
                            continue;
                        }
                    }
                    result.put(fieldName, fieldValue);
                }
            }
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有访问权限.");
        } catch (NullPointerException e) {
            throw new RuntimeException("空指针异常了.");
        }
    }

    /**
     * wrapper类型
     * @param annotation 注解
     * @param fieldName  字段
     * @param fieldValue 属性
     * @return value
     */
    protected abstract String wrapTheType(Wrapper annotation,String fieldName,String fieldValue);

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
