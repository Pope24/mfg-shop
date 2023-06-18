package com.mfgshopapi.repository;

import com.mfgshopapi.model.ImgProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IImageProductRepository extends JpaRepository<ImgProduct, Integer> {
}
