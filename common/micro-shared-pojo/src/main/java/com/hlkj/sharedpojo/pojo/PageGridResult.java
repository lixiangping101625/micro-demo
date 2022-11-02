package com.hlkj.sharedpojo.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月17日 15:46
 * @decription: 分页结果
 */
@Data
public class PageGridResult {

    private int page;
    private int total;
    private long records;
    private List<?> rows;

}
