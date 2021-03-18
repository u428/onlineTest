package com.omad.lee.damo;

import com.omad.lee.damo.Context.SpringApplicationContext;
import com.omad.lee.damo.Security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
public class DamoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DamoApplication.class, args);
	}


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}
	@Bean(name = "AppProperties")
	public AppProperties getAppProperties(){
		return new AppProperties();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("/**"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new
				UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
		configuration.setExposedHeaders(Arrays.asList("Authorization", "UserID"));
//		configuration.setExposedHeaders(Arrays.asList("UserID"));
		return source;
	}


}
