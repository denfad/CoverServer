package ru.denfad.kvantorida.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.denfad.kvantorida.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select p from Person p where p.login = :login and p.password = :password")
    Person findUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("select count(p) from Person p where p.status = :status")
    Integer getIllPersons(@Param("status") String status);
}
