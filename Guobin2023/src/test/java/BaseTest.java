import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources")
@ContextConfiguration(locations = { "classpath:springmvc.xml", "classpath:sqlMapConfig.xml", "classpath:applicationContext.xml" })
public class BaseTest {

    @Test
    public void test() {
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
//spring-mvc.xml: 是前端控制器的配置文件，主要是前台展示的各种资源向后台请求的配置，包括各种静态资源的请求，拦截等配置。

