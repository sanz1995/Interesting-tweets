package political_tweets.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class OriginalListener {


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    public void receiveMessage(String message) {

        messagingTemplate.convertAndSend("/queue/original/",message);

    }
}
