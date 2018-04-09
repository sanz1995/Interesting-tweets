package interesting_tweets.controller;

import interesting_tweets.Tweet;
import interesting_tweets.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;

import java.util.List;


@RestController
public class SearchController {

    @Autowired
    private TweetRepository tr;



    /**
     * Servicio REST que devuelve todas las incidencias de la base de datos
     */
    @GetMapping(value = "/tweets",produces = "application/json")
    public @ResponseBody List<Tweet> getTweets() {

        return Lists.newArrayList(tr.findAll());
    }





}