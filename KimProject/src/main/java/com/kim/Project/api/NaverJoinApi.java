package com.kim.Project.api;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class NaverJoinApi {

	private final static String CLIENT_ID = "COQ0sVyDnaNbXvoqHNxe";
	private final static String CLIENT_SECRET = "GlTo69z651";

	private final static String REDIRECT_URI = "http://localhost:8090/Project/naverjoinok";
	private final static String SESSION_STATE = "oauth_state";

	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";


	public static String getAuthorizationUrl(HttpSession session) {


		String state = generateRandomString();

		setSession(session, state);


		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URI).state(state) 
				.build(NaverApi.instance());

		return oauthService.getAuthorizationUrl();
	}

	public static OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {

		String sessionState = getSession(session);
		if (StringUtils.pathEquals(sessionState, state)) {

			OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID).apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URI).state(state).build(NaverApi.instance());

			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

	private static String generateRandomString() {
		return UUID.randomUUID().toString();
	}

	private static void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}

	private static String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}

	public static String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {

		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID)
														  .apiSecret(CLIENT_SECRET)
														  .callback(REDIRECT_URI)
														  .build(NaverApi.instance());

		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}

}
