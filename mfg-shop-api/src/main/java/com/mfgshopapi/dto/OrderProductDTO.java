package com.mfgshopapi.dto;

import com.mfgshopapi.model.Product;
import com.mfgshopapi.model.Size;

public class OrderProductDTO {
    private int amount;
    private Product product;
    private Size size;

    public OrderProductDTO() {
    }

    public OrderProductDTO(int amount, Product product, Size size) {
        this.amount = amount;
        this.product = product;
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
