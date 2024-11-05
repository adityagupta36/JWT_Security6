package aditya.SpringSecurity.JWT_Security6.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity    //lets you define custom security rules, such as login configurations, authorization rules, and CSRF settings
public class Config {

    @Autowired
    UserDetailsService userDetailService;

    @Autowired
    JwtFilter jwtFilter;


    /*    // SecurityFilterChain - defines security rules using HttpSecurity to set up basic authentication
    @Bean   //Explicit Security Filter Chain
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //activated by default when using EnableWebSecurity
        httpSecurity.csrf(customiser -> customiser.disable());

        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated()); //all requests to the server require authentication.
//        httpSecurity.formLogin(Customizer.withDefaults());  //Provide a Default Login Form:
        httpSecurity.httpBasic(Customizer.withDefaults());

        //Every time new session id, each request is independent.
        //JWT Authentication
        httpSecurity.sessionManagement(Customizer -> Customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }*/
    @Bean
    public SecurityFilterChain sfc(HttpSecurity hs) throws Exception {
        return hs
                .csrf(cus -> cus.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("login", "register").permitAll().anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())  //simple, stateless authentication mechanism where the client sends the username and password in the Authorization header of each HTTP request.
                .sessionManagement(cust -> cust.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
                .build();
    }






    //This is not safe for production
    //(it is intended for getting started experience)
    // because the password "password" is compiled into the source code
    // and then is included in memory at the time of creation.
    // This means there are still ways to recover the plain text password making it unsafe.
/*    @Bean
    UserDetailsService usd (){
        UserDetails ud1 = User.withDefaultPasswordEncoder().username("Aditya1").password("Gupta1").roles("USER").build();
        UserDetails ud2 = User.withDefaultPasswordEncoder().username("Aditya2").password("Gupta2").roles("USER").build();
        UserDetails ud3 = User.withDefaultPasswordEncoder().username("Aditya3").password("Gupta3").roles("USER").build();
        return new InMemoryUserDetailsManager(ud1,ud2,ud3);
    }*/


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }












}
