package com.vastrika.backend.productOrdered.service;

import java.util.ArrayList;
import java.util.List;

import com.vastrika.backend.DeliveryEmployee.model.DeliveryEmployee;
import com.vastrika.backend.DeliveryEmployee.repository.DeliveryEmployeeRepository;
import com.vastrika.backend.productOrdered.controller.DevEmpPOPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.orders.repository.OrdersRepository;
import com.vastrika.backend.product.model.Product;
import com.vastrika.backend.product.repository.ProductRepository;
import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.repository.ProductOrderedRepository;

@Service
public class ProductOrderedService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductOrderedRepository productOrderedRepository;
    @Autowired
    DeliveryEmployeeRepository deliveryEmployeeRepository;

    @Autowired
    OrdersRepository ordersRepository;
    public List<ProductOrdered> getByBusinessAndStatus(String ownerEmail, String status){
        return productOrderedRepository.findAllByBusinessAndStatus(ownerEmail, status);
    }

    public String dispatchOrderForDelivery(int orderId, int productId){
        ProductOrdered oldItem = productOrderedRepository.findByProductAndOrder(productId, orderId);
        oldItem.setStatus("Packed");
        oldItem.setRemark("Order is Packed by the Retailer");
        productOrderedRepository.save(oldItem);
        return "Success";
    }

    public List<ProductOrdered> findByStatus(String status){
        return productOrderedRepository.findByStatus(status);
    }
    public String packedToDispatched(int orderId, int productId){
        ProductOrdered oldItem = productOrderedRepository.findByProductAndOrder(productId, orderId);
        oldItem.setStatus("Dispatched");
        oldItem.setRemark("The Order has been Dispatched");
        productOrderedRepository.save(oldItem);
        return "Success";
    }

    public String dispatchedToInCity(int orderId, int productId){
        ProductOrdered oldItem = productOrderedRepository.findByProductAndOrder(productId, orderId);
        oldItem.setStatus("InCity");
        oldItem.setRemark("The Product has arrived in your city");
        productOrderedRepository.save(oldItem);
        return "Success";
    }

    public List<ProductOrdered> getAllForAdmin(String status){
        return productOrderedRepository.findByStatus(status);
    }

    public String cancelOrder(int orderId, int productId, String reason){
        ProductOrdered item = productOrderedRepository.findByProductAndOrder(productId, orderId);
        if(item.getStatus().equals("Placed") || item.getStatus().equals("Packed")){
            //updating quantity available of product
            Product product = productRepository.findById(productId).get();
            product.setQuantityAvailable(product.getQuantityAvailable()+item.getQuantity());
            //changes in productOrdered record
            item.setStatus("Cancelled");
            String newRemark = "Order is cancelled by customer. Reason: "+reason;
            item.setRemark(newRemark);
            productOrderedRepository.save(item);
            //changing grand total of orders;
            Orders curr = ordersRepository.findById(orderId).get();
            List<ProductOrdered> allProds = productOrderedRepository.findAllByOrders(curr);
            int newtotal = 0;
            for(ProductOrdered i: allProds){
                if(!i.getStatus().equals("Cancelled")){
                    newtotal += (i.getQuantity()*i.getRate());
                }
            }
            curr.setGrandTotal(newtotal);
            ordersRepository.save(curr);
            return "Success";
        }
        return "Failure";
    }

    public List<DevEmpPOPayload> getByDestinationAndStat(String city){
        List<ProductOrdered> found = productOrderedRepository.findPOByCustomerCityAndStat(city, "InCity");
        for(ProductOrdered p: found) System.out.println(p.toString());
        List<DevEmpPOPayload> out = new ArrayList<>();
        for(ProductOrdered po: found){
            int orderId = po.getOrders().getOrderId();
            int productId = po.getProduct().getProductId();
            String paym = po.getOrders().getPaymentMethod();
            String address = po.getOrders().getCustomer().getHouseNumber()+", "+
                    po.getOrders().getCustomer().getStreetBuildingName()+", "+
                    po.getOrders().getCustomer().getLandmark()+", "+
                    po.getOrders().getCustomer().getCity();
            String cName = po.getOrders().getCustomer().getFirstName()+" "+ po.getOrders().getCustomer().getLastName();
            String phone = po.getOrders().getCustomer().getMobile();
            int amount = po.getRate()*po.getQuantity();
            out.add(new DevEmpPOPayload(cName, address, orderId, productId, phone, paym, amount));
        }
        return out;
    }

    public List<DevEmpPOPayload> getByDestAndStatAndEmp(String city, String empEmail){
        List<DevEmpPOPayload> output = new ArrayList<>();
        List<ProductOrdered> found = productOrderedRepository.findByDestAndStatAndEmp(city, "Delivering", empEmail);
        for(ProductOrdered po: found){
            int orderId = po.getOrders().getOrderId();
            int productId = po.getProduct().getProductId();
            String paym = po.getOrders().getPaymentMethod();
            String address = po.getOrders().getCustomer().getHouseNumber()+", "+
                    po.getOrders().getCustomer().getStreetBuildingName()+", "+
                    po.getOrders().getCustomer().getLandmark()+", "+
                    po.getOrders().getCustomer().getCity();
            String cName = po.getOrders().getCustomer().getFirstName()+" "+ po.getOrders().getCustomer().getLastName();
            String phone = po.getOrders().getCustomer().getMobile();
            int amount = po.getRate()* po.getQuantity();
            output.add(new DevEmpPOPayload(cName, address, orderId, productId, phone, paym, amount));
        }
        return output;
    }

    public String handleOrderAcceptance(int orderId, int productId, String empEmail) {
        ProductOrdered found = productOrderedRepository.findByProductAndOrder(productId, orderId);
        DeliveryEmployee devemp = deliveryEmployeeRepository.findById(empEmail).get();
        found.setDeliveryEmployee(devemp);
        found.setStatus("Delivering");
        found.setRemark("We are delivering to you. Complete the order by providing the OTP: " + found.getOtpForCustomer());
        productOrderedRepository.save(found);
        return "Success";
    }

    public String finishOrder(int orderId, int productId, String empEmail, int otp){
        ProductOrdered po = productOrderedRepository.findByProductAndOrder(productId, orderId);
        if(!po.getDeliveryEmployee().getEmployeeEmail().equals(empEmail))
            return "Unauthorized";
        if(otp!=po.getOtpForCustomer())
            return "Invalid";
        po.setStatus("Complete");
        po.setRemark("Product was delivered to Customer.");
        productOrderedRepository.save(po);
        return "Success";
    }
}
