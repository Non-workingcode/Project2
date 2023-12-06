package by.smirnou.project1.repositories;

import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

     List<Person> getPersonById(int id);

    List<Person> getPersonByBooks(Book book);

    List<Person> getPersonByBooks(int id);

    List<Person> findByBooks(String title);


}
