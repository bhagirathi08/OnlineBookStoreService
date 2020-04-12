package com.assignment.onlinebookstore.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Book", uniqueConstraints = {
        @UniqueConstraint(columnNames = "isbn", name = "uniqueIsbnConstraint")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String title;
    @NotNull
    @Column(nullable = false)
    private String author;
    @NotNull
    @Column(nullable = false)
    private String isbn;
    @NotNull
    @Column(nullable = false)
    private Double price;
    private int quantity;

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Book)) {
            return false;
        }

        Book c = (Book) o;

        if(c.title.equals(title) &&  c.author.equals(author)
                && c.isbn.equals(isbn) && c.price.compareTo(price)==0
                && c.quantity == quantity)
            return true;
        else
            return false;

    }
}
