package com.xp.common.vo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 19:39:06
 */
@Data
public class DemandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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
