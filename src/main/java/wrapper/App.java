package wrapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wrapper.core.BaseControllerWrapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BaseControllerWrapper bean = (BaseControllerWrapper)applicationContext.getBean("wrapper");
        bean.hello();
    }
}
