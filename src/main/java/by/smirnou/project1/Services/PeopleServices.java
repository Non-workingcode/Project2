package by.smirnou.project1.Services;


import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import by.smirnou.project1.repositories.BooksRepository;
import by.smirnou.project1.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class PeopleServices {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleServices(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll(){
        return  peopleRepository.findAll();
    }

    public Optional<Person> findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.stream().findAny();
    }

    public Optional<Person> findPersonByTitle(String title){
        Optional<Person> foundPerson = peopleRepository.findByBooks(title).stream().findAny();
        return foundPerson;
    }

    @Transactional
    public Person save(Person person){
        return peopleRepository.save(person);
    }

    @Transactional
    public Person update(int id, Person person){
        person.setId(id);
        return peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }



    public boolean checkBook(Book book){
        Date startDate = Date.from(book.getTimeTakeBook().toInstant());
        Date plusTenDays = Date.from(book.getTimeTakeBook().toInstant().plus(10, ChronoUnit.DAYS));
        Duration duration = Duration.between(startDate.toInstant(),plusTenDays.toInstant());
        if(duration.getSeconds() - duration.getSeconds() >1){
            return false;
        }
        return true;
    }


    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }

    }
}
