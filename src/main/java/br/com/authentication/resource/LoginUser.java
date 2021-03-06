package br.com.authentication.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import br.com.authentication.service.AuthenticationUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.authentication.vo.Login;
import br.com.authentication.exception.EncryptionException;
import br.com.authentication.exception.LoginOrPasswordInvalidException;
import br.com.authentication.exception.MessageError;
import br.com.authentication.respository.entity.User;

/**
 * Serviço Rest realizar a autenticação no sistema.
 *
 * @author anderson
 */
@RestController
@RequestMapping("/login")
public class LoginUser {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationUser authentication;

    /**
     * Métdod usado para realizar o login através do email e senha.
     *
     * @param login
     * @return Response Entity
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    @PostMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<User> authentication(@Validated @RequestBody final Login login) throws LoginOrPasswordInvalidException, EncryptionException {
        logger.info("Iniciando a autenticação do usuário");
        ResponseEntity<User> response = new ResponseEntity<User>(authentication.login(login), OK);
        logger.info("Login ocorrido com sucesso");
        return response;
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
        logger.error("Erro durante o processo de login", ex);
        return new MessageError(ex.getMessage());
    }
}
