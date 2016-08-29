package io.authentication.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.authentication.component.Encrypts;
import io.authentication.exception.EncryptionException;
import io.authentication.exception.LoginOrPasswordInvalidException;
import io.authentication.respository.UserRespository;
import io.authentication.respository.entity.User;
import io.authentication.service.Authenticates;

/**
 * Classe de Implementação para relizar o login
 * @author anderson
 */
@Service("authenticates")
public class AuthenticationUser implements Authenticates {

    @Autowired
    private Encrypts encrypts;

    @Autowired
    private UserRespository userRepository;

    /**
     * Serviço usado para realizar o login.
     * 
     * @param email
     * @param password
     * @return user
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    @Override
    public User login(final String email, final String password) throws LoginOrPasswordInvalidException, EncryptionException {
	User user = userRepository.findByEmailAndPassword(email, encrypts.encryptShaTwo(password));

	if (null == user)
	    throw new LoginOrPasswordInvalidException();

	user.setLastLogin(LocalDateTime.now());
	user = userRepository.save(user);
	return user;
    }
}
