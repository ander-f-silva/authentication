package io.authentication.exception;

/**
 * Erro para e-mail já cadastrado
 * 
 * @author anderson
 */
public class EmailRegisterException extends Exception {

    private static final long serialVersionUID = -1016010326713713248L;

    /**
     * Construtor Padrão
     */
    public EmailRegisterException() {
	super("E-mail já existente");
    }

}
