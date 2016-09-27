package whatIConsume;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * Servlet implementation class TwitterSignIn
 */
@WebServlet("/TwitterSignIn")
public class TwitterSignIn extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			 Twitter twitter = new TwitterFactory().getInstance();
			 RequestToken requestToken;
			 request.getSession().setAttribute("twitter", twitter);
			 twitter.setOAuthConsumer("qx22ayVjmjO0YQGV5ZcATtJnJ", "UHsytdYFbYAfO8UIRdXQ0iz8cUF60hee5Z2IPgmLQggPvH6Iaa");
			 requestToken = twitter.getOAuthRequestToken("http://127.0.0.1:8080/TwitterConsumption/TwitterServlet");
			 String authURL = requestToken.getAuthenticationURL();
			 request.getSession().setAttribute("requestToken", requestToken);
			 response.sendRedirect(authURL);
			 } catch (TwitterException  twitterException) {
			 twitterException.printStackTrace();
			 }
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterSignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

}
