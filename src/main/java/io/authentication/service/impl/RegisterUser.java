package io.authentication.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.authentication.component.Encrypts;
import io.authentication.component.GeneratesToken;
import io.authentication.exception.EmailRegisterException;
import io.authentication.exception.EncryptionException;
import io.authentication.respository.UserRespository;
import io.authentication.respository.entity.User;
import io.authentication.service.Register;

/**
 * Classe de Implementação para manter um registro do usuário
 * 
 * @author anderson
 */
@Service("register")
public class RegisterUser implements Register {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private GeneratesToken generatesToken;

    @Autowired
    private Encrypts encryption;

    /**
     * Cadastra um novo usuário
     * 
     * @param user
     * @return user
     * @throws EncryptionException
     */
    @Override
    @Transactional
    public User create(final User user) throws EmailRegisterException, EncryptionException {
	if (null != userRepository.findByEmail(user.getEmail()))
	    throw new EmailRegisterException();

	return userRepository.save(new User(user.getName(), user.getEmail(), encryption.encryptShaTwo(user.getPassword()), user.getPhones(), LocalDateTime.now(), generatesToken.create()));
    }
}
