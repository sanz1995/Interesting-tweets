package political_tweets.listeners.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;


/**
 * Created by jorge on 29/03/18.
 *
 *
 */


@Component
public class FrenchListener {


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    public void receiveMessage(String message) {

        messagingTemplate.convertAndSend("/queue/language/fr",message);

    }
}
