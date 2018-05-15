package political_tweets.controller;

import political_tweets.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;

import java.util.List;


@RestController
public class SearchController {

    @Autowired
    private TweetRepository tr;

    @Autowired
    private KeywordRepository kr;

    @Autowired
    private LanguageRepository lr;

    @Autowired
    private LanguageSelectionRepository lsr;



    /**
     * Servicio REST que devuelve todas las incidencias de la base de datos
     */
    @PostMapping(value = "/language",produces = "application/json",consumes = "application/json")
    public @ResponseBody String setLanguage(@RequestBody String message) {

        JSONObject JSONMessage = new JSONObject(message);

        String l = JSONMessage.getString("lang");

        lr.deleteAll();


        lr.save(new Language(l));


        return "{ \"message\" : \"OK\"}";
    }


    @PostMapping(value = "/languageDefault",produces = "application/json",consumes = "application/json")
    public @ResponseBody String setLanguageDefault(@RequestBody String message) {

        JSONObject JSONMessage = new JSONObject(message);

        String l = JSONMessage.getString("lang");

        lsr.deleteAll();


        lsr.save(new LanguageSelection(l));


        return "{ \"message\" : \"OK\"}";
    }






    /**
     * Servicio REST que devuelve todas las incidencias de la base de datos
     */
    @PostMapping(value = "/words",produces = "application/json",consumes = "application/json")
    public @ResponseBody String setWords(@RequestBody String message) {


        JSONObject JSONMessage = new JSONObject(message);

        JSONArray words = JSONMessage.getJSONArray("words");

        kr.deleteAll();

        for (int i =0; i< words.length();i++){
            kr.save(new Keyword((String)words.get(i)));
        }
        return "{ \"message\" : \"OK\"}";
    }

    /**
     * Servicio REST que devuelve todas las incidencias de la base de datos
     */
    @GetMapping(value = "/words",produces = "application/json")
    public @ResponseBody List<Keyword> getWords() {

        return Lists.newArrayList(kr.findAll());
    }




    /**
     * Servicio REST que devuelve todas las incidencias de la base de datos
     */
    @GetMapping(value = "/tweets",produces = "application/json")
    public @ResponseBody List<Tweet> getTweets() {

        return Lists.newArrayList(tr.findAll());
    }





}