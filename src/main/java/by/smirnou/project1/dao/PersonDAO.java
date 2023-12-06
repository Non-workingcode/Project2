package by.smirnou.project1.dao;

import by.smirnou.project1.models.Book;
import by.smirnou.project1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

//@Component
//public class PersonDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public PersonDAO(){}
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Optional<Person> show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(FIO,yearOfBirth) VALUES (? , ?)", person.getFIO(), person.getYearOfBirth());
//    }
//
//    public void update(int id, Person person) {
//        jdbcTemplate.update("UPDATE Person set FIO = ? , yearOfBirth =?  where id =? ", person.getFIO(), person.getYearOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person where id = ?", id);
//    }
//
//}
//    //Для валидации уникальности ФИО
//    public Optional<Person> getPersonByFullName(String fullName){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE FIO =? ", new Object[]{fullName},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    //Здесь JOIN не нужен. И так уже получили человека с помощью отдельного метода
//    public List<Book> getBooksByPersonId (int id){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?" , new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class));
//    }
//
//}
