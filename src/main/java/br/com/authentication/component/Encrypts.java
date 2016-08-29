package br.com.authentication.component;

import br.com.authentication.exception.EncryptionException;

/**
 * Interface para implantar a criptografia
 * 
 * @author anderson
 */
public interface Encrypts {

    /**
     * Método para realizar o processo de criptografia SHA-2
     * 
     * @param passWord
     * @return hash do passWord
     * @throws EncryptionException
     */
    String encryptShaTwo(final String passWord) throws EncryptionException;
}
