package wrapper.core;

import org.springframework.util.ReflectionUtils;
import wrapper.annotion.Warpper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper包装类
 * @author yangyouwang
 */
public class BaseControllerWrapper {


    private String SERIAL_VERSION_UID = "serialVersionUID";

    public List<Map<String,Object>> wrap(List<?> objs)  {
        return objs.stream().map(this::wrap).collect(Collectors.toList());
    }


    public Map<String,Object> wrap(Object obj)  {
//       return Arrays.stream(obj.getClass().getDeclaredFields())
//                .filter(field -> !SERIAL_VERSION_UID.equals(field.getName())
//                        && field.isAnnotationPresent(Warpper.class))
//                .collect(Collectors.toMap(
//                        Field::getName,
//                        field -> {
//                            try {
//                                Method method = new PropertyDescriptor(field.getName(), obj.getClass()).getReadMethod();
//                                String fieldValue = ReflectionUtils.invokeMethod(method, obj).toString();
//                                Warpper warpper = field.getAnnotation(Warpper.class);
//                                if (!"".equals(warpper.dictType())) {
//                                    // 这块具体看自己数据库中的枚举表结构
//
//                                } else {
//                                    return Arrays.stream(warpper.dictData())
//                                            .map(dict -> dict.split(":"))
//                                            .filter(dict -> dict.length > 0 && dict[0].equals(fieldValue))
//                                            .findAny().equals(null);
//                                }
//                                return null;
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                throw new RuntimeException("出错了.");
//                            }
//                        }
//                ));

        try {
            Map<String,Object> result = new HashMap<>(16);
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Method method = new PropertyDescriptor(field.getName(), clazz).getReadMethod();
                String fieldValue = ReflectionUtils.invokeMethod(method, obj).toString();
                if (!SERIAL_VERSION_UID.equals(field.getName())
                        && field.isAnnotationPresent(Warpper.class)) {
                    Warpper warpper = field.getAnnotation(Warpper.class);
                    if (!"".equals(warpper.dictType())) {
                        // 这块具体看自己数据库中的枚举表结构

                    } else {
                        for (String dict: warpper.dictData()) {
                            String[] split = dict.split(":") ;
                            if (split.length > 0 && fieldValue.equals(split[0])) {
                                result.put(field.getName(),split[1]);
                            }
                        }
                    }
                    continue;
                }
                result.put(field.getName(),fieldValue);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void hello(){
        System.out.println("hello");
    }
}
