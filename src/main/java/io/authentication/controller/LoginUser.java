package io.authentication.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.authentication.exception.EncryptionException;
import io.authentication.exception.LoginOrPasswordInvalidException;
import io.authentication.exception.MessageError;
import io.authentication.respository.entity.User;
import io.authentication.service.Authenticates;

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
    @RequestMapping(method = RequestMethod.GET, produces = { APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody ResponseEntity<User> authentication(@RequestParam(name = "email", required = true) final String email, @RequestParam(name = "password", required = true) final String password) throws LoginOrPasswordInvalidException, EncryptionException {
	return new ResponseEntity<User>(authentication.login(email, password), OK);
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
    public @ResponseBody MessageError handleLoginOrPasswordInvalidException(HttpServletRequest request, Exception ex) {
	return new MessageError(ex.getMessage());
    }
}
