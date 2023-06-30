package com.mfgshopapi.repository;

import com.mfgshopapi.dto.TopProductDTO;
import com.mfgshopapi.model.OrderProduct;
import com.mfgshopapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IOrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findOrderProductsByOrder_Id(int id);
    @Transactional
    void deleteOrderProductsByOrder_Id(int id);
    @Query(value = "SELECT p FROM Product as p LEFT JOIN OrderProduct as op on p.idProduct = op.product.idProduct GROUP BY p.idProduct ORDER BY COUNT(p.idProduct) DESC, p.idProduct")
    Page<Product> getBestSellerList(Pageable pageable);
    @Query("SELECT new com.mfgshopapi.dto.TopProductDTO(od.product, SUM(od.amountProduct)) " +
            "FROM OrderProduct od " +
            "GROUP BY od.product " +
            "ORDER BY SUM(od.amountProduct) DESC")
    List<TopProductDTO> getTop10ProductSeller(Pageable pageable);
}
