package org.edindandil.app.repositories;

import org.edindandil.app.pojo.Book;
import org.edindandil.app.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    Book findByTitle (String title);
    List<Book> findByReader(Person reader);
    List<Book> findByTitleStartingWithIgnoreCase (String startString);
}