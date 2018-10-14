package br.com.authentication.service;

import java.time.LocalDateTime;
import java.util.Optional;

import br.com.authentication.component.CalculationMinutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.authentication.exception.EncryptionException;
import br.com.authentication.exception.LoginOrPasswordInvalidException;
import br.com.authentication.exception.NotAuthorizedException;
import br.com.authentication.exception.SessionInvalidatesException;
import br.com.authentication.respository.UserRespository;
import br.com.authentication.respository.entity.User;

/**
 * Classe de Implementação para recuperar os perfis
 *
 * @author anderson
 */
@Service("profile")
public class RetrieveProfileUser {

    @Autowired
    private UserRespository userRepository;

    @Autowired
    private CalculationMinutes calculatesHoras;

    /**
     * Método para recupear o Perfil do usuário
     *
     * @param token
     * @param userId
     * @return user
     * @throws NotAuthorizedException
     * @throws SessionInvalidatesException
     * @throws LoginOrPasswordInvalidException
     * @throws EncryptionException
     */
    public User retrieveUserProfile(final String token, final Long userId) throws NotAuthorizedException, SessionInvalidatesException {

        if (null == token)
            throw new NotAuthorizedException();

        Optional<User> optUser = userRepository.findById(userId);

        if (!optUser.isPresent() || !token.equals(optUser.get().getToken()))
            throw new NotAuthorizedException();

        long minutes = calculatesHoras.calculate(optUser.get().getLastLogin(), LocalDateTime.now());

        long THIRTY_MINUTES = 30L;
        if (THIRTY_MINUTES < minutes)
            throw new SessionInvalidatesException();

        return optUser.get();
    }

}
