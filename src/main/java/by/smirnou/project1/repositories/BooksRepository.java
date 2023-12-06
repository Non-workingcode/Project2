package by.smirnou.project1.repositories;

import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book ,Integer> {

//     Optional<Book> findBookByTitleStartingWith(String title);

    List<Book> findByTitleStartingWith(String title); // П -> Психопатология

     Book getBookById(int id);

     Book deleteBookByOwner(int id);

     Book updateBookByOwner(Person selectedPerson , int id);



}
