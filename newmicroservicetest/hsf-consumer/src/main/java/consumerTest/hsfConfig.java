package consumerTest;

import com.taobao.hsf.app.spring.util.annotation.HSFConsumer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class hsfConfig {

    @HSFConsumer(clientTimeout = 3000,serviceVersion = "1.0.0")
    private helloService helloService;
}
