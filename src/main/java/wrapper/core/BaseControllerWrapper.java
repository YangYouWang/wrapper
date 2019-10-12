package wrapper.core;

import org.springframework.util.ReflectionUtils;
import wrapper.annotion.Warpper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper包装类
 * @author yangyouwang
 */
public class BaseControllerWrapper {


    private String SERIAL_VERSION_UID = "serialVersionUID";

    public List<Map<String,Object>> wrap(List<?> objs)  {
        List<Map<String,Object>> result = new ArrayList<>();
        for (Object obj : objs) {
            result.add(this.wrap(obj));
        }
        return result;
    }


    public Map<String,Object> wrap(Object obj)  {
        try {
            Map<String,Object> result = new HashMap<>(16);
            PropertyDescriptor propertyDescriptor;
            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!SERIAL_VERSION_UID.equals(field.getName())) {
                    propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
                    Method method = propertyDescriptor.getReadMethod();
                    Object fieldValue = ReflectionUtils.invokeMethod(method, obj);
                    if(field.isAnnotationPresent(Warpper.class)){
                        Warpper warpper = field.getAnnotation(Warpper.class);
                        if (!"".equals(warpper.dictType())) {
                            // 这块具体看自己数据库中的枚举表结构
                            /*SysDictData sysDictData = new SysDictData() ;
                            sysDictData.setDictType(warpper.dictType());
                            List<SysDictData> sysDictDatas = dictDataService.selectDictDataList(sysDictData);
                            if (sysDictDatas.size() > 0) {
                                for(SysDictData dict : sysDictDatas) {
                                    if (fieldValue.toString().equals(dict.getDictValue())) {
                                        result.put(field.getName(),dict.getDictLabel()) ;
                                    }
                                }
                                break;
                            }*/
                        }
                        if (!"".equals(warpper.dictData())) {
                            for (String dict: warpper.dictData()) {
                                String[] split = dict.split(":") ;
                                if (split.length > 0 && fieldValue.toString().equals(split[0])) {
                                    result.put(field.getName(),split[1]);
                                }
                            }
                            break;
                        }
                    }
                    result.put(field.getName(),fieldValue);
                }
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
