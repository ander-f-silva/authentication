package br.com.authentication;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class AuthenticationApplication {

    
    /**
     * Método iniciar a spring boot
     * 
     * @param args
     * @throws IOException 
     * @throws SecurityException 
     */
    public static void main(String[] args) throws SecurityException, IOException {
	 Handler fh = new FileHandler("%t/authentication.log");
	 Logger.getLogger("").addHandler(fh);
	 Logger.getLogger("br.com.authentication").setLevel(Level.ALL);
	
	SpringApplication.run(AuthenticationApplication.class, args);
    }
}
