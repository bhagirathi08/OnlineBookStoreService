package com.assignment.onlinebookstore.services;

import com.assignment.onlinebookstore.Utils.*;
import com.assignment.onlinebookstore.entities.*;
import com.assignment.onlinebookstore.entities.Responses.*;
import com.assignment.onlinebookstore.entities.requests.*;
import com.assignment.onlinebookstore.repositories.*;
import com.assignment.onlinebookstore.services.interfaces.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
public class OrderService implements OrderIface {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<OrderResponse> placeOrder(List<OrderRequest> orderRequests) {
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(OrderRequest orderRequest:orderRequests){

            Book book = null;
            try {
                book = bookRepository.findById(orderRequest.getBookId()).get();
            }catch (NoSuchElementException e){
                orderResponses.add(buildResponse(false,orderRequest.getBookId(),Constants.BOOK_DOESNT_EXIST,0));
                continue;
            }
            if(orderRequest.getQty() > book.getQuantity()){

                //Incase you don't want to add book when qty is not sufficient
               //orderResponses.add(buildResponse(false,orderRequest.getBookId(),Constants.INSUFFICIENT_QTY));
                logger.info("Books qty insufficient : "+book.getQuantity());
                logger.info("Adding books to replenish : "+orderRequest.getQty()+1000);
                book.setQuantity(1000);
                bookRepository.save(book);
                orderResponses.add(buildResponse(true,orderRequest.getBookId(),
                        Constants.INSUFFICIENT_QTY+" , "+Constants.BOOKS_ADDED,
                        orderRequest.getQty() * book.getPrice()));

            }else{
                book.setQuantity(book.getQuantity() - orderRequest.getQty());
                bookRepository.save(book);
                orderResponses.add(buildResponse(true,orderRequest.getBookId(),"",
                        orderRequest.getQty() * book.getPrice()));
            }
        }

        return orderResponses;
    }

    private OrderResponse buildResponse(boolean isPlaced,Long bookId, String error,double totalAmount){
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setBookId(bookId);
        if(isPlaced){
            orderResponse.setStatus(ORDER_STATUS.PLACED);
            orderResponse.setError(error);
            orderResponse.setTotalAmount(totalAmount);
        }else{
            orderResponse.setStatus(ORDER_STATUS.FAILED);
            orderResponse.setError(error);
            orderResponse.setTotalAmount(0);
        }

        return orderResponse;
    }
}
