package br.com.authentication.exception;

/**
 * Erro gerado quando ocorrer problema na autenticação
 * 
 * @author anderson
 */
public class LoginOrPasswordInvalidException extends Exception {

    private static final long serialVersionUID = -7607615499230706368L;

    /**
     * Construtor Padrão
     */
    public LoginOrPasswordInvalidException() {
	super("Usuário e/ou senha inválidos");
    }

}
