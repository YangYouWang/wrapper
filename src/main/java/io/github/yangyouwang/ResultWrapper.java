package io.github.yangyouwang;

import io.github.yangyouwang.core.MasterWrapper;
import io.github.yangyouwang.factory.FactoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
/**
 * 包装返回结果
 * @author yangyouwang
 */
@Component
public class ResultWrapper  {


    @Autowired
    private FactoryWrapper factoryWrapper;

    /**
     * list数据
     */
    public List<Map<String, Object>> wrap(List<?> objs) {
        MasterWrapper masterWrapper = factoryWrapper.getObject();
        for (Object obj : objs) {
            masterWrapper.submit(obj);
        }
        // 执行
        masterWrapper.execute();
        // 获取结果
        while (true) {
            //判断当所有线程都结束后打印结果
            if (masterWrapper.isComplete()) {
                return masterWrapper.getResult();
            }
        }
    }

    /**
     * 对象
     */
    public Map<String, Object> wrap(Object obj) {
        MasterWrapper masterWrapper = factoryWrapper.getObject();
        masterWrapper.submit(obj);
        // 执行
        masterWrapper.execute();
        // 获取结果
        while (true) {
            //判断当所有线程都结束后打印结果
            if (masterWrapper.isComplete()) {
                return masterWrapper.getResult().get(masterWrapper.getResult().size() - 1);
            }
        }
    }
}
