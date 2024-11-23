package com.vastrika.backend.orders.service;

import com.vastrika.backend.cart.model.CartItem;
import com.vastrika.backend.cart.repository.CartItemRepository;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.orders.controller.NewOrderData;
import com.vastrika.backend.orders.controller.PlacedOrderData;
import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.orders.repository.OrdersRepository;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.repository.ProductRepository;
import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.repository.ProductOrderedRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductOrderedRepository productOrderedRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public List<Object[]> dropcolumns(){
//        String q1 = "ALTER TABLE orders DROP COLUMN sub_total";
//        String q2 = "ALTER TABLE orders DROP COLUMN tax";
//        jdbcTemplate.execute(q1);
//        jdbcTemplate.execute(q2);
//        return ordersRepository.getOrderDescription();
//    }

    public List<Object[]> getTableDesc(){
        return ordersRepository.getOrderDescription();
    }

    @Transactional
    public String addOrder(NewOrderData newOrderData){
        List<CartItem> cartItems = newOrderData.getCartItems();
        //check if cart quantity exceeds available quantity
        for(CartItem curr: cartItems){
            Product currProd = productRepository.findById(curr.getProduct().getProductId()).get();
            if(curr.getQuantity()> currProd.getQuantityAvailable()){
                return "Quantity Unavailable for "+currProd.getProductName();
            }
        }
        Customer customer = cartItems.get(0).getCustomer();
        double grandTotal = newOrderData.getGrandTotal();
        String paymentMethod = newOrderData.getPaymentMethod();

        Orders orders = new Orders(customer, grandTotal,
                                    LocalDateTime.now(), paymentMethod);

        Orders addedData = ordersRepository.save(orders);
        List<ProductOrdered> productOrderedList = new ArrayList<>();
        for(CartItem curr: cartItems){
            ProductOrdered currOrdered = new ProductOrdered();
            currOrdered.setOrders(addedData);
            currOrdered.setProduct(curr.getProduct());
            currOrdered.setQuantity(curr.getQuantity());
            currOrdered.setStatus("Placed");
            currOrdered.setRemark("Order is placed by customer");
            currOrdered.setOtpForCustomer(OTPGenerator.generate4DigitOTP());
            double price = curr.getProduct().getPrice(), disc = curr.getProduct().getDiscount();
            int rate = (int)(price - disc*price/100);
            currOrdered.setRate(rate);

            productOrderedList.add(currOrdered);
            //removing product from customer cart
            cartItemRepository.delete(curr);
            //updating quantityAvailable
            Product currProd = productRepository.findById(curr.getProduct().getProductId()).get();
            currProd.setQuantityAvailable(currProd.getQuantityAvailable()-curr.getQuantity());
            productRepository.save(currProd);
        }
        productOrderedRepository.saveAll(productOrderedList);
        return "Success";
    }

    public List<PlacedOrderData> getOrdersByCustomer(Customer customer){
        List<Orders> orders = ordersRepository.findAllByCustomer(customer.getCustomerEmail());
        List<PlacedOrderData> output = new ArrayList<>();
        for(Orders curr: orders){
            List<ProductOrdered> currProdOrdered = productOrderedRepository.findAllByOrders(curr);
            output.add(new PlacedOrderData(curr, currProdOrdered));
        }
        return output;
    }
}
class OTPGenerator{
    static int generate4DigitOTP(){
        Random random = new Random();
        return random.nextInt(9999-1000+1)+1000;
    }
}
