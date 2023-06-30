package com.mfgshopapi.service.impl;

import com.mfgshopapi.dto.TopProductDTO;
import com.mfgshopapi.model.Order;
import com.mfgshopapi.model.OrderProduct;
import com.mfgshopapi.model.Product;
import com.mfgshopapi.repository.IOrderProductRepository;
import com.mfgshopapi.service.IOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductService implements IOrderProductService {
    @Autowired
    private IOrderProductRepository orderProductRepository;

    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    @Override
    public List<List<OrderProduct>> findOrderProductsByOrderId(List<Order> orders) {
        List<List<OrderProduct>> orderProducts = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            List<OrderProduct> orderProductsOfOrder = orderProductRepository.findOrderProductsByOrder_Id(orders.get(i).getId());
            orderProducts.add(orderProductsOfOrder);
        }
        return orderProducts;
    }

    @Override
    public void deleteOrdersByOrderId(int id) {
        orderProductRepository.deleteOrderProductsByOrder_Id(id);
    }

    @Override
    public Page<Product> getBestSellerList(Pageable pageable) {
        return orderProductRepository.getBestSellerList(pageable);
    }

    @Override
    public List<TopProductDTO> getTop10ProductSeller(Pageable pageable) {
        return orderProductRepository.getTop10ProductSeller(pageable);
    }
}
