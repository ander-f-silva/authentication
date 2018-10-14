package br.com.authentication.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

/**
 * Classe de Implementacao do Token
 *
 * @author anderson
 */
@Component("generatesToken")
public class GenerationToken {

	/**
	 * Método para gerar Token
	 *
	 * @return token
	 */
	public String create() {
		Long iat = System.currentTimeMillis() / 1000l;

		Algorithm algorithm = Algorithm.HMAC256(iat.toString());

		return  JWT.create()
				.withIssuer("auth0")
				.sign(algorithm);

	}
}
