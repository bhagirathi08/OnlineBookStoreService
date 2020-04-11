package com.assignment.onlinebookstore.Utils;

import org.springframework.stereotype.*;

@Component
public class Constants {
    public static final String INSUFFICIENT_QTY = "Insufficient quantity";
    public static final String BOOKS_ADDED = "Books added";
    public static final String BOOK_DOESNT_EXIST = "Book doesn't exist";
    public static final String BOOK_ADD_API_VALUE = "Book's author, title, isbn, price can't be null \n " +
            "isbn should be unique";
    public static final String BOOK_ORDER_API_VALUE = "Place order by book id and quantity\n"+
            "If quantity is less than the book count in store it will add few books and fulfill the request";
    public static final String SEARCH_BOOK_API_VALUE = "Advance search for books\n"+
            "Returns list of books whose title, author, isbn matches with the request params";
    public static final String SEARCH_MEDIA_COVERAGE_API_VALUE = "Search media coverage of a book"
            + "\nThe book is searched by the isbn, then all posts are fetched from the media url : https://jsonplaceholder.typicode.com/posts"
            + "\nThe title of the book is searched in post's title and body, if a match is found the post is returned";
}
