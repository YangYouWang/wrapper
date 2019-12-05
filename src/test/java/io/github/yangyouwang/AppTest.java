package io.github.yangyouwang;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.yangyouwang.bean.Student;
import io.github.yangyouwang.core.ControllerWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    private static final Logger log = LoggerFactory.getLogger(AppTest.class);

    private List<Student> students;

    @Before
    public void init() {
        // 初始化数据
        students = new ArrayList<>();
        for (int i = 0; i <= 100; i++ ) {
            Student student = new Student(i, "张三" + i , i % 2);
            students.add(student);
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        long startTime = System.currentTimeMillis();
        List<Map<String, Object>> wrap = ControllerWrapper.getInstance().wrap(students);
        for (Map<String, Object> map : wrap) {
            log.info(map.toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }
}
