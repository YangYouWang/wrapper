package wrapper.core;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import wrapper.annotion.Wrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * config wrapper
 * @author yangyouwang
 */
@Component
public class ConfigWrapper extends BaseReflexWrapper {

    @Override
    public Map<String, Object> wrapperType(Wrapper wrapperAnnotation, String fieldName, String fieldValue) {
        Map<String, Object> result = new HashMap<>(16);
        for (String configKey : wrapperAnnotation.dictData()) {
            String configValue = CustomizedPropertyPlaceholderConfigurer.getContextProperty(configKey).toString();
            if (fieldValue.equals(configKey)) {
                if (StringUtils.isEmpty(wrapperAnnotation.name())) {
                    result.put(fieldName, configValue);
                } else {
                    result.put(wrapperAnnotation.name(), configValue);
                }
            }
        }
        return result;
    }
}
