package br.com.authentication.service;

import java.time.LocalDateTime;

import br.com.authentication.component.Encryption;
import br.com.authentication.component.GenerationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.authentication.exception.EmailRegisterException;
import br.com.authentication.exception.EncryptionException;
import br.com.authentication.respository.UserRespository;
import br.com.authentication.respository.entity.User;

/**
 * Classe de Implementação para manter um registro do usuário
 * 
 * @author anderson
 */
@Service("register")
public class RegisterUser {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private GenerationToken generatesToken;

    @Autowired
    private Encryption encryption;

    /**
     * Cadastra um novo usuário
     * 
     * @param user
     * @return user
     * @throws EncryptionException
     */
    @Transactional
    public User create(final User user) throws EmailRegisterException, EncryptionException {
	    if (null != userRepository.findByEmail(user.getEmail()))
	        throw new EmailRegisterException();

	    return userRepository.save(new User(user.getName(), user.getEmail(), encryption.encryptShaTwo(user.getPassword()), user.getPhones(), LocalDateTime.now(), generatesToken.create()));
    }
}
