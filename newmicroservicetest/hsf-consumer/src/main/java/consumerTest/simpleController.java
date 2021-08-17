package consumerTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class simpleController {
    @Autowired
    private helloService helloService;

    @RequestMapping(value = "/hsf-hello/{str}",method = RequestMethod.GET)
    public String hello(@PathVariable String str){
        return helloService.hello(str);
    }
}
