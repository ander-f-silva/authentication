package br.com.authentication.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.authentication.component.Encrypts;
import br.com.authentication.component.GeneratesToken;
import br.com.authentication.exception.EmailRegisterException;
import br.com.authentication.exception.EncryptionException;
import br.com.authentication.respository.UserRespository;
import br.com.authentication.respository.entity.User;
import br.com.authentication.service.Register;

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
