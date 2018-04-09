package interesting_tweets;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tweet {


    @Id
    private String id;

    private String text;

    private String user;


    public Tweet() {
    }

    public Tweet(String id, String text, String user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }


    public String getId() {
        return id;
    }


    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }



}
