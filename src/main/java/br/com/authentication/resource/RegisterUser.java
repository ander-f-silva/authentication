package br.com.authentication.resource;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Serviço para cadastrar um novo usuário
 *
 * @author anderson
 */
@RestController
@RequestMapping("/user")
public class RegisterUser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private br.com.authentication.service.RegisterUser register;

    /**
     * Médodo usado para realizar o cadastro do cliente.
     *
     * @param user
     * @return Response Entity
     * @throws EmailRegisterException
     * @throws EncryptionException
     */
    @RequestMapping(method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<User> create(@Valid @RequestBody final User user) throws EmailRegisterException, EncryptionException {
        logger.info("Iniciando o cadastro do usuário");
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
        logger.error("Erro no processo de cadastro do usuário", ex);
        return new MessageError(ex.getMessage());
    }
}
