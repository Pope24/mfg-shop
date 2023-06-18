package com.mfgshopapi.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
    public boolean checkItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (product.getIdProduct() == entry.getKey().getIdProduct()) {
                return true;
            }
        }
        return false;
    }

    public Map.Entry<Product, Integer> selectItemInCart(Product product) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getIdProduct() == product.getIdProduct()) {
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (!checkItemInCart(product)) {
            products.put(product, 1);
        } else {
            Map.Entry<Product, Integer> item = selectItemInCart(product);
            products.put(item.getKey(), item.getValue() + 1);
        }
    }

    public void subtractProduct(Product product) {
        if (selectItemInCart(product).getValue() == 1) {
            return;
        } else {
            Map.Entry<Product, Integer> item = selectItemInCart(product);
            products.put(item.getKey(), item.getValue() - 1);
        }
    }

    public Integer countTotalProductQuantity() {
        Integer productsQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productsQuantity += entry.getValue();
        }
        return productsQuantity;
    }

    public Integer countItemQuantity() {
        return products.size();
    }

    public BigDecimal countTotalPayment() {
        BigDecimal payment = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment = payment.add(entry.getKey().getCurrCost().multiply(new BigDecimal(entry.getValue())));
        }
        return payment;
    }

    public BigDecimal calculateTotalByProduct(Product product) {
        BigDecimal temp = new BigDecimal(products.get(product)).multiply(product.getCurrCost());
        return temp;
    }
    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
