package io.authentication.service;

import io.authentication.exception.EncryptionException;
import io.authentication.exception.LoginOrPasswordInvalidException;
import io.authentication.exception.NotAuthorizedException;
import io.authentication.exception.SessionInvalidatesException;
import io.authentication.respository.entity.User;

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
