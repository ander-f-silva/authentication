package br.com.authentication.exception;

/**
 * Erro gerado quando a sessão está invalida para usuário
 * 
 * @author andersono
 */
public class SessionInvalidatesException extends RuntimeException {

    private static final long serialVersionUID = 6267958863713211964L;
    
    /**
     * Construtor Padrão
     */
    public SessionInvalidatesException() {
	super("Sessão inválida");
    }
}
