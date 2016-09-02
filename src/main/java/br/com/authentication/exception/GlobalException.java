package br.com.authentication.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Captura alguma expetion não identificada no sistema
 * 
 * @author anderson
 *
 */
@RestControllerAdvice
public class GlobalException {

    
    private final Logger logger = Logger.getLogger("br.com.authentication.exception");
    
    /**
     * Método Handler para capturar erros não tratados no sistema
     * 
     * @param request
     * @param ex
     * @return Mensagem de Erro que será transformado no formato json
     */
    @ExceptionHandler(Exception.class)
    public MessageError handleException(HttpServletRequest request, Exception ex) {
	logger.log(Level.SEVERE, "Erro inesperado", ex);
	return new MessageError("Falha ocorrida no sistema. Favor entrar em contato com administrador do sistema.");
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageError handleMethodArgumentNotValidException(HttpServletRequest request, Exception ex) {
	logger.log(Level.SEVERE, "Erro no preenchimento de dados", ex);
	return new MessageError(ex.getMessage());
    }
    
    @ExceptionHandler(ServletRequestBindingException.class)
    public MessageError handleServletRequestBindingException(HttpServletRequest request, Exception ex) {
	logger.log(Level.SEVERE, "Erro na construção da requisição", ex);
	return new MessageError(ex.getMessage());
    }
}
