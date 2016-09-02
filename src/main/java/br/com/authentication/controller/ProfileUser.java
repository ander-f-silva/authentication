package br.com.authentication.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.authentication.exception.MessageError;
import br.com.authentication.exception.NotAuthorizedException;
import br.com.authentication.exception.SessionInvalidatesException;
import br.com.authentication.respository.entity.User;
import br.com.authentication.service.RecoversProfile;

/**
 * Serviço Rest recupearar o perfil do usuário
 * 
 * @author anderson
 */
@RestController
@RequestMapping("/profile")
public class ProfileUser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private RecoversProfile recoversProfile;

    /**
     * Método usado para recuperar perfil do usuário através do token e do id do
     * usuário
     * 
     * @param token
     * @param userId
     * @return Response Entity
     * @throws SessionInvalidatesException
     * @throws NotAuthorizedException
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = { APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<User> retrieveProfile(@RequestHeader(value = "token", required = true) final String token, @PathVariable("userId") final Long userId) throws NotAuthorizedException, SessionInvalidatesException {
	logger.info("Iniciando a consulta do perfil");
	ResponseEntity<User> response = new ResponseEntity<User>(recoversProfile.retrieveUserProfile(token, userId), OK);
	logger.info("Consulta de perfil ocorrido com sucesso");
	return response;
    }

    /**
     * Handler para exibir mensagem permissão não autorizado
     * 
     * @param request
     * @param ex
     * @return messageError
     */
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public MessageError handleNotAuthorizedException(HttpServletRequest request, Exception ex) {
	logger.error("Erro durante a consulta do perfil", ex);
	return new MessageError(ex.getMessage());
    }

    /**
     * Handler para Sessão inválida (observação: nesta versão não esta
     * disponviel o erro 440 para sessão expirada.)
     * 
     * @param request
     * @param ex
     * @return messageError
     */
    @ExceptionHandler(SessionInvalidatesException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MessageError handleSessionInvalidatesException(HttpServletRequest request, Exception ex) {
	logger.error("Erro durante o processo de login", ex);
	return new MessageError(ex.getMessage());
    }
}
