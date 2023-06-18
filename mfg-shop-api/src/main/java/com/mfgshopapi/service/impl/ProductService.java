package com.mfgshopapi.service.impl;

import com.mfgshopapi.model.Product;
import com.mfgshopapi.repository.IProductRepository;
import com.mfgshopapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;
    @Override
    public Page<Product> findAll(String search, Pageable pageable) {
        return iProductRepository.findProductsByNameProductContaining(search, pageable);
    }

    @Override
    public Page<Product> findAllByTypeProduct(String search, int typeProduct, Pageable pageable) {
        return iProductRepository.findProductsByNameProductContainingAndTypeProductId(search, typeProduct, pageable);
    }

    @Override
    public Product findById(int id) {
        return iProductRepository.findById(id).get();
    }

    @Override
    public Page<Product> findProductsByTypeProduct(int id, Pageable pageable) {
        return iProductRepository.findProductsByTypeProductId(id, pageable);
    }
}
