package interesting_tweets.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


 import java.net.URI;


/**
 * Created by jorge on 29/03/18.
 *
 *
 */


@Component
public class TweetProcessorTranslator {


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Value( "${yandex.key}")
    private String key;



    public void receiveMessage(String message) {


        JSONObject tweet = new JSONObject(message);

        String texto = tweet.get("unmodifiedText").toString();


        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate";



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("key", key);
        map.add("text", texto);
        map.add("lang", "es");


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );


        JSONObject translation = new JSONObject(response.getBody());

        tweet.put("unmodifiedText",translation.getJSONArray("text").get(0));



        messagingTemplate.convertAndSend("/queue/search/",tweet.toString());

    }
}
