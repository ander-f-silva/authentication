package io.authentication.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.authentication.exception.MessageError;
import io.authentication.exception.NotAuthorizedException;
import io.authentication.exception.SessionInvalidatesException;
import io.authentication.respository.entity.User;
import io.authentication.service.RecoversProfile;

/**
 * Serviço Rest recupearar o perfil do usuário
 * 
 * @author anderson
 */
@RestController
@RequestMapping("/profile")
public class ProfileUser {

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
    public @ResponseBody ResponseEntity<User> retrieveProfile(@RequestHeader(value = "token", required = true) final String token, @PathVariable("userId") final Long userId) throws NotAuthorizedException, SessionInvalidatesException {
	return new ResponseEntity<User>(recoversProfile.retrieveUserProfile(token, userId), OK);
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
    public @ResponseBody MessageError handleNotAuthorizedException(HttpServletRequest request, Exception ex) {
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
    public @ResponseBody MessageError handleSessionInvalidatesException(HttpServletRequest request, Exception ex) {
	return new MessageError(ex.getMessage());
    }
}
