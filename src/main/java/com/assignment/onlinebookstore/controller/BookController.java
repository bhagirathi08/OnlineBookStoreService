package com.assignment.onlinebookstore.controller;

import com.assignment.onlinebookstore.Utils.*;
import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.services.*;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(value="Book Controller", description="CRUD book APIs")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookService bookService;

    @ApiOperation(value = "Health Check",response = String.class)
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(){
        return "PONG PONG";
    }

    @ApiOperation(value = "Add a list of book",response = HashMap.class)
    @PostMapping("books")
    public ResponseEntity<Map<String,String>> addBooks(@ApiParam(value = Constants.BOOK_ADD_API_VALUE) @RequestBody List<Book> books){
        logger.info("Adding books "+books.toString());

        Map<String,String> body = null;
        try {
            body = bookService.addBooks(books);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
