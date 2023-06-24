package com.mfgshopapi.controller;

import com.mfgshopapi.dto.OrderDTO;
import com.mfgshopapi.dto.OrderProductDTO;
import com.mfgshopapi.model.Customer;
import com.mfgshopapi.model.Employee;
import com.mfgshopapi.model.Order;
import com.mfgshopapi.model.OrderProduct;
import com.mfgshopapi.security.request.NameAccount;
import com.mfgshopapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderProductService orderProductService;
    @PostMapping("/public/get-user")
    public ResponseEntity<?> getUserByNameAccount(@RequestBody NameAccount nameAccount) {
        Object user = accountService.findUserByNameAccount(nameAccount.getNameAccount());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PostMapping("/user/postpaid")
    public ResponseEntity<?> payAfterReceive(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        LinkedHashMap<?, ?> userMap = (LinkedHashMap<?, ?>) orderDTO.getUser();
        Customer customer = customerService.findCustomerByEmail(String.valueOf(userMap.get("email")));
        if (customer != null) {
            order.setCustomer(customer);
        } else {
            Employee employee = employeeService.findById((Integer) userMap.get("id"));
            order.setEmployee(employee);
        }
        order.setTotalMoney(orderDTO.getTotalMoney());
        order.setStatusPayment(0);
        order.setStatusOrder(0);
        order.setBookingDate(LocalDate.now());
        order.setDeliveryAddress(orderDTO.getAddressTakeOrder());
        Order newOrder = orderService.saveNewOrder(order);
        for (int i = 0; i < orderDTO.getListProduct().size(); i++) {
            OrderProductDTO orderProductDTO = orderDTO.getListProduct().get(i);
            orderProductService.save(new OrderProduct( orderProductDTO.getProduct(), newOrder, orderProductDTO.getAmount(), orderProductDTO.getSize()));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/user/history")
    public ResponseEntity<?> getHistoryOrderByUser(@RequestBody NameAccount nameAccount) {
        List<Order> orderOfUser;
        Object user = accountService.findUserByNameAccount(nameAccount.getNameAccount());
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            orderOfUser = orderService.findOrdersByCustomer(customer.getId());
        } else {
            Employee employee = (Employee) user;
            orderOfUser = orderService.findOrdersByEmployee(employee.getId());
        }
        if (orderOfUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderProductService.findOrderProductsByOrderId(orderOfUser), HttpStatus.OK);
    }
    @GetMapping("/employee/management-order")
    public ResponseEntity<?> getAllListOrder(@PageableDefault(size = 8)Pageable pageable, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
        pageable = PageRequest.of(page, 5);
        Page<Order> orders = orderService.findAllOrder(pageable);
        List<Order> orderList = new ArrayList<>();
        if(orders != null && orders.hasContent()) {
            orderList = orders.getContent();
        }
        return new ResponseEntity<>(orderProductService.findOrderProductsByOrderId(orderList), HttpStatus.OK);
    }
}
