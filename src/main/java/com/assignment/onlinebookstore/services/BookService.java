package com.assignment.onlinebookstore.services;

import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.repositories.*;
import com.assignment.onlinebookstore.services.interfaces.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BookService implements BookIface {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookRepository bookRepository;

    @Override
    public Map<String,String> addBooks(List<Book> books) {
        Map<String,String> bookTitleToMessage = new HashMap<>();

        for(Book book:books){

            String message="";
            try {
                Book savedBook = bookRepository.save(book);
                message = String.valueOf(savedBook.getId());
            }catch (Exception e){
                if(e.getCause()!=null && e.getCause().getCause()!=null)
                    message = e.getCause().getCause().getMessage();
                else
                    message = e.getMessage();
            }

            logger.info("Adding book: "+book.toString());
            logger.info("Message: "+message);
            bookTitleToMessage.put(book.getIsbn(),message);
        }

        return bookTitleToMessage;
    }
}
