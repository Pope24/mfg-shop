package com.mfgshopapi.repository;

import com.mfgshopapi.dto.TopCustomerDTO;
import com.mfgshopapi.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByCustomer_Id(int id);
    List<Order> findOrdersByEmployee_Id(int id);
    @Query(value = "SELECT new com.mfgshopapi.dto.TopCustomerDTO(c, e, SUM(o.totalMoney)) FROM Order o LEFT JOIN o.customer c LEFT JOIN o.employee e GROUP BY c, e ORDER BY SUM(o.totalMoney) DESC ")
    List<TopCustomerDTO> getTopCustomerBuyer(Pageable pageable);
}
