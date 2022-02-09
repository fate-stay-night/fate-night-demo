package cn.fatenight.dubbo3.service;

import cn.fatenight.dubbo3.Dubbo3Interface;
import cn.fatenight.dubbo3.Dubbo3Req;
import cn.fatenight.dubbo3.Dubbo3Resp;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 接口实现
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2022/1/13
 */
@DubboService(interfaceClass = Dubbo3Interface.class, retries = 0)
public class Dubbo3InterfaceImpl implements Dubbo3Interface {

    @Override
    public Dubbo3Resp queryInfo(Dubbo3Req dubbo3Req) {
        Dubbo3Resp resp = new Dubbo3Resp();
        resp.setTime(dubbo3Req.getTime());
        resp.setTraceId(dubbo3Req.getTraceId());
        resp.setMessage("调用成功");
        return resp;
    }
}
