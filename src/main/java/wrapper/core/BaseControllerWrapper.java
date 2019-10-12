package wrapper.core;

import wrapper.annotion.Warpper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper包装类
 *
 * @author yangyouwang
 */
public class BaseControllerWrapper {


    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    public List<Map<String, Object>> wrap(List<?> objs) {
        return objs.stream().map(this::wrap).collect(Collectors.toList());
    }

    public Map<String, Object> wrap(Object obj) {
        Map<String, Object> result = new HashMap<>(16);
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String value = field.get(obj).toString();
                if (!SERIAL_VERSION_UID.equals(field.getName()) && field.isAnnotationPresent(Warpper.class)) {
                    Warpper warpper = field.getAnnotation(Warpper.class);
                    if (!"".equals(warpper.dictType())) {
                        // TODO: 2019/10/12 这块具体看自己数据库中的枚举表结构
                        continue;
                    } else {
                        for (String dict : warpper.dictData()) {
                            String[] split = dict.split(":");
                            if (split.length > 0 && value.equals(split[0])) {
                                result.put(field.getName(), split[1]);
                            }
                        }
                    }
                    continue;
                }
                result.put(field.getName(), value);
            }
            return result;

        } catch (IllegalAccessException e) {
            throw new RuntimeException("没有访问权限.");
        } catch (Exception e) {
            throw new RuntimeException("出错了.");
        }
    }

    public void hello() {
        System.out.println("hello");
    }
}
