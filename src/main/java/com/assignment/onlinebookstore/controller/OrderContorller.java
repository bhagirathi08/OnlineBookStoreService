package com.assignment.onlinebookstore.controller;

import com.assignment.onlinebookstore.entities.Responses.*;
import com.assignment.onlinebookstore.entities.requests.*;
import com.assignment.onlinebookstore.services.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderContorller {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

    @PostMapping("books/order")
    public ResponseEntity<List<OrderResponse>> orderBooks(@RequestBody List<OrderRequest> orderRequests){
        logger.info("Placing order of books "+orderRequests.toString());

        List<OrderResponse> body = null;
        try {
            body = orderService.placeOrder(orderRequests);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
