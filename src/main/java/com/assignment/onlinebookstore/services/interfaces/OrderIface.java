package com.assignment.onlinebookstore.services.interfaces;

import com.assignment.onlinebookstore.entities.Responses.*;
import com.assignment.onlinebookstore.entities.requests.*;

import java.util.*;

public interface OrderIface {
    public List<OrderResponse> placeOrder(List<OrderRequest> orderRequests);
}
