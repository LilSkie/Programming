package com.kim.Project.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.Project.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sql;

	public int MemberJoin(MemberDTO member) {

		if (member.getKakaoId() != null) {

			return sql.insert("Member.kakaoJoin", member);

		} else if (member.getNaverId() != null) {

			return sql.insert("Member.naverJoin", member);

		} else {

			return sql.insert("Member.memberJoin", member);
		}

	}

	public String MemberLogin(MemberDTO member) {

		return sql.selectOne("Member.memberLogin", member);
	}

	public List<MemberDTO> memberList() {

		return sql.selectList("Member.memberList");
	}

	public MemberDTO MemberView(String mid) {

		return sql.selectOne("Member.memberView", mid);
	}

	public int MemberDelete(String mid) {

		return sql.delete("Member.memberDelete", mid);
	}

	public int MemberUpdate(MemberDTO member) {

		return sql.insert("Member.memberUpdateProcess", member);
	}

	public String idOverlap(String mid) {

		return sql.selectOne("Member.idOverlap", mid);
	}

	public String kakaoLogin(String kakaoId) {
		return sql.selectOne("Member.kakaoLogin", kakaoId);
	}
	public String naverLogin(String naverId) {
		return sql.selectOne("Member.naverLogin", naverId);
	}

}
