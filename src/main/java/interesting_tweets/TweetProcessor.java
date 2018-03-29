package interesting_tweets;

import org.springframework.stereotype.Component;

/**
 * Created by jorge on 29/03/18.
 */
@Component
public class TweetProcessor {

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
