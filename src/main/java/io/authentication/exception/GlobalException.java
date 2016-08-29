package io.authentication.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Captura alguma expetion não identificada no sistema
 * 
 * @author anderson
 *
 */
@RestControllerAdvice
public class GlobalException {

    /**
     * Método Handler para capturar erros não tratados no sistema
     * 
     * @param request
     * @param ex
     * @return Mensagem de Erro que será transformado no formato json
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody MessageError handleException(HttpServletRequest request, Exception ex) {
	return new MessageError("Falha ocorrida no sistema. Favor entrar em contato com administrador do sistema.");
    }
}
