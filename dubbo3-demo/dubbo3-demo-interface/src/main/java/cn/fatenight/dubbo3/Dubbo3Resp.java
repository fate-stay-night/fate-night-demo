package cn.fatenight.dubbo3;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应
 *
 * @author zhangzheng
 * @version 1.0.0
 * @date 2022/1/13
 */
@Data
public class Dubbo3Resp implements Serializable {

    private static final long serialVersionUID = 1L;

    private String traceId;

    private String time;

    private String message;
}
