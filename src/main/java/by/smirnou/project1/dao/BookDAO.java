package by.smirnou.project1.dao;
//
//
//import by.smirnou.project1.models.Book;
//import by.smirnou.project1.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
////@Component
//public class BookDAO {
//
//    private  final JdbcTemplate jdbcTemplate;
//
////    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index(){
//        return jdbcTemplate.query("SELECT * FROM Book" , new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Optional<Book> show(int id){
//        return jdbcTemplate.query("SELECT * FROM Book , where id = ?" , new Object[]{id},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
//    }
//
//    public void save(Book book){
//        jdbcTemplate.update("INSERT INTO Book(title,author,year) values (?, ?, ?)", book.getTitle(), book.getTitle() ,book.getYear());
//    }
//
//    public void update(int id, Book updatedBook){
//        jdbcTemplate.update("UPDATE Book set title = ?, author =? , year = ? where id = ?"  ,
//                updatedBook.getTitle() , updatedBook.getAuthor() , updatedBook.getYear() ,id);
//    }
//
//    public void delete(int id){
//        jdbcTemplate.update("DELETE FROM Book where  id = ?" , id);
//    }
//
//    //Join'им таблицы Book и Person  и получаем человека , которому принадлежит книга с указанным id
//    public Optional<Person> getBookOwner(int id){
//        //Выбираем все колонки таблицы Person из объединенной таблицы
//        return jdbcTemplate.query("Select * from Book join on Person Book_person_id = Person.id" + "where book.id = ?" ,
//                new Object[]{id} ,
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    //Освобождаем книгу (этот метод вызывается, когда человек возвращает книгу в библиотеку)
//    public void release(int id){
//        jdbcTemplate.update("UPDATE BOOK set person_id = null where id = ?" , id);
//    }
//
//    //Назначает книгу человеку (этот метод вызывается  , когда человек забирает книгу)
//    public void assign(int id, Person selectedPerson){
//        jdbcTemplate.update("UPDATE BOOK set person_id = ? where id = ?" , selectedPerson.getFIO(),id);
//    }
//
//
//
//}
