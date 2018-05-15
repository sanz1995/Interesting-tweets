package political_tweets;

/**
 * Created by jorge on 29/03/18.
 */
import political_tweets.listeners.OriginalListener;
import political_tweets.listeners.TranslatorListener;
import political_tweets.listeners.language.*;
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
    static final String queue2Name = "language";



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



    /**
     * Configuración cola en español
     */
    @Bean
    Queue spanishQueue() {
        return new Queue(queue2Name+"/es", false);
    }


    @Bean
    SimpleMessageListenerContainer spanishContainer(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter spanishListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue2Name+"/es");
        container.setMessageListener(spanishListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter spanishListenerAdapter(SpanishListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    /**
     * Configuración cola en aleman
     */
    @Bean
    Queue germanQueue() {
        return new Queue(queue2Name+"/de", false);
    }


    @Bean
    SimpleMessageListenerContainer germanContainer(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter germanListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue2Name+"/de");
        container.setMessageListener(germanListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter germanListenerAdapter(GermanListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    /**
     * Configuración cola en  catalan
     */
    @Bean
    Queue catalanQueue() {
        return new Queue(queue2Name+"/ca", false);
    }


    @Bean
    SimpleMessageListenerContainer catalanContainer(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter catalanListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue2Name+"/ca");
        container.setMessageListener(catalanListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter catalanListenerAdapter(CatalanListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }



    /**
     * Configuración cola en frances
     */
    @Bean
    Queue frenchQueue() {
        return new Queue(queue2Name+"/fr", false);
    }


    @Bean
    SimpleMessageListenerContainer frenchContainer(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter frenchListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue2Name+"/fr");
        container.setMessageListener(frenchListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter frenchListenerAdapter(FrenchListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    /**
     * Configuración cola en aleman
     */
    @Bean
    Queue englishQueue() {
        return new Queue(queue2Name+"/en", false);
    }


    @Bean
    SimpleMessageListenerContainer englishContainer(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter englishListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue2Name+"/en");
        container.setMessageListener(englishListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter englishListenerAdapter(EnglishListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


    /**
     * Configuración cola por defecto
     */
    @Bean
    Queue defaultQueue() {
        return new Queue(queue2Name+"/default", false);
    }


    @Bean
    SimpleMessageListenerContainer defaultContainer(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter defaultListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue2Name+"/default");
        container.setMessageListener(defaultListenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter defaultListenerAdapter(DefaultListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }







    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

}