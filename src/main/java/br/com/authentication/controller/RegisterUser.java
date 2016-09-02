package br.com.authentication.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.authentication.exception.EmailRegisterException;
import br.com.authentication.exception.EncryptionException;
import br.com.authentication.exception.MessageError;
import br.com.authentication.respository.entity.User;
import br.com.authentication.service.Register;

/**
 * Serviço para cadastrar um novo usuário
 * 
 * @author anderson
 */
@RestController
@RequestMapping("/user")
public class RegisterUser {

    private final Logger logger = Logger.getLogger("br.com.authentication.controller");
    
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
    public ResponseEntity<User> create(@Valid @RequestBody final User user) throws EmailRegisterException, EncryptionException {
	ResponseEntity<User> response = new ResponseEntity<User>(register.create(user), CREATED);
	logger.info("Cadastrao realizado com sucesso");
	return response;
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
    public MessageError handleEmailRegisterException(HttpServletRequest request, Exception ex) {
	logger.log(Level.SEVERE, "Erro no processo de cadastro do usuário", ex);
	return new MessageError(ex.getMessage());
    }
}
