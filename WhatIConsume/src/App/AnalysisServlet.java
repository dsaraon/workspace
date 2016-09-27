package App;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.UserMentionEntity;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Servlet implementation class TwitterServlet
 */
@WebServlet("/AnalysisServlet")
public class AnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static DB db;
	static List<Status> statuses;
	static DBCollection items;
	static MongoClient mongo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalysisServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		for (Status status : statuses) {
			response.getWriter().println("@" + status.getUser().getScreenName() + " - " + status.getText());
        }
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
	
			ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
              .setOAuthConsumerKey("qx22ayVjmjO0YQGV5ZcATtJnJ")
              .setOAuthConsumerSecret("UHsytdYFbYAfO8UIRdXQ0iz8cUF60hee5Z2IPgmLQggPvH6Iaa")
              .setOAuthAccessToken("32796350-flrcV5C8VBNG10Og39O0VbyBvQnnSFYaS2byF42sm")
              .setOAuthAccessTokenSecret("creQzMK9FP8hdKhg3KEfepJI1t1G02heuXbpHYTVS1gvp");
            
            cb.setJSONStoreEnabled(true); 
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter2 = tf.getInstance();
            
            statuses = twitter2.getHomeTimeline();
            
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
		}
		catch(Exception e){
			 e.printStackTrace();
		}
		
		connectdb();
		callDB();
	}
	
	//CONNECTING TO DATABASE
			public static void connectdb(){
		        try {
		 
		            initMongoDB();
		            items = db.getCollection("tweets"); 
		 
		            //make the tweet_ID unique in the database
		           // BasicDBObject index = new BasicDBObject("tweet_ID", 1);
		           // items.createIndex(index);
		            
		            storeTweets();
		 
		        } catch (MongoException ex) {
		            System.out.println("MongoException :" + ex.getMessage());
		        }
		 
		    }
		 
			public static void initMongoDB() throws MongoException {
		        try {
		            System.out.println("Connecting to Mongo DB..");
		           //mongo = new Mongo("127.0.0.1");
		            
		             mongo = new MongoClient( "localhost" , 27017 );
		            
		            db = mongo.getDB("tweetDB");
		        } catch (Exception ex) {
		            System.out.println("MongoDB Connection Error :" + ex.getMessage());
		        }
		    }
			
			//Put tweets and status objects into database
			public static void storeTweets(){
//				for (Status status : statuses) {
//					@SuppressWarnings("deprecation")
//					String tweet = DataObjectFactory.getRawJSON(status);
//					DBObject tweetDBOject = (DBObject)JSON.parse(tweet);
//					System.out.println(tweet);
//					items.insertOne(tweetDBOject);
//	            }
				
				for (Status status : statuses) {
					 BasicDBObject basicObj = new BasicDBObject();
					 basicObj.put("user_ID", status.getUser().getId());
		             basicObj.put("user_name", status.getUser().getScreenName());
		             basicObj.put("retweet_count", status.getRetweetCount());
		             basicObj.put("tweet_followers_count", status.getUser().getFollowersCount());
		             basicObj.put("source",status.getSource());
		             basicObj.put("coordinates",status.getGeoLocation());
		
		             UserMentionEntity[] mentioned = status.getUserMentionEntities();
		             basicObj.put("tweet_mentioned_count", mentioned.length);
		             basicObj.put("tweet_ID", status.getId());
		             basicObj.put("tweet_text", status.getText());
		           
		             
		             try {
		                 items.insert(basicObj);
		             } catch (Exception e) {
		                 System.out.println("MongoDB Connection Error : " + e.getMessage());
		             }
				}
			}
			
			public static void callDB(){
				DBCursor cursor = items.find();
//		         int i=1;
//		         while (cursor.hasNext()) { 
//		            System.out.println("Inserted Document: "+i); 
//		            System.out.println(cursor.next()); 
//		            i++;
//		         }
				System.out.println(items.count());
				
				DBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$user_name"));
				//DBObject max =  new BasicDBObject(( ));
				
				AggregationOutput output = items.aggregate(group);
				System.out.println(output);
			}

}
