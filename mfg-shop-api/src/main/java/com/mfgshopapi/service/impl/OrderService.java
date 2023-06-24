package com.mfgshopapi.service.impl;

import com.mfgshopapi.dto.OrderDTO;
import com.mfgshopapi.dto.OrderProductDTO;
import com.mfgshopapi.model.Customer;
import com.mfgshopapi.model.Employee;
import com.mfgshopapi.model.Order;
import com.mfgshopapi.model.OrderProduct;
import com.mfgshopapi.repository.IOrderRepository;
import com.mfgshopapi.service.ICustomerService;
import com.mfgshopapi.service.IEmployeeService;
import com.mfgshopapi.service.IOrderProductService;
import com.mfgshopapi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IOrderProductService orderProductService;

    @Override
    public Page<Order> findAllOrder(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findOrderById(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order saveNewOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order payment(OrderDTO orderDTO, int statusOrder, int statusPayment) {
        Order order = new Order();
        LinkedHashMap<?, ?> userMap = (LinkedHashMap<?, ?>) orderDTO.getUser();
        Customer customer = customerService.findCustomerByEmail(String.valueOf(userMap.get("email")));
        if (customer != null) {
            order.setCustomer(customer);
        } else {
            Employee employee = employeeService.findById((Integer) userMap.get("id"));
            order.setEmployee(employee);
        }
        order.setTotalMoney(orderDTO.getTotalMoney().add(new BigDecimal(30000)));
        order.setStatusPayment(statusPayment);
        order.setStatusOrder(statusOrder);
        order.setBookingDate(LocalDate.now());
        order.setDeliveryAddress(orderDTO.getAddressTakeOrder());
        Order newOrder = saveNewOrder(order);
        for (int i = 0; i < orderDTO.getListProduct().size(); i++) {
            OrderProductDTO orderProductDTO = orderDTO.getListProduct().get(i);
            orderProductService.save(new OrderProduct( orderProductDTO.getProduct(), newOrder, orderProductDTO.getAmount(), orderProductDTO.getSize()));
        }
        return order;
    }

    @Override
    public List<Order> findOrdersByCustomer(int id) {
        return orderRepository.findOrdersByCustomer_Id(id);
    }

    @Override
    public List<Order> findOrdersByEmployee(int id) {
        return orderRepository.findOrdersByEmployee_Id(id);
    }
}
