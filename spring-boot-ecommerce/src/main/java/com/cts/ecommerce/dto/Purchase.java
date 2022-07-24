package com.cts.ecommerce.dto;

import com.cts.ecommerce.entity.Address;
import com.cts.ecommerce.entity.Customer;
import com.cts.ecommerce.entity.Order;
import com.cts.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
