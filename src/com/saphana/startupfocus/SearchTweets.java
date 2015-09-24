package com.saphana.startupfocus;

import twitter4j.*;
import com.saphana.startupfocus.config.Configurations;
import com.saphana.startupfocus.dao.TweetDAO;
import com.saphana.startupfocus.util.TwitterConnection;
import com.saphana.startupfocus.entity.Tweet;
import java.util.List;
import java.util.ArrayList;

public class SearchTweets {
	/**
	 * Invoke Twitter Search API to get the tweets and return as a list of Tweet
	 * 
	 * */
    public List<Tweet> search(String searchTerm) {
    	List<Tweet> tList = new ArrayList<Tweet>();
    	
    	Twitter twitter = TwitterConnection.getInstance();
        try {
            Query query = new Query(searchTerm);
            QueryResult result;
            
            int index = 0;
            do {
            	index++;
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                
                for (Status tweet : tweets) {
                	Tweet t = new Tweet();
                	t.setUserName(tweet.getUser().getName());
                	t.setCreatedAt(tweet.getCreatedAt());
                	t.setText(tweet.getText());
                	t.setHashTags(tweet.getHashtagEntities());
                	tList.add(t);
                }
            } while ((query = result.nextQuery()) != null && index < Configurations.SEARCH_RESULT_COUNT );
            
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return tList;
    }

    public static void main(String[] args) {
    	// Search tweets
    	SearchTweets searchTweets = new SearchTweets();
    	List<Tweet> tList = searchTweets.search(Configurations.SEARCH_TERM);
    	
    	// Insert tweets into HANA DB
    	TweetDAO tDAO = TweetDAO.getInstance();
    	tDAO.insert(tList);
    	tDAO.commitAndClose();
    	
    }	
}
