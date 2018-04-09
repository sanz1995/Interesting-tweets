package interesting_tweets;

/**
 * Created by jorge on 29/03/18.
 */
import interesting_tweets.listeners.OriginalListener;
import interesting_tweets.listeners.TranslatorListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {


    static final String queue0Name = "tweet";
    static final String queue1Name = "translation";



    /**
     * Configuración cola de original
     */
    @Bean
    Queue originalQueue() {
        return new Queue(queue0Name, false);
    }


    @Bean
    SimpleMessageListenerContainer originalContainer(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter originalListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue0Name);
        container.setMessageListener(originalListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter originalListenerAdapter(OriginalListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }





    /**
     * Configuración cola de traducción
     */
    @Bean
    Queue translationQueue() {
        return new Queue("translation", false);
    }


    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter translationListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue1Name);
        container.setMessageListener(translationListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter translationListenerAdapter(TranslatorListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }




    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

}