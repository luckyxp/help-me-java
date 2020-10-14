package com.xp.demand.entity;

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
 * @date 2020-09-22 19:39:06
 */
@Data
@TableName("demand")
public class DemandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String imges;
	/**
	 * 
	 */
	private Integer pubId;
	/**
	 * 
	 */
	private Float bounty;
	/**
	 * 
	 */
	private Integer demandStatus;
	/**
	 * 
	 */
	private Date time;

}
