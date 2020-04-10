package com.assignment.onlinebookstore.entities.Responses;

import com.assignment.onlinebookstore.entities.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponse {
    private Long bookId;
    private String error;
    private ORDER_STATUS status;
    private double totalAmount;
}
