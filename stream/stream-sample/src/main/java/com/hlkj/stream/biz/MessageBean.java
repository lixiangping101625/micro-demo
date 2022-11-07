package com.hlkj.stream.biz;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lixiangping
 * @createTime 2022年11月04日 13:11
 * @decription:
 */
@Data
public class MessageBean implements Serializable {

    //生产者发送消息生成的消息体
    private String payload;

}
