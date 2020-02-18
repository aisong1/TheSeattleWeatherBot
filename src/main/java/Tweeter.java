import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;


public class Tweeter {

    private Twitter twitter;
    private PrintStream consolePrint;
    private List<Status> statuses;

    public Tweeter(PrintStream console) {
        // Creates instance of Twitter
        twitter = TwitterFactory.getSingleton();
        consolePrint = console;
        statuses = new ArrayList<Status>();
    }

    public void tweet(String message) throws TwitterException, IOException {
        Status status = twitter.updateStatus(message);
        System.out.println("Successfully updated status to [" + status.getText() + "]");
    }

}

