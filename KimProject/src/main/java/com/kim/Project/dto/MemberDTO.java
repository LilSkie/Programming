package com.kim.Project.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	
	private String mid;
	private String mpassword;
	private String mname;
	private String mbirth;
	private String maddress;
	private String memail;
	private String mphone;
	private int mpostcode; //우편번호
	private String mdetailaddress;//상세주소
	private String mextraaddress;//참고항목
	
	private String kakaoId;
	private String naverId;
	
	private MultipartFile mfile;
	private String mfilename;
	

}
