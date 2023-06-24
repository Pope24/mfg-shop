package com.mfgshopapi.service.impl;

import com.mfgshopapi.model.Order;
import com.mfgshopapi.repository.IOrderRepository;
import com.mfgshopapi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Page<Order> findAllOrder(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order saveNewOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByCustomer(int id) {
        return orderRepository.findOrdersByCustomer_Id(id);
    }

    @Override
    public List<Order> findOrdersByEmployee(int id) {
        return orderRepository.findOrdersByEmployee_Id(id);
    }
}
