package facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class newFB{
public static  Configuration createConfiguration() {
    ConfigurationBuilder confBuilder = new ConfigurationBuilder();

    confBuilder.setDebugEnabled(true);
    confBuilder.setOAuthAppId("1638380886471080");
    confBuilder.setOAuthAppSecret("69365e6e59dfe7a05367d7ecf6c1391a");
    confBuilder.setUseSSL(true);
    confBuilder.setJSONStoreEnabled(true);
    Configuration configuration = confBuilder.build();
    return configuration;
}
public static void main(String[] argv) throws FacebookException {
    Configuration configuration =  createConfiguration();
    FacebookFactory facebookFactory = new FacebookFactory(configuration );
    Facebook facebookClient = facebookFactory.getInstance();
    AccessToken accessToken = null;
    try{
    	System.out.println("ciao");
        OAuthSupport oAuthSupport = new OAuthAuthorization(configuration );
        accessToken = oAuthSupport.getOAuthAppAccessToken();

    }catch (FacebookException e) {
        System.err.println("Error while creating access token " + e.getLocalizedMessage());
    }
    facebookClient.setOAuthAccessToken(accessToken);
//results in an error says {An active access token must be used to query information about the current user}
    facebookClient.postStatusMessage("Hello World from Facebook4J."); 
}
}