package cn.fatenight.dubbo3.controller;

import cn.fatenight.dubbo3.Dubbo3Interface;
import cn.fatenight.dubbo3.Dubbo3Req;
import cn.fatenight.dubbo3.Dubbo3Resp;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.local.LocalAddress;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.http.client.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author zhangzheng
 * @version 1.0.0
 * @date 2022/2/8
 */
@RestController
@RequestMapping("consumer")
public class Dubbo3Controller {

    @DubboReference(interfaceClass = Dubbo3Interface.class, check = false)
    private Dubbo3Interface dubbo3Interface;

    @GetMapping("query")
    public Dubbo3Resp query() {
        Dubbo3Req req = new Dubbo3Req();
        req.setTime(LocalDateTime.now().toString());
        req.setTraceId(UUID.randomUUID().toString());
        Dubbo3Resp resp = dubbo3Interface.queryInfo(req);
        return resp;
    }
}
