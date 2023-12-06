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


    public BooksServices(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;

    }

//    public List<Book> findAll(){
//        return booksRepository.findAll();
//    }
//
//    public List<Book> findAll(String sorting){
//        return booksRepository.findAll(Sort.by(sorting));
//    }
//
//    public List<Book> findAll(int page , int itemsPerPage){
//        return booksRepository.findAll(PageRequest.of(page , itemsPerPage)).getContent();
//    }
//    public List<Book> findAll(int page , int itemsPerPage, String sorting){
//        return booksRepository.findAll(PageRequest.of(page , itemsPerPage, Sort.by(sorting))).getContent();
//    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        } else {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
        }
    }


    public Optional<Book> findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.stream().findAny();
    }

    public List<Book> searchByTitle(String someTitle) {
//        Optional<Book> foundBook = booksRepository.findByTitleStartingWith(someTitle).stream().findAny();
//        return foundBook;
        return booksRepository.findByTitleStartingWith(someTitle);
    }

//    public Optional<Person> findOwner(Book book){
//        Optional<Person> bookOwner = peopleRepository.getPersonByBooks(book).stream().findAny();
//        return bookOwner;
//    }
//
//    public Optional<Person> findOwner (int id){
//        Optional<Person> owner = peopleRepository.getPersonByBooks(id).stream().findAny();
//        return owner;
//    }


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
    public Book save(Book book) {
        return booksRepository.save(book);
    }

    @Transactional
    public Book update(Book updateBook, int id) {

        //добавляем по сути новую книгу( которая не находится в Persistence context)
        //пожтому нужен save()
        updateBook.setId(id);
        updateBook.setOwner(updateBook.getOwner()); //чтобы не терялась связи при обновлении

        return booksRepository.save(updateBook);
    }

    // Освобождает книгу(этот метод вызывается, когда человек возвращает книгу в библиотеку)
    @Transactional
    public void release(int id) {
        //так как на данной сущности уже висят книги , мы меняем в них владельца и время взятия
        //данная сущность возвращается через booksRepository.findById(id) и она находится в Persistence Context
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTimeTakeBook(null);
                });
    }

    // Returns null if book has no owner
    public Person getBookOwner(int id) {
        //Здесь Hibernate.initialize() не нужен, так как владелец(Сторона One) загружается не лениво
        //Если метод map вернул человека, то возвращаем объект класса person
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }


//    @Transactional
//    public Book assign(int id, Person selectedPerson){
//        selectedPerson.setTimeTakeBook(new Date());
//        Book book = booksRepository.getBookById(id);
//        book.setTimeTakeBook(new Date());
//        return booksRepository.updateBookByOwner(selectedPerson , id);
//    }

    // Назначает книгу человеку(этот метод вызывается, когда человек забирает книгу из библиотеки)
    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                    book.setTimeTakeBook(new Date()); // Текущее время
                }
        );
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }
}
