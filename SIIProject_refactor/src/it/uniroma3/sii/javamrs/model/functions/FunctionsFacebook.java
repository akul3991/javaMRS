package it.uniroma3.sii.javamrs.model.functions;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

public class FunctionsFacebook {
	
	private static final String APP_ID = "1840714026145501";
	private static final String APP_SECRET = "b3b558d93bd692517aa6fc149c6e8dca";
	
	
	public static Facebook setupFacebook(String accessToken) {
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(APP_ID, APP_SECRET);
		facebook.setOAuthPermissions("public_profile,user_posts");
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
		
		return facebook;
	}

}
