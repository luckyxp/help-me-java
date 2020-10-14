package com.xp.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:35
 */
@Data
@TableName("order_result")
public class OrderResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Date time;
	/**
	 * 
	 */
	private String note;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer orderId;
	/**
	 * 
	 */
	private String images;

}
