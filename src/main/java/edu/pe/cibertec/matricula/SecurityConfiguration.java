package edu.pe.cibertec.matricula;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pe.cibertec.matricula.repositories.UserRepository;
import edu.pe.cibertec.matricula.services.MyUserDetailsService;

@Configuration
public class SecurityConfiguration {
    

    @Bean
    public UserDetailsService getUserDetailsService(UserRepository userRepository) {
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
            .authorizeHttpRequests(authorizeHttpRequests -> 
                authorizeHttpRequests
                    .requestMatchers("/nosotros", "/" , "docentes", "alumnos","/registrar").permitAll()
                    .requestMatchers("/Registrar", "/Actualizar", "/Eliminar", "/**").authenticated()
                    
                )
            .formLogin(formLogin -> 
                formLogin.loginPage("/login")
                    .permitAll())
            .build();
    }
}