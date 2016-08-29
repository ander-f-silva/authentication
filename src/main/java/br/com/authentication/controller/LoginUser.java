package br.com.authentication.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.authentication.dto.Login;
import br.com.authentication.exception.EncryptionException;
import br.com.authentication.exception.LoginOrPasswordInvalidException;
import br.com.authentication.exception.MessageError;
import br.com.authentication.respository.entity.User;
import br.com.authentication.service.Authenticates;

/**
 * Serviço Rest realizar a autenticação no sistema.
 * 
 * @author anderson
 */
@RestController
@RequestMapping("/login")
public class LoginUser {

    @Autowired
    private Authenticates authentication;

    /**
     * Métdod usado para realizar o login através do email e senha.
     * 
     * @param email
     * @param password
     * @return Response Entity
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    @RequestMapping(method = RequestMethod.POST, produces = { APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE })
    public ResponseEntity<User> authentication(@Validated @RequestBody final Login login) throws LoginOrPasswordInvalidException, EncryptionException {
	return new ResponseEntity<User>(authentication.login(login), OK);
    }

    /**
     * Handler para Login o usuário inválido
     * 
     * @param request
     * @param ex
     * @return messageError
     */
    @ExceptionHandler(LoginOrPasswordInvalidException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public MessageError handleLoginOrPasswordInvalidException(HttpServletRequest request, Exception ex) {
	return new MessageError(ex.getMessage());
    }
}
