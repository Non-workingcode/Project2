package by.smirnou.project1.Services;


import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import by.smirnou.project1.repositories.BooksRepository;
import by.smirnou.project1.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
@Transactional(readOnly = true)
public class BooksServices {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;




    public BooksServices(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public List<Book> findAll(String sorting){
        return booksRepository.findAll(Sort.by(sorting));
    }

    public List<Book> findAll(int page , int itemsPerPage){
        return booksRepository.findAll(PageRequest.of(page , itemsPerPage)).getContent();
    }

    public List<Book> findAll(int page , int itemsPerPage, String sorting){
        return booksRepository.findAll(PageRequest.of(page , itemsPerPage, Sort.by(sorting))).getContent();
    }


    public Optional<Book> findOne(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.stream().findAny();
    }

    public Optional<Book> findBookByTitleStartingWith(String someTitle){
        Optional<Book> foundBook = booksRepository.findBookByTitleStartingWith(someTitle).stream().findAny();
        return foundBook;
    }

    public Optional<Person> findOwner(Book book){
        Optional<Person> bookOwner = peopleRepository.getPersonByBooks(book).stream().findAny();
        return bookOwner;
    }

    public Optional<Person> findOwner (int id){
        Optional<Person> owner = peopleRepository.getPersonByBooks(id).stream().findAny();
        return owner;
    }


//    public List<Book> getBooksByPersonId(int id){
//        Optional<Person> person = peopleRepository.findById(id);
//
//        if(person.isPresent()){
//            Hibernate.initialize(person.get().getBooks());
//            return person.get().getBooks();
//        }
//        else {
//            return Collections.emptyList();
//        }
//    }






    @Transactional
    public Book save(Book book){
        return booksRepository.save(book);
    }

    @Transactional
    public Book update(Book updateBook , int id){
        updateBook.setId(id);
        return  booksRepository.save(updateBook);
    }


    @Transactional
    public Book release( int id){
       return booksRepository.deleteBookByOwner(id);
    }


    @Transactional
    public Book assign(int id, Person selectedPerson){
        selectedPerson.setTimeTakeBook(new Date());
        Book book = booksRepository.getBookById(id);
        book.setTimeTakeBook(new Date());
        return booksRepository.updateBookByOwner(selectedPerson , id);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }
}
