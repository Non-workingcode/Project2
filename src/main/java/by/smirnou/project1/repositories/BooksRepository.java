package by.smirnou.project1.repositories;

import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book ,Integer> {

    public Optional<Book> findBookByTitleStartingWith(String title);

    public Optional<Book> getBookByOwner(int id);

    public Optional<Book> getBookByOwner(Book book);

    public Book getBookById(int id);



    public Book deleteBookByOwner(int id);

    public Book updateBookByOwner(Person selectedPerson , int id);

    List<Book> findAll(PageRequest of, Sort year);

}
