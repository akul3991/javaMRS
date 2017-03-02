package facebook;


import java.io.IOException;
import java.util.Map;

import TrovaTipo.caricaFile;
import cleaner.Parsing;
import cleaner.TextCleaner;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Like;
import facebook4j.Music;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import stemmer.SnowballStemmer;
import stemmer.italianStemmer;

public class facebookTry{


public static void main(String[] args) throws FacebookException, IOException {

    
    Facebook facebook = new FacebookFactory().getInstance();
    facebook.setOAuthAppId("", "");
    String accessTokenString = "EAACEdEose0cBACJKExce1dSyutNVLCcDQn1Bj7FogjNtZCK1RNq6JrPdfZABmq8IvQltyxu0GODebbpMjca2pELjRkQRpNZBYAULkMbUZBTgW9FGgZCjkWvtFY9vTeOwMg6dEVkj3R9zeuXPQ9WLtSfS57noxg1Qbu7mGWjsOvrQ8uvwZC9cYvZBgZAaCpnBbZBsZD";
    AccessToken at = new AccessToken(accessTokenString);
    facebook.setOAuthAccessToken(at);
    Parsing parse=new Parsing();
    Map<String, String> rule= parse.ruleParser("src/cleaner/normRules.txt", "=");
    TextCleaner clean=new TextCleaner(rule);
    String pathToSWN = "src/facebook/sentix.txt";
	SentiWordNetDemoCode sentiwordnet = new SentiWordNetDemoCode(pathToSWN);
	SnowballStemmer stemmer = (SnowballStemmer) new italianStemmer();
	caricaFile file=new caricaFile();
	Map<String, String> tipoParola=file.getTipi();
	
    
    ResponseList<Post> feeds = facebook.getPosts();
    
    for (int i = 0; i < feeds.size(); i++) {
    	
            Post post = feeds.get(i);
            
            String message = post.getMessage();
            System.out.println(message);
            
            if(message!=null){
            	message=clean.cleanUp(message);
            	System.out.println(message);
            	String[] mess = message.split(" ");
            	for(String word:mess){
            		stemmer.setCurrent(word);
                    stemmer.stem();
                    String stemmed = stemmer.getCurrent();
                    String tipo=tipoParola.get(stemmed);
            		System.out.println(stemmed+" "+tipo+" "+sentiwordnet.extract(stemmed));
            	}
            }
        }           
    }
}