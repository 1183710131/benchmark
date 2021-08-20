package com.alibaba.edas;

import com.alibaba.edas.generator.Userinfo;
import com.alibaba.edas.generator.UserinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.sql.Date;

@RestController
public class SimpleController {

    @Resource
    private HelloService helloService;
//    @Resource
//    private calculate cal;

    @Resource
    private UserinfoDao u;

    @RequestMapping(value = "/hsf-echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return helloService.echo(str);
    }

//    @RequestMapping(value = "/hsf-cal/{str}",method = RequestMethod.GET)
//    public int cal(@PathVariable String str){
//        return cal.cal(str);
//    }

    @RequestMapping(value = "/hsf-test/insert")
    public void test() {
        Userinfo user = new Userinfo();
        user.setUserName("test");
        Date date =new Date(System.currentTimeMillis());
        user.setCreateTime(date);
        System.out.println(user);
        u.insert(user);

        System.out.println(u.selectByPrimaryKey((long)1));

    }
}
