package io.authentication.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.authentication.exception.EmailRegisterException;
import io.authentication.exception.EncryptionException;
import io.authentication.exception.MessageError;
import io.authentication.respository.entity.User;
import io.authentication.service.Register;

/**
 * Serviço para cadastrar um novo usuário
 * 
 * @author anderson
 */
@RestController
@RequestMapping("/user")
public class RegisterUser {

    @Autowired
    private Register register;

    /**
     * Médodo usado para realizar o cadastro do cliente.
     * 
     * @param user
     * @return Response Entity
     * @throws EmailRegisterException
     * @throws EncryptionException
     */
    @RequestMapping(method = RequestMethod.POST, produces = { APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody ResponseEntity<User> create(@Valid @RequestBody final User user) throws EmailRegisterException, EncryptionException {
	return new ResponseEntity<User>(register.create(user), CREATED);
    }

    /**
     * Handler para exibir mensagem usuário invalido
     * 
     * @param request
     * @param ex
     * @return messageError
     */
    @ExceptionHandler(EmailRegisterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody MessageError handleEmailRegisterException(HttpServletRequest request, Exception ex) {
	return new MessageError(ex.getMessage());
    }
}
