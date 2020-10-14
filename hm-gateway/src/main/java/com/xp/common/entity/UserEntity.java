package com.xp.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author climb.xu
 * @email 2271613696@qq.com
 * @date 2020-09-22 18:47:36
 */
@Data
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String password;

	private String fullName;

	private String headImage;

	private String phone;

	private Float moeny;

	private String authorities;

}
