package com.mfgshopapi.controller;

import com.mfgshopapi.model.Cart;
import com.mfgshopapi.model.Product;
import com.mfgshopapi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public/cart")
@CrossOrigin("*")
@SessionAttributes("cart")
public class CartController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }
    @PostMapping("update-cart")
        public ResponseEntity<?> updateCart(@RequestBody Map<Product, Integer> products, @ModelAttribute Cart cart) {
         cart.setProducts(products);
         return new ResponseEntity<>(HttpStatus.OK);
        }
    @PostMapping("/add-cart/{id}")
    public ResponseEntity<Map<String, BigDecimal>> addProductToCart(@PathVariable int id, @ModelAttribute Cart cart) {
        Product product = productService.findById(id);
        cart.addProduct(product);
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("amount", new BigDecimal(cart.selectItemInCart(product).getValue()));
        map.put("price", cart.calculateTotalByProduct(product));
        map.put("amountMoney", cart.countTotalPayment());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/subtract-cart/{id}")
    public ResponseEntity<Map<String, BigDecimal>> subtractProductToCart(@PathVariable int id, @ModelAttribute Cart cart) {
        Product product = productService.findById(id);
        cart.subtractProduct(product);
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("amount", new BigDecimal(cart.selectItemInCart(product).getValue()));
        map.put("price", cart.calculateTotalByProduct(product));
        map.put("amountMoney", cart.countTotalPayment());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/delete-product-cart/{id}")
    public ResponseEntity<Map<String, BigDecimal>> deleteProductInCart(@PathVariable int id, @ModelAttribute Cart cart) {
        Product product = productService.findById(id);
        cart.deleteProduct(product);
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("amountMoney", cart.countTotalPayment());
        map.put("amountItems", new BigDecimal(cart.countItemQuantity()));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
