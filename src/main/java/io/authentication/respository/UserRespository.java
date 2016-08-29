package io.authentication.respository;

import org.springframework.data.jpa.repository.Query;
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
    @Query("select u from User u left join u.phones ps where u.email = :email")
    User findByEmail(@Param("email") String email);

    /**
     * Consulta usuário por e-mail e password
     * 
     * @param email
     * @param password
     * @return user - cadastrado na base
     */
    @Query("select u from User u left join u.phones ps where u.email = :email and u.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    /**
     * Consulta usuário por e-mail e password
     * 
     * @param email
     * @param password
     * @return user - cadastrado na base
     */
    @Query("select u from User u left join u.phones ps where u.id = :id")
    User findById(@Param("id") Long id);
}
