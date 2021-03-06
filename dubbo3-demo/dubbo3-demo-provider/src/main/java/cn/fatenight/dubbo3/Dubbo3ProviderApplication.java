package cn.fatenight.dubbo3;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ζε‘ζδΎ
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2022/2/8
 */
@EnableDubbo
@SpringBootApplication
public class Dubbo3ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dubbo3ProviderApplication.class, args);
    }
}
