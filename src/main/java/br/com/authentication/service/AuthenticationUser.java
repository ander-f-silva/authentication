package br.com.authentication.service;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.authentication.component.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.authentication.vo.Login;
import br.com.authentication.exception.EncryptionException;
import br.com.authentication.exception.LoginOrPasswordInvalidException;
import br.com.authentication.respository.UserRespository;
import br.com.authentication.respository.entity.User;

/**
 * Classe de Implementação para relizar o login
 *
 * @author anderson
 */
@Service("authenticates")
public class AuthenticationUser {

    @Autowired
    private Encryption encrypts;

    @Autowired
    private UserRespository userRepository;

    /**
     * Serviço usado para realizar o login.
     *
     * @param login
     * @return user
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    public User login(final Login login) throws LoginOrPasswordInvalidException, EncryptionException {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), encrypts.encryptShaTwo(login.getPassword()));

        Optional.ofNullable(user).orElseThrow(LoginOrPasswordInvalidException::new);

        user.setLastLogin(LocalDateTime.now());
        user = userRepository.save(user);
        return user;
    }
}
