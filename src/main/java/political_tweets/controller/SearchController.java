package political_tweets.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import political_tweets.model.*;

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


    @PutMapping(value = "/language",produces = "application/json",consumes = "application/json")
    public @ResponseBody String setLanguage(@RequestBody String message) {

        JSONObject JSONMessage = new JSONObject(message);

        String l = JSONMessage.getString("lang");

        lr.deleteAll();


        lr.save(new Language(l));


        return "{ \"message\" : \"OK\"}";
    }


    @PutMapping(value = "/languageDefault",produces = "application/json",consumes = "application/json")
    public @ResponseBody String setLanguageDefault(@RequestBody String message) {

        JSONObject JSONMessage = new JSONObject(message);

        String l = JSONMessage.getString("lang");

        lsr.deleteAll();


        lsr.save(new LanguageSelection(l));


        return "{ \"message\" : \"OK\"}";
    }





    @PutMapping(value = "/words",produces = "application/json",consumes = "application/json")
    public @ResponseBody String setWords(@RequestBody String message) {


        JSONObject JSONMessage = new JSONObject(message);

        JSONArray words = JSONMessage.getJSONArray("words");

        kr.deleteAll();

        for (int i =0; i< words.length();i++){
            kr.save(new Keyword((String)words.get(i)));
        }
        return "{ \"message\" : \"OK\"}";
    }


    @GetMapping(value = "/words",produces = "application/json")
    public @ResponseBody List<Keyword> getWords() {

        return Lists.newArrayList(kr.findAll());
    }




    /**
     * Servicio REST que devuelve todas las incidencias de la base de datos
     */
    @GetMapping(value = "/tweets/{pag:.*}",produces = "application/json")
    public @ResponseBody List<Tweet> getTweets(@PathVariable String pag) {


        int pagina = Integer.parseInt(pag);
        return Lists.newArrayList(tr.findAll(new PageRequest(pagina, 20)));
    }





}