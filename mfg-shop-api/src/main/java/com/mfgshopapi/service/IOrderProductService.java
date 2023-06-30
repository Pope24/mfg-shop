package com.mfgshopapi.service;

import com.mfgshopapi.dto.TopProductDTO;
import com.mfgshopapi.model.Order;
import com.mfgshopapi.model.OrderProduct;
import com.mfgshopapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderProductService {
    void save(OrderProduct orderProduct);
    List<List<OrderProduct>> findOrderProductsByOrderId(List<Order> orders);
    void deleteOrdersByOrderId(int id);
    Page<Product> getBestSellerList(Pageable pageable);
    List<TopProductDTO> getTop10ProductSeller(Pageable pageable);
}
