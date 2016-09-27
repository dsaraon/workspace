import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class Timeline {
	
	public static void main(String args[]){
		
		//Login Action
//		Twitter twitter = new Twitter();
//		twitter.setOAuthConsumer("qx22ayVjmjO0YQGV5ZcATtJnJ","UHsytdYFbYAfO8UIRdXQ0iz8cUF60hee5Z2IPgmLQggPvH6Iaa");
//		RequestToken requestToken = twitter.getOAuthRequestToken("http://dsaraon.com");
//		String authUrl = requestToken.getAuthorizationURL(); 
//		session.setAttribute("request-token",  requestToken);
//		redirect(url: authUrl);
//		
//		///  Call back action: Where the twitter will redirect after successfull authentication.
//		RequestToken requestToken = (RequestToken) session.getAttribute("request-token");
//		String verifier = request.getParameter("oauth_verifier")
//		AccessToken accessToken = twitter.getOAuthAccessToken(requestToken,verifier)
		
//		//create client
//		Twitter twitter = new Twitter()
//		twitter.setOAuthConsumer(ConsumerKey, ConsumerSecret)
//		twitter.setOAuthAccessToken(token, secretToken)  //  token, secretToken which you have persisted 
//		// For Example: to search someone on twitter.
//		List users = twitter.searchUsers(name, 1)
		
	
		//fetch timeline
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer("qx22ayVjmjO0YQGV5ZcATtJnJ","UHsytdYFbYAfO8UIRdXQ0iz8cUF60hee5Z2IPgmLQggPvH6Iaa");
		AccessToken accessToken = new AccessToken("32796350-flrcV5C8VBNG10Og39O0VbyBvQnnSFYaS2byF42sm","creQzMK9FP8hdKhg3KEfepJI1t1G02heuXbpHYTVS1gvp");
		twitter.setOAuthAccessToken(accessToken);
		 
		int totalTweets = 20; // no of tweets to be fetched
		Paging paging = new Paging(1, totalTweets);
		try {
			List tweets = twitter.getHomeTimeline(paging);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
