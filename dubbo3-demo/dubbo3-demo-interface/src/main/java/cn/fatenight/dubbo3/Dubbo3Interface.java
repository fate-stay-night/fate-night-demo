package cn.fatenight.dubbo3;

/**
 * 公有接口
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2022/1/13
 */
public interface Dubbo3Interface {

    /**
     * 查询信息
     *
     * @param dubbo3Req 请求
     * @return 结果
     */
    Dubbo3Resp queryInfo(Dubbo3Req dubbo3Req);
}
