package com.xp.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_order")
public class OrderEntity implements Serializable {
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
	private Integer demandId;
	/**
	 * 
	 */
	private Integer subId;
	/**
	 * 
	 */
	private Integer orderStatus;

}
