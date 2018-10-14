package br.com.authentication.component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import br.com.authentication.exception.EncryptionException;

/**
 * Classe que o implementa o algoritmo de criptografia
 *
 * @author anderson
 */
@Component("encrypts")
public class Encryption {

    private static final String MODE_ENCRYPT = "SHA-256";
    private static final String FORMAT_CODE = "%02X";

    /**
     * Método para Realizar a Criptografia
     *
     * @param passWord
     * @return passWord encrypt
     */
    public String encryptShaTwo(final String passWord) throws EncryptionException {
        try {
            MessageDigest algorithm = MessageDigest.getInstance(MODE_ENCRYPT);
            byte bites[] = algorithm.digest(passWord.getBytes(StandardCharsets.UTF_8));


            StringBuilder hexString = new StringBuilder();

            for (byte b : bites)
                hexString.append(String.format(FORMAT_CODE, 0xFF & b));

            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new EncryptionException();
        }
    }
}
