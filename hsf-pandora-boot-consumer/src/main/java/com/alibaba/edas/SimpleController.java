package com.alibaba.edas;


import com.alibaba.edas.generator.*;
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
    private UserService userService;

    @Resource
    private PointService pointService;



    @RequestMapping(value = "/hsf-echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return helloService.echo(str);
    }

//    @RequestMapping(value = "/hsf-cal/{str}",method = RequestMethod.GET)
//    public int cal(@PathVariable String str){
//        return cal.cal(str);
//    }

//    @RequestMapping(value = "/hsf-test/insert")
//    public void test() {
//        Userinfo user = new Userinfo();
//        user.setUserName("test");
//        Date date =new Date(System.currentTimeMillis());
//        user.setCreateTime(date);
//        System.out.println(user);
//        u.insert(user);
//        System.out.println(u.selectByPrimaryKey((long)1));
//
//    }

    @RequestMapping(value = "/hsf-test/point")
    public void checkpoint(){
        Pointaccount pointaccount = new Pointaccount();
        pointaccount.setPointNumber((long)0);
        Date date =new Date(System.currentTimeMillis());
        pointaccount.setGmtCreate(date);
        pointaccount.setGmtModify(date);
        pointaccount.setUserId((long)1);
        pointaccount.setPointActivity((long)1);
     //   pointService.insertPointAccount(pointaccount);

        Pointdetail pointdetail = new Pointdetail();
        pointdetail.setChangeReason("增加积分10");
        pointdetail.setGmtCreate(date);
        pointdetail.setGmtModify(date);
        pointdetail.setPointChange((long)10);
        pointdetail.setPointId((long)1);
        pointdetail.setUserId((long)1);
        pointService.insertPointDetail(pointdetail);

        pointdetail.setChangeReason("减少积分6");
        pointdetail.setPointChange((long)-6);
        pointService.insertPointDetail(pointdetail);

    }


}
