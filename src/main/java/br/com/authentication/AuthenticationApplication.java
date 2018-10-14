package br.com.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe para iniciar o contexto do spring boot. Observação: O web container
 * utilizado é Tomcat.
 * 
 * @author anderson
 */
@SpringBootApplication
@EnableAutoConfiguration
class AuthenticationApplication {

    
    /**
     * Método iniciar a spring boot
     * 
     * @param args
     * @throws SecurityException
     */
    public static void main(String[] args) throws SecurityException {
	SpringApplication.run(AuthenticationApplication.class, args);
    }
}
