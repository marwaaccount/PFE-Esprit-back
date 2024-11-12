package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.services.UtilisateurService;
import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigurationSecuriteApplication {
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private final JwtFilter jwtFilter;
	@Autowired
	private final UserDetailsService userDetailsService;

	public ConfigurationSecuriteApplication(BCryptPasswordEncoder bCryptPasswordEncoder, JwtFilter jwtFilter,
			UserDetailsService userDetailsService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtFilter = jwtFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.cors(withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/user/**").permitAll()
						.requestMatchers("/personnel/add").permitAll()
						.requestMatchers("/personnel/getpersonnel").hasAnyAuthority("ROLE_PERS", "ROLE_ADMIN","ROLE_CONDI")
						.requestMatchers("/personnel/**").hasAnyAuthority("ROLE_PERS", "ROLE_ADMIN","ROLE_CONDI")
						.requestMatchers("/offre/add").hasAnyAuthority("ROLE_PERS", "ROLE_ADMIN")
						.requestMatchers("/absence/**").hasAnyAuthority("ROLE_PERS", "ROLE_ADMIN")
						.requestMatchers("/fiche/**").hasAnyAuthority("ROLE_PERS", "ROLE_ADMIN")
						.requestMatchers("/candidatoffre/**").hasAnyAuthority("ROLE_PERS", "ROLE_ADMIN","ROLE_CONDI")
						.requestMatchers("/offre/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

				).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return daoAuthenticationProvider;
	}
	   @Bean
	    public CorsFilter corsFilter() {
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        final CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	        config.setAllowedHeaders(Arrays.asList(
	                HttpHeaders.ORIGIN,
	                HttpHeaders.CONTENT_TYPE,
	                HttpHeaders.ACCEPT,
	                HttpHeaders.AUTHORIZATION
	        ));
	        config.setAllowedMethods(Arrays.asList(
	                "GET",
	                "POST",
	                "DELETE",
	                "PUT",
	                "PATCH"
	        ));
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);

	    }

}
