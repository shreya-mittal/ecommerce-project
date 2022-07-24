package com.cts.ecommerce.service;

import com.cts.ecommerce.dao.CustomerRepository;
import com.cts.ecommerce.dto.Purchase;
import com.cts.ecommerce.dto.PurchaseResponse;
import com.cts.ecommerce.entity.Customer;
import com.cts.ecommerce.entity.Order;
import com.cts.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        
        //check if an existing customer
        String theEmail = customer.getEmail();
        Customer customerFromDb = customerRepository.findByEmail(theEmail);
        
        if(customerFromDb != null) {
        	customer = customerFromDb;
        }
        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        //
        return UUID.randomUUID().toString();
    }
}









