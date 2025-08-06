package org.edindandil.app.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.edindandil.app.validators.CurrentYear;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && isExpired == book.isExpired && Objects.equals(reader, book.reader) && Objects.equals(takenAt, book.takenAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reader, takenAt, isExpired);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonIgnore
    private Person reader;

    @NotNull(message = "Название книги не может быть пустым")
    @Pattern(regexp = "^[\\p{L}\\p{N}\\s\\p{P}\\p{S}]+$", message = "Название книги должно содержать буквы")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Фамилия и имя автор не могут быть пустыми")
    @Pattern(regexp = "[а-яА-ЯёЁ \\-]+", message = "Имя автора должно содержать только буквы")
    @Column(name = "author")
    private String author;

    @Min(value = 1564, message = "Год издания должен быть больше 1564")
    @CurrentYear
    @Column(name = "year")
    private int year;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "taken_at")
    private Date takenAt;

    @Transient
    private boolean isExpired;

    public boolean isExpired() {
        return (new Date().getTime() - takenAt.getTime()) / 86400000 > 10;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    public @NotNull(message = "Название книги не может быть пустым") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "Название книги не может быть пустым") String title) {
        this.title = title;
    }

    public @NotNull(message = "Фамилия и имя автор не могут быть пустыми") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull(message = "Фамилия и имя автор не могут быть пустыми") String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}