package com.assignment.onlinebookstore.entities;

import lombok.*;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FetchedBook {
    private String userId;
    private String id;
    private String title;
    private String body;
}
