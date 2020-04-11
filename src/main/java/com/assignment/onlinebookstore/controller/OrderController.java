package com.assignment.onlinebookstore.controller;

import com.assignment.onlinebookstore.Utils.*;
import com.assignment.onlinebookstore.entities.Responses.*;
import com.assignment.onlinebookstore.entities.requests.*;
import com.assignment.onlinebookstore.services.*;
import io.swagger.annotations.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(value="Order Controller", description="Place order of book")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

    @ApiOperation(value="Place order for book",response = ArrayList.class)
    @PostMapping("books/order")
    public ResponseEntity<List<OrderResponse>> orderBooks(@ApiParam(value = Constants.BOOK_ORDER_API_VALUE) @RequestBody List<OrderRequest> orderRequests){
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
