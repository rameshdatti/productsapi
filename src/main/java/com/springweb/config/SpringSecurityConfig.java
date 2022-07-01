package com.springweb.config;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
	        corsConfiguration.setAllowedOrigins(List.of("*"));
	        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
	        corsConfiguration.setAllowCredentials(true);
	        corsConfiguration.setExposedHeaders(List.of("Authorization"));
	        
	        // You can customize the following part based on your project, it's only a sample
	        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest()
	                .authenticated().and().csrf().disable().cors().configurationSource(request -> corsConfiguration);

	    }
	 @Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	    .passwordEncoder(passwordEncoder())
	    .withUser("revathi")
	    .password(passwordEncoder().encode("revathi"))
	    .roles("USER");
	   }
	  @Bean
	   public PasswordEncoder passwordEncoder() {
	       return new BCryptPasswordEncoder();
	   }
	/*
	@Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	    .passwordEncoder(passwordEncoder())
	    .withUser("revathi")
	    .password(passwordEncoder().encode("revathi"))
	    .roles("USER");
	   }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
    }

	   @Bean
	   public PasswordEncoder passwordEncoder() {
	       return new BCryptPasswordEncoder();
	   } 
		
		  @Bean CorsConfigurationSource corsConfigurationSource() { CorsConfiguration
		  configuration = new CorsConfiguration();
		  configuration.setAllowedOrigins(Arrays.asList("*"));
		  configuration.setAllowedMethods(Arrays.asList("*"));
		  configuration.setAllowedHeaders(Arrays.asList("*"));
		  configuration.setAllowCredentials(true); UrlBasedCorsConfigurationSource
		  source = new UrlBasedCorsConfigurationSource();
		  source.registerCorsConfiguration("/**", configuration); return source; }
		 
*/
}