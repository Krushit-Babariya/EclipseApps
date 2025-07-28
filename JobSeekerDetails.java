package com.krushit.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class JobSeekerDetails implements Serializable {
	private Integer jsId;
	private String  jsName;
	private String jsAddrs;
	private  String  jsQlfy;
	private MultipartFile resume;
	private MultipartFile photo;
}
