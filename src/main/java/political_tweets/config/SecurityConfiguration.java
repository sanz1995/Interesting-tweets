package political_tweets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextListener;


@Component
//@EnableOAuth2Client
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//@Order(1000)
@Order(-20)
//@Order(-20)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;


    //@Autowired
    //private static ClientDetailsService clientDetailsService;
    //@Autowired
    //@Lazy
    //private AuthenticationManager authenticationManager;

        /*@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("user")
                .roles("USER");
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

/*
    @Bean
    public static TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Autowired
    public static TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public static ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("/admin","/lenguage","/words").hasRole("ADMIN")
            .antMatchers("/index").hasRole("USER")
            .and()
        .formLogin()
            .and()
        .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/index");

    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //http.antMatcher("/**").authorizeRequests()
          //      .antMatchers("/admin**").hasAuthority("ROLE_ADMIN");
        http
                .httpBasic().disable();
        http
                .csrf().disable();
                 http.formLogin().and()
                //.authorizeRequests().antMatchers("/admin/", "/words/","/language/").hasRole("ADMIN")
                 .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").and()
                 .authorizeRequests().antMatchers("/words/**").hasRole("ADMIN").and()
                 .authorizeRequests().antMatchers("/language/**").hasRole("ADMIN")
                .anyRequest().permitAll();

                /*.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll().and()
        //http.requestMatchers()
                //.antMatchers("/login", "/oauth/authorize, /index")
                //.and()
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .anyRequest().hasAuthority("ROLE_ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied.jsp")*/;
    }



    //@RequestMapping("/admin")
    //public Principal user(Principal principal) {
       // return principal;
    //}


   // @Bean
    //public RequestContextListener requestContextListener(){
       // return new RequestContextListener();
    //}

    /*@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //return super.authenticationManagerBean();
        return authenticationManager;
    }*/

    /*@Autowired
    public void CustomWebSecurityConfigurerAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager);
    }*/
}
