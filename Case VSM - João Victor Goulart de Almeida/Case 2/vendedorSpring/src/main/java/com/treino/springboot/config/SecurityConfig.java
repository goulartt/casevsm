package com.treino.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.treino.springboot.repository.UsuarioDao;
import com.treino.springboot.repository.VendedorDao;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UsuarioDao vendedor;
	
	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	            	.antMatchers("/vendedores/**").permitAll()
	            	.antMatchers(HttpMethod.POST,"/vendedores/**").permitAll()
	                .anyRequest().authenticated()
	                .antMatchers("/resources/**", "/webjars/**").permitAll()
	            
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .permitAll()	
	             .and()
	                .logout().permitAll()
	        	.and()
	        	 .rememberMe();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(vendedor).passwordEncoder(new BCryptPasswordEncoder()).and()
	            .inMemoryAuthentication()
	                .withUser("1").password("1").roles("USER");
	            
	        	
	        	
	    }
}	
