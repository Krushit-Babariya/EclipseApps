package com.krushit.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name="BOOT_JS_INFO1")
@Data
public class JobSeekerInfo implements Serializable {
	@Id
	@GeneratedValue
	private Integer jsId;
	@Column(length = 20)
	private String  jsName;
	@Column(length = 20)
	private String jsAddrs;
	@Column(length = 20)
	private  String  jsQlfy;
	@Column(length = 150)
	private String resumePath;
	@Column(length = 150)
	private  String photoPath;
	

}
