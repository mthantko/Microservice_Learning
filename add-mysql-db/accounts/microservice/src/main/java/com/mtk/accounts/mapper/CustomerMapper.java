package com.mtk.accounts.mapper;

import com.mtk.accounts.dto.CustomersDto;
import com.mtk.accounts.entity.Customer;

public class CustomerMapper {

    // Entity → DTO
    public static CustomersDto mapToCustomerDto(Customer customer, CustomersDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    // DTO → Entity
    public static Customer mapToCustomer(CustomersDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}
