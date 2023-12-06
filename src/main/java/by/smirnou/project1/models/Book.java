package by.smirnou.project1.models;

import by.smirnou.project1.Services.PeopleServices;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "title")
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min =  2 , max = 100, message = "Название книги должно быть от 2 до 100 символов")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2 , max = 100 , message = "Имя автора должно быть от 2 до 100 символов")
    private String author;

    @Column(name = "year")
    @Min(value = 1500,message = "Год должен быть больше , чем 1500")
    private int year;


    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "time_take_book")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTakeBook;

    @Transient
    private boolean expired; //Hibernate не будет замечать этого поля, что нам и нужно. По-умолчанию false

    public Book(){}

    public Book (String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Date getTimeTakeBook() {
        return timeTakeBook;
    }

    public void setTimeTakeBook(Date timeTakeBook) {
        this.timeTakeBook = timeTakeBook;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
