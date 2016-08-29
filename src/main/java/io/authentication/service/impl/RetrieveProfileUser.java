package io.authentication.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.authentication.component.CalculatesMinutes;
import io.authentication.exception.EncryptionException;
import io.authentication.exception.LoginOrPasswordInvalidException;
import io.authentication.exception.NotAuthorizedException;
import io.authentication.exception.SessionInvalidatesException;
import io.authentication.respository.UserRespository;
import io.authentication.respository.entity.User;
import io.authentication.service.RecoversProfile;

/**
 * Classe de Implementação para recuperar os perfis
 * 
 * @author anderson
 */
@Service("profile")
public class RetrieveProfileUser implements RecoversProfile {

    private final long THIRTY_MINUTES = 30L;
    
    @Autowired
    private UserRespository userRepository;
    
    @Autowired
    private CalculatesMinutes calculatesHoras;
    
    /**
     * Método para recupear o Perfil do usuário
     * 
     * @param codeToken
     * @param userId
     * @return user
     * @throws NotAuthorizedException
     * @throws SessionInvalidatesException
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    @Override
    public User retrieveUserProfile(final String token, final Long userId) throws NotAuthorizedException, SessionInvalidatesException {

	if (null == token) 
	    throw new NotAuthorizedException();
	
	User user = userRepository.findOne(userId);
	
	if (null == user || !token.equals(user.getToken()))
	    throw new NotAuthorizedException();
	
	long minutes = calculatesHoras.calculate(user.getLastLogin(), LocalDateTime.now());
	
	if (THIRTY_MINUTES < minutes)
	    throw new SessionInvalidatesException();
	
	return user;
    }

}
