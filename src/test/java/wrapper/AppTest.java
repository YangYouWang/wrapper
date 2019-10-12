package wrapper;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wrapper.bean.Student;
import wrapper.core.BaseControllerWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    BaseControllerWrapper baseControllerWrapper ;
    List<Student> students;
    Student student;

    @Before
    public void init() {
        // 初始化容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        baseControllerWrapper = (BaseControllerWrapper) applicationContext.getBean("wrapper");
        // 初始化数据
        students = new ArrayList<>();
        for (int i = 0; i <= 10; i++ ) {
            student = new Student(i, "张三" + i , i % 2);
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
            System.out.println(map.toString());
        }
    }
}
