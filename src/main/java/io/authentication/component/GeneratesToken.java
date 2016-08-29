package io.authentication.component;

/**
 * Interface para implementar a geração do token
 * 
 * @author anderson
 */
public interface GeneratesToken {

    /**
     * Método usado para gerar o token
     * 
     * @return token Valor do Token
     */
    String create();
}
