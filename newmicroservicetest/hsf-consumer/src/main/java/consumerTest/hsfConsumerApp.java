package consumerTest;

import com.taobao.pandora.boot.PandoraBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class hsfConsumerApp {
    public static void main(String[] args) {
        PandoraBootstrap.run(args);
        SpringApplication.run(hsfConsumerApp.class,args);
        PandoraBootstrap.markStartupAndWait();
    }
}
