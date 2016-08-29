package br.com.authentication.exception;

/**
 * Erro gerado quando o usuário não tem atutorização para não realizar uma
 * operação
 * 
 * @author andersono
 */
public class NotAuthorizedException extends Exception {

    private static final long serialVersionUID = -8292775752578704912L;

    /**
     * Construtor Padrão
     */
    public NotAuthorizedException() {
	super("Não autorizado");
    }
}
