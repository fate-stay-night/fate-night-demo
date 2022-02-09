package cn.fatenight.dubbo3;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2022/1/13
 */
@Data
public class Dubbo3Req implements Serializable {

    private static final long serialVersionUID = 1L;

    private String traceId;

    private String time;
}
