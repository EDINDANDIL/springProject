package org.edindandil.app.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.edindandil.app.validators.CurrentYear;


import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "ФИО не может быть пустым")
    @Pattern(regexp = "([А-ЯЁ][а-яА-ЯёЁ\\-]+\\s){2}[А-ЯЁ][а-яё]+",
            message = "Должен быть формат \"Фамилия Имя Отчество ")
    @Column(name = "name")
    private String name;

    public Person() {}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && year == person.year && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, books);
    }

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @CurrentYear
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "reader", fetch =  FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull(message = "ФИО не может быть пустым") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "ФИО не может быть пустым") String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Person(int year, String name) {
        this.year = year;
        this.name = name;
    }
}