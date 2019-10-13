package wrapper.core;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import wrapper.annotion.Wrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Array Wrap
 * @author yangyouwang
 */
@Component
public class ArrayWrapper extends BaseReflexWrapper {

    @Override
    public Map<String, Object> wrapperType(Wrapper wrapperAnnotation, String fieldName, String fieldValue) {
        Map<String, Object> result = new HashMap<>(16);
        for (String dict : wrapperAnnotation.dictData()) {
            String[] split = dict.split(":");
            if (fieldValue.equals(split[0])) {
                if (StringUtils.isEmpty(wrapperAnnotation.name())) {
                    result.put(fieldName, split[1]);
                } else {
                    result.put(wrapperAnnotation.name(), split[1]);
                }
            }
        }
        return result;
    }
}
