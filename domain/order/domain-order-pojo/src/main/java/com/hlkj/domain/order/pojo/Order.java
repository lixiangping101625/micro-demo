package com.hlkj.domain.order.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 20:07
 * @decription:
 */
@Table(name = "t_order")
@Data
public class Order implements Serializable {

    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "addr")
    private String addr;

    @Column(name = "items_json")
    private String itemsJson;

    @Column(name = "count")
    private Integer count;

    @Column(name = "user_id")
    private Long userId;

}
