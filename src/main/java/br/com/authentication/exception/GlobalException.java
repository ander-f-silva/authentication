package br.com.authentication.exception;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * Método Handler para capturar erros não tratados no sistema
     * 
     * @param request
     * @param ex
     * @return Mensagem de Erro que será transformado no formato json
     */
    @ExceptionHandler(Exception.class)
    public MessageError handleException(HttpServletRequest request, Exception ex) {
	logger.error("Erro inesperado", ex);
	return new MessageError("Falha ocorrida no sistema. Favor entrar em contato com administrador do sistema.");
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageError handleMethodArgumentNotValidException(HttpServletRequest request, Exception ex) {
	logger.error("Argumentos Invalidos", ex);
	return new MessageError(ex.getMessage());
    }
    
    @ExceptionHandler(ServletRequestBindingException.class)
    public MessageError handleServletRequestBindingException(HttpServletRequest request, Exception ex) {
	logger.error("Request Invalido", ex);
	return new MessageError(ex.getMessage());
    }
}
