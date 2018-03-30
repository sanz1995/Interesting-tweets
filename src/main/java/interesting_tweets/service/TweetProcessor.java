package interesting_tweets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

/**
 * Created by jorge on 29/03/18.
 *
 *
 */


@Component
public class TweetProcessor {


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;



    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");

        messagingTemplate.convertAndSend("/queue/search/",message);

    }
}
