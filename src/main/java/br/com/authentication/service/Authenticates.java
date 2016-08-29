package br.com.authentication.service;

import br.com.authentication.exception.EncryptionException;
import br.com.authentication.exception.LoginOrPasswordInvalidException;
import br.com.authentication.respository.entity.User;

/**
 * Classe para tratar com as informaçõe de autenticação
 * 
 * @author anderson
 *
 */
public interface Authenticates {

    /**
     * Método para realizar o login no sistema
     * 
     * @param email
     * @param password
     * @return user
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    User login(final String email, final String password) throws LoginOrPasswordInvalidException, EncryptionException;
}
