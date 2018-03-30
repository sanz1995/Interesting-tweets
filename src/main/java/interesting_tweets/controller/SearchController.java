package interesting_tweets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;


@RestController
public class SearchController {

    //@Autowired
    //TwitterLookupService twitter;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    /**
    @RequestMapping("/search")
    public SearchResults search(@RequestParam("q") String q) {
        return twitter.search(q);
    }
     */
    @MessageMapping("/search")
    public void search()
    {
        //twitter.search(query);
    }


    /**
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UncategorizedApiException.class)
    public SearchResults handleUncategorizedApiException() {
        return twitter.emptyAnswer();
    }

    */
}