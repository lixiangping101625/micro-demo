package com.hlkj.webcomponents.controller;

import org.springframework.stereotype.Controller;

/**
 * @author Lixiangping
 * @createTime 2022年10月17日 16:31
 * @decription:
 */
@Controller
public class BaseController {

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    //支付中心调用地址
    //微信支付成功 -》支付中心 -》 吃货平台
    //                        -》 回调通知的URL
    //用户上传头像的位置


}
