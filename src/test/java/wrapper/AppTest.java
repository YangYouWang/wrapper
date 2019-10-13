package wrapper;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wrapper.bean.Student;
import wrapper.core.ControllerWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    private static final Logger log = LoggerFactory.getLogger(AppTest.class);
    private ControllerWrapper baseControllerWrapper ;
    private List<Student> students;

    @Before
    public void init() {
        // 初始化容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        baseControllerWrapper = (ControllerWrapper) applicationContext.getBean("controllerWrapper");
        // 初始化数据
        students = new ArrayList<>();
        for (int i = 0; i <= 10; i++ ) {
            Student student = new Student(i, "张三" + i , i % 2);
            students.add(student);
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        List<Map<String, Object>> wrap = baseControllerWrapper.wrap(students);
        for (Map<String, Object> map : wrap) {
            log.info(map.toString());
        }
    }
}
