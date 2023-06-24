package com.mfgshopapi.service;

import com.mfgshopapi.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Page<Order> findAllOrder(Pageable pageable);
    Order saveNewOrder(Order order);
    List<Order> findOrdersByCustomer(int id);
    List<Order> findOrdersByEmployee(int id);
}
