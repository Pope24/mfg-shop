package com.mfgshopapi.service;

import com.mfgshopapi.dto.OrderDTO;
import com.mfgshopapi.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Page<Order> findAllOrder(Pageable pageable);
    Order findOrderById(int id);
    Order saveNewOrder(Order order);
    Order payment(OrderDTO orderDTO, int statusOrder, int statusPayment);
    List<Order> findOrdersByCustomer(int id);
    List<Order> findOrdersByEmployee(int id);
}
