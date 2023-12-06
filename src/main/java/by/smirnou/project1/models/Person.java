package by.smirnou.project1.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Person")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "FIO")
    @NotEmpty
    @Pattern(regexp = ("[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+") , message = "FIO should be in the 3 words")
    private String FIO;

    @Column(name = "yearOfBirth")
    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;


    @Transient
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date timeTakeBook;


    public Person(){

    }

    public Person(String FIO, int yearOfBirth){
        this.FIO = FIO;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public List<Book> getBooks() {

        return books;
    }


    public Date getTimeTakeBook() {
        return timeTakeBook;
    }

    public void setTimeTakeBook(Date timeTakeBook) {
        this.timeTakeBook = timeTakeBook;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
