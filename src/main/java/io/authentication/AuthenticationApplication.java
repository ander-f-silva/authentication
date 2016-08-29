package io.authentication;

import static org.springframework.http.MediaType.ALL_VALUE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe para iniciar o contexto do spring boot. Observação: O web container
 * utilizado é Tomcat.
 * 
 * @author anderson
 */
@SpringBootApplication
@Controller
@EnableAutoConfiguration
public class AuthenticationApplication {

    private final String RETURN_EMPTY = "";
    private final String CONTEXT_PATH = "/";

    /**
     * Método para inicar o contexto do serviço
     * 
     * @return empty
     */
    @RequestMapping(value = CONTEXT_PATH, produces = ALL_VALUE)
    @ResponseBody
    String index() {
	return RETURN_EMPTY;
    }

    /**
     * Método iniciar a spring boot
     * 
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(AuthenticationApplication.class, args);
    }
}
