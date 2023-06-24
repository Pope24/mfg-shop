package com.mfgshopapi.repository;

import com.mfgshopapi.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findOrderProductsByOrder_Id(int id);
}
