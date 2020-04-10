package com.assignment.onlinebookstore.repositories;

import com.assignment.onlinebookstore.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value="select * from Book u where u.author like %:author% or u.title like %:title% or u.isbn like %:isbn%", nativeQuery=true)
    List<Book> findByFilter(@Param("author") String author, @Param("title") String title, @Param("isbn") String isbn);

    @Query(value="select * from Book u where u.isbn = :isbn",nativeQuery = true)
    Book findByIsbn(@Param("isbn") String isbn);
}
