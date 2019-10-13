package wrapper.core;

import org.springframework.stereotype.Component;
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

    public List<Map<String, Object>> wrap(List<?> objs) {
        return objs.stream().map(this::wrap).collect(Collectors.toList());
    }
    public Map<String, Object> wrap(Object obj) {
         BaseReflexWrapper reflex = new ArrayWrapper();
         return reflex.wrapTheMap(obj);
    }
}
