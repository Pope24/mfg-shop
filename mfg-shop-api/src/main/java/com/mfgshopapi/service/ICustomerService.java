package com.mfgshopapi.service;

import com.mfgshopapi.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer findById(int id);
    Customer findCustomerByEmail(String email);
}
