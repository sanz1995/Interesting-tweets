package political_tweets.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "political-tweets";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //http
                //.anonymous().disable()
                //.requestMatchers().antMatchers("/admin","/lenguage","/words")
                //.and().authorizeRequests()
                //.antMatchers("/admin","/lenguage","/words").hasAuthority("ROLE_ADMIN")
                //.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        http.csrf().disable();
        //http.httpBasic().disable();
        http.formLogin().and()
        //http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll().and()
                .requestMatchers().antMatchers("/admin/**", "/oauth/**")

                .and().authorizeRequests() .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/oauth/activate/**").permitAll()
                .antMatchers("/oauth/password/**").permitAll()
                .antMatchers("/oauth/auth/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
