package br.com.authentication.service;

import br.com.authentication.exception.EncryptionException;
import br.com.authentication.exception.LoginOrPasswordInvalidException;
import br.com.authentication.exception.NotAuthorizedException;
import br.com.authentication.exception.SessionInvalidatesException;
import br.com.authentication.respository.entity.User;

/**
 * Interface para implantar a recuperação do profile
 * 
 * @author anderson
 */
public interface RecoversProfile {

    /**
     * Método para recupear o Perfil do usuário
     * 
     * @param codeToken
     * @param userId
     * @return user
     * @throws UserDoesNotExistException
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    User retrieveUserProfile(final String token, final Long userId) throws NotAuthorizedException, SessionInvalidatesException;

}
