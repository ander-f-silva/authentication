package br.com.authentication.component.impl;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWTSigner;

import br.com.authentication.component.GeneratesToken;

/**
 * Classe de Implementacao do Token
 * 
 * @author anderson
 */
@Component("generatesToken")
public class GenerationToken implements GeneratesToken {

    /**
     * Método para gerar Token
     * 
     * @return token
     */
    @Override
    public String create() {
	long iat = System.currentTimeMillis() / 1000l;

	JWTSigner signer = new JWTSigner("{{a secret used for signing}}");
	HashMap<String, Object> claims = new HashMap<String, Object>();
	claims.put("iss", "https://localhost/");
	claims.put("exp", iat + 60L);
	claims.put("iat", iat);

	return signer.sign(claims);
    }
}
