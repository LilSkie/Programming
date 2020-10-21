package com.kim.Project.controller;


import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.kim.Project.api.KakaoJoinApi;
import com.kim.Project.api.KakaoLoginApi;
import com.kim.Project.api.NaverJoinApi;
import com.kim.Project.api.NaverLoginApi;
import com.kim.Project.dto.MemberDTO;
import com.kim.Project.service.MemberService;

@Controller
public class MemberCotroller {

	@Autowired
	private MemberService memberService;
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;

	
	
	@RequestMapping(value="/")
	public String home() {
		
		
		return "home";
	}
	
	//회원가입,파일업로드
	@RequestMapping(value="/memberjoinForm")
	public String MemberJoingForm() {
		
		
		return "MemberJoin";
	}
	@RequestMapping(value="/memberjoin")
	public ModelAndView MemberJoin(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		
		mav = memberService.MemberJoin(member);
		
		
		return mav;	
	}
	
	
	//로그인
	@RequestMapping(value="/memberloginForm")
	public String MemberLogin() {
		
		return "MemberLogin";
		
	}
	@RequestMapping(value="/memberlogin")
	public ModelAndView MemberLogin(@ModelAttribute MemberDTO member) {
		
		mav = new ModelAndView();
		
		mav = memberService.MemberLogin(member);
			
		return mav;
		
	}
	//리스트
	@RequestMapping(value="/memberlist")
	public ModelAndView MemberList() {
		
		mav = new ModelAndView();
		
		mav = memberService.MemberList();
		
		return mav;
	}
	//상세조회
	@RequestMapping(value="/memberview")
	public ModelAndView MemberView(@RequestParam ("mid") String mid) {
		
		mav = new ModelAndView();
		
		mav = memberService.MemberView(mid);
		
		return mav;
		
		
	}
	//업데이트
	@RequestMapping(value="/memberupdateprocess")
	public ModelAndView MemberUpdateProcess(@ModelAttribute MemberDTO member) {
		mav = new ModelAndView();
		
		mav = memberService.MemberUpdateProcess(member);
		
		return mav;
		
	}
	
	@RequestMapping(value="/memberupdate")
	public ModelAndView MemberUpdate(@RequestParam ("mid") String mid) {
		
		mav = new ModelAndView();
		
		mav = memberService.MemberUpdate(mid);
				
		return mav;	
	}
	
	
	
	
	//삭제
	@RequestMapping(value="/memberdelete")
	public ModelAndView MemberDelete(@RequestParam("mid") String mid) {
	
		mav = new ModelAndView();
	
		mav = memberService.MemberDelete(mid);
		
		return mav;
	
	}
	
	//로그아웃
	@RequestMapping(value="/memberlogout")
	public String MemberLogout() {
		
		session.removeAttribute("LoginId");
		
		return "redirect:/memberloginForm";
	}
	//로그인 중복확인
	@RequestMapping(value="/idoverlap")
	public  @ResponseBody String idOverlap(@RequestParam("mid") String mid) {
		
		System.out.println("전달받은 값"+mid);
		String resultMsg = memberService.idOverlap(mid);

		return resultMsg;
		
	}
	//카카오 서버 로그인
		@RequestMapping(value="/kakaojoin")
		public ModelAndView kakaoJoin(HttpSession session) {
			
			String kakaoUrl = KakaoJoinApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("kakaoUrl",kakaoUrl);
			mav.setViewName("KakaoLogin");
			
			return mav;
			
		}
		//카카오 서버 인증 통과 후 처리
		@RequestMapping(value="/kakaojoinok")
		public ModelAndView kakaoJoinOK(@RequestParam("code") String code, HttpSession session) {
			
			JsonNode token = KakaoJoinApi.getAccessToken(code);
			JsonNode profile = KakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
			System.out.println("profile"+profile);		
			//profile에 담긴 id 값을 가지고 MemberJoin.jsp로 이동	
			String kakaoId = profile.get("id").asText();
			mav = new ModelAndView();
			mav.addObject("kakaoId",kakaoId);
			mav.setViewName("MemberJoin");
			return mav;
		}
		//카카오 로그인
		@RequestMapping(value="/kakaologin")
		public ModelAndView kakaologin(HttpSession session) {
			
			String kakaoUrl = KakaoLoginApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("kakaoUrl",kakaoUrl);
			mav.setViewName("KakaoLogin");
			
			return mav;
			
		}
		@RequestMapping(value="/kakaologinok")
		public ModelAndView kakaoLoginOK(@RequestParam("code") String code) {
			JsonNode token = KakaoLoginApi.getAccessToken(code);
			JsonNode profile = KakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
			
			mav = memberService.kakaoLogin(profile);
			return mav;
			
		}
		//네이버로그인
		@RequestMapping(value="/naverjoin")
		public ModelAndView naverJoin(HttpSession session) {
			String naverUrl = NaverJoinApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("naverUrl", naverUrl);
			mav.setViewName("NaverLogin");
			return mav;
		}
		
		@RequestMapping(value="/naverjoinok")
		public ModelAndView naverJoinOK(@RequestParam("code") String code,
				@RequestParam("state") String state, HttpSession session) throws IOException, ParseException, org.json.simple.parser.ParseException {
			mav = new ModelAndView();
			OAuth2AccessToken oauthToken = NaverJoinApi.getAccessToken(session, code, state);
			String profile = NaverJoinApi.getUserProfile(oauthToken);
			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(profile);
			
			JSONObject naverUser = (JSONObject)obj;
			JSONObject userInfo = (JSONObject)naverUser.get("response");
			String naverId = (String) userInfo.get("id");
			
			mav.addObject("naverId", naverId);
			mav.setViewName("MemberJoin");
			
			return mav;
		}
		
		@RequestMapping(value="/naverlogin")
		public ModelAndView naverLogin(HttpSession session) {
			String naverUrl = NaverJoinApi.getAuthorizationUrl(session);
			mav = new ModelAndView();
			mav.addObject("naverUrl", naverUrl);
			mav.setViewName("NaverLogin");
			return mav;
		}
		
		@RequestMapping(value="/naverloginok")
		public ModelAndView naverLoginOK(@RequestParam("code") String code,
				@RequestParam("state") String state, HttpSession session) throws IOException, ParseException, org.json.simple.parser.ParseException {
			OAuth2AccessToken oauthToken = NaverLoginApi.getAccessToken(session, code, state);
			String profile = NaverJoinApi.getUserProfile(oauthToken);
			mav = memberService.naverLogin(profile);
			return mav;
			
		}
		
		


}
