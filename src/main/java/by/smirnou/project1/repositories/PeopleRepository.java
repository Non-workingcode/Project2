package by.smirnou.project1.repositories;

import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByFIO(String FIO); //Валидатор

    List<Person> getPersonByBooks(Book book);

    List<Person> getPersonByBooks(int id);

    List<Person> findByBooks(String title);


}
