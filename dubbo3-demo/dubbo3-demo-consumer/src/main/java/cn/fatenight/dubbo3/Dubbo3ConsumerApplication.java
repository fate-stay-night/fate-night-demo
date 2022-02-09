package cn.fatenight.dubbo3;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务消费者
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2022/2/8
 */
@SpringBootApplication
public class Dubbo3ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo3ConsumerApplication.class, args);
    }
}
