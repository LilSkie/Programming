package com.kim.Project.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kim.Project.dao.MemberDAO;
import com.kim.Project.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	@Autowired
	private HttpSession session;

	private ModelAndView mav;

	public ModelAndView MemberJoin(MemberDTO member) throws IllegalStateException, IOException {

		mav = new ModelAndView();
		MultipartFile mfile = member.getMfile();
		String mfilename = mfile.getOriginalFilename();
		String savePath = "D:\\source\\Spring\\KimProject\\src\\main\\webapp\\resources\\uproadfile\\"+mfilename;

		if(!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}
		member.setMfilename(mfilename);
		int JoinResult = memberDAO.MemberJoin(member);
		
		if(JoinResult > 0)
			mav.setViewName("MemberLogin");
		else
			mav.setViewName("Main");
		

		return mav;
	}

	public ModelAndView MemberLogin(MemberDTO member) {

		mav = new ModelAndView();

		String LoginId = memberDAO.MemberLogin(member);
		if (LoginId != null) {

			session.setAttribute("LoginId", LoginId);
			mav.setViewName("Main");

		} else {

			mav.setViewName("MemberLoginFail");

		}

		return mav;
	}

	public ModelAndView MemberList() {
		mav = new ModelAndView();
		List<MemberDTO> MemberList = new ArrayList<MemberDTO>();
		MemberList = memberDAO.memberList();
		mav.addObject("memberList", MemberList);
		mav.setViewName("MemberList");
		return mav;
	}

	public ModelAndView MemberView(String mid) {
		mav = new ModelAndView();

		MemberDTO MemView = memberDAO.MemberView(mid);
		mav.addObject("memView", MemView);
		mav.setViewName("MemberView");
		return mav;
	}

	public ModelAndView MemberDelete(String mid) {
		mav = new ModelAndView();

		int DeleteResult = memberDAO.MemberDelete(mid);

		if (DeleteResult > 0) {

			mav.setViewName("redirect:/memberlist");

		} else {

			mav.setViewName("Main");

		}

		return mav;
	}

	public ModelAndView MemberUpdateProcess(MemberDTO member) {

		mav = new ModelAndView();

		int UpdateProcess = memberDAO.MemberUpdate(member);

		if (UpdateProcess > 0) {

			mav.setViewName("redirect:/memberlist");

		} else {

			mav.setViewName("Main");

		}

		return mav;
	}

	public ModelAndView MemberUpdate(String mid) {

		mav = new ModelAndView();

		MemberDTO MemberUpdate = memberDAO.MemberView(mid);

		if (MemberUpdate != null) {

			mav.addObject("memberUpdate", MemberUpdate);
			mav.setViewName("MemberUpdate");

		} else {

			mav.setViewName("Main");

		}
		return mav;
	}

	public String idOverlap(String mid) {
		String checkResult = memberDAO.idOverlap(mid);
		String resultMsg = null;
		if (checkResult == null) {

			resultMsg = "OK";

		} else {

			resultMsg = "NO";

		}

		return resultMsg;
	}

	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();
		String kakaoId = profile.get("id").asText();
		String loginId = memberDAO.kakaoLogin(kakaoId);
		
		session.setAttribute("loginId", loginId);
		mav.setViewName("MemberMain");
		
		return mav;
	}
	
	public ModelAndView naverLogin(String profile) throws org.json.simple.parser.ParseException {
		mav = new ModelAndView();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String)userInfo.get("id");
		String loginId = memberDAO.naverLogin(naverId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("MemberMain");
		return mav;
	}
}
