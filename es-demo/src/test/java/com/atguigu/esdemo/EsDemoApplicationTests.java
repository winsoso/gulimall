package com.atguigu.esdemo;

import io.searchbox.client.JestClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDemoApplicationTests {q587E4A

    @Autowired
    JestClient jestClient;
    @Test
    public void contextLoads() {

        System.out.println(jestClient);
    }
    @Test
    public  void index(){
        User user = new User("张三","zhangsan@qq.com",28);
    }

}
@NoArgsConstructor
@AllArgsConstructor
@Data
class User{
    private String username;
    private String email;
    private Integer age;
}