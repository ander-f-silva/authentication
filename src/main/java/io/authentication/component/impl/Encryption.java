package io.authentication.component.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import io.authentication.component.Encrypts;
import io.authentication.exception.EncryptionException;

/**
 * Classe que o implementa o algoritmo de criptografia
 * 
 * @author anderson
 */
@Component("encrypts")
public class Encryption implements Encrypts {

    private static final String MODE_ENCRYPT = "SHA-256";
    private static final String FORMAT_CODE = "%02X";
    private static final String FORMAT_UTF_8 = "UTF-8";

    /**
     * Método para Realizar a Criptografia
     * 
     * @param passWord
     * @return passWord encrypt
     */
    @Override
    public String encryptShaTwo(final String passWord) throws EncryptionException {
	try {
	    MessageDigest algorithm = MessageDigest.getInstance(MODE_ENCRYPT);

	    byte messageDigest[] = algorithm.digest(passWord.getBytes(FORMAT_UTF_8));

	    StringBuilder hexString = new StringBuilder();

	    for (byte b : messageDigest)
		hexString.append(String.format(FORMAT_CODE, 0xFF & b));

	    return hexString.toString();
	} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
	    throw new EncryptionException();
	}
    }
}
