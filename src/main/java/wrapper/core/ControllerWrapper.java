package wrapper.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper包装类
 *
 * @author yangyouwang
 */
@Component
public class ControllerWrapper {

    @Resource
    private ArrayWrapper arrayWrapper;

    private static ControllerWrapper instance;

    private ControllerWrapper(){}

    public static ControllerWrapper getInstance() {
        if (instance == null) {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
            instance = (ControllerWrapper) applicationContext.getBean("controllerWrapper");
        }
        return instance;
    }

    public List<Map<String, Object>> wrap(List<?> objs) {
        return objs.stream().map(this::wrap).collect(Collectors.toList());
    }
    public Map<String, Object> wrap(Object obj) {
         return arrayWrapper.wrapTheMap(obj);
    }
}
