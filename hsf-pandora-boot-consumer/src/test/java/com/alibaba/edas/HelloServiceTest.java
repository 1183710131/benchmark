package com.alibaba.edas;

import com.alibaba.benchmark.HSFConsumerApplication;
import com.alibaba.boot.hsf.annotation.HSFConsumer;

import com.taobao.hsf.remoting.service.GenericService;
import com.taobao.pandora.boot.test.junit4.DelegateTo;
import com.taobao.pandora.boot.test.junit4.PandoraBootRunner;

import junit.framework.TestCase;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@RunWith(PandoraBootRunner.class)
@DelegateTo(SpringJUnit4ClassRunner.class)
// 加载测试需要的类，一定要加入 Spring Boot 的启动类，其次需要加入本类。
@SpringBootTest(classes = {HSFProviderApplication.class, HSFConsumerApplication.class, HelloServiceTest.class})
@Component
public class HelloServiceTest {

    @Test
    public void testCriteria(){

        // 读取文件内容
        String path = "E:\\WeChat Files\\WeChat Files\\wxid_hwymr7hmgjeh21\\FileStorage\\File\\2021-11\\test_action_list.txt";
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                //System.out.println(s);

                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                Request request = new Request.Builder()
                        .url(s)
                        .method("GET", null)
                        .build();
                Response response = client.newCall(request).execute();


            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }



    }


    /**
     * 当使用 @HSFConsumer 时，一定要在 @SpringBootTest 类加载中，加载本类，通过本类来注入对象，否则当做泛化时，会出现类转换异常。
     */
//    @HSFConsumer(generic = true)
//    HelloService helloService222;

//    //普通的调用
//    @Test
//    public void testInvoke() {
//        TestCase.assertEquals("hello world", helloService222.echo("hello world"));
//    }

//    //泛化调用
//    @Test
//    public void testGenericInvoke() {
//        GenericService service = (GenericService) helloService222;
//        Object result = service.$invoke("echo", new String[]{"java.lang.String"}, new Object[]{"hello world"});
//        TestCase.assertEquals("hello world", result);
//    }

//    //返回值 Mock
//    @Test
//    public void testMock() {
//        HelloService mock = Mockito.mock(HelloService.class, AdditionalAnswers.delegatesTo(helloService));
//        Mockito.when(mock.echo("")).thenReturn("beta");
//        TestCase.assertEquals("beta", mock.echo(""));
//    }
}