package br.com.authentication.exception;

/**
 * Erro gerado quando ocorrer problema na
 * 
 * @author anderson
 */
public class EncryptionException extends RuntimeException {

    private static final long serialVersionUID = -1016010326713713248L;

    /**
     * Construtor Padrão
     */
    public EncryptionException() {
	super("Não foi possivel registrar o usuário");
    }

}
