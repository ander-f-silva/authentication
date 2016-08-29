package io.authentication.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.authentication.respository.entity.User;

/**
 * Inteface Spring Data - Impl Hibernate para realizarção operação de
 * persistência.
 * 
 * @author anderson
 */
public interface UserRespository extends CrudRepository<User, Long> {

    /**
     * Consulta usuário por e-mail
     * 
     * @param email
     * @return user
     */
    User findByEmail(@Param("email") String email);

    /**
     * Consulta usuário por e-mail
     * 
     * @param email
     * @param password
     * @return user - cadastrado na base
     */
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
