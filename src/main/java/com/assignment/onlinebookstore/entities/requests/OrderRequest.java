package com.assignment.onlinebookstore.entities.requests;

import lombok.*;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
    private Long bookId;
    private int qty;
}
