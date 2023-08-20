package edu.pe.cibertec.matricula.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.pe.cibertec.matricula.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    // select * from user where username = ?
    Optional<User> findByUsername(String username);

     // select * from user where username = ?
    Optional<User> getByUsername(String username);
    Optional<User> queryByUsername(String username);


    // select * from user where username = ? and registrationDate = ?
    Optional<User> findByUsernameAndRegistrationDate(String username, LocalDateTime registrationDate);

    // select * from user where username like %?%
    Optional<User> findByUsernameContains(String username);
    

    // metodos personalizados (custom methods) basados en una nomenclatura
    @Query("select u from User u where u.username = :username")
    Optional<User> buscarPorNombre(String username);

    @Query(value = "select * from user where username = :username", nativeQuery = true)
    Optional<User> buscarPorEmail(String username);

}
