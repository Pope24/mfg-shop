package com.mfgshopapi.service;

import com.mfgshopapi.model.Order;
import com.mfgshopapi.model.OrderProduct;
import com.mfgshopapi.model.Product;

import java.util.List;

public interface IOrderProductService {
    void save(OrderProduct orderProduct);
    List<List<OrderProduct>> findOrderProductsByOrderId(List<Order> orders);
}
