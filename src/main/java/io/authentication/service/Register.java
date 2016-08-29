package io.authentication.service;

import io.authentication.exception.EmailRegisterException;
import io.authentication.exception.EncryptionException;
import io.authentication.respository.entity.User;

/**
 * Serviço para realizar as operações para manipular os dados o usuário.
 * 
 * @author anderson
 */
public interface Register {
    /**
     * Método para criar um usuário
     * 
     * @param user
     * @return user
     * @throws EmailRegisterException
     * @throws EncryptionException
     */
    User create(final User user) throws EmailRegisterException, EncryptionException;

}
