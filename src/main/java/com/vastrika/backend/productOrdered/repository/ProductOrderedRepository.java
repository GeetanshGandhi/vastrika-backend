package com.vastrika.backend.productOrdered.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vastrika.backend.orders.model.Orders;
import com.vastrika.backend.productOrdered.model.ProductOrdered;
import com.vastrika.backend.productOrdered.model.ProductOrderedPK;

@Repository
public interface ProductOrderedRepository extends JpaRepository<ProductOrdered, ProductOrderedPK> {

    List<ProductOrdered> findAllByOrders(Orders orders);

    @Query(value = "SELECT po.* FROM product_ordered po JOIN product p ON p.product_id=po.product_id where p.owner_email = :ownerEmail and po.status = :status ORDER BY po.order_id DESC", nativeQuery = true)
    List<ProductOrdered> findAllByBusinessAndStatus(@Param("ownerEmail") String ownerEmail,
                                                    @Param("status") String status);

    @Query(value= "SELECT * FROM product_ordered where product_id = :productId and order_id = :orderId ORDER BY order_id DESC", nativeQuery = true)
    ProductOrdered findByProductAndOrder(@Param("productId") int productId, @Param("orderId") int orderId);

    @Query(value= "SELECT * FROM product_ordered where status IN ('Packed', 'Dispatched')", nativeQuery=true)
    List<ProductOrdered> findAllForAdmin();

    @Query(value="SELECT po.* from product_ordered po, orders o, customer c WHERE po.order_id=o.order_id and o.customer_email=c.customer_email and c.city= :city and c.state = :state", nativeQuery = true)
    List<ProductOrdered> getByCustomerStateAndCity(@Param("city") String city, @Param("state") String state);

    @Query(value= "SELECT * FROM product_ordered where status = :status", nativeQuery=true)
    List<ProductOrdered> findByStatus(@Param("status") String status);

    @Query(value = "SELECT po.* FROM product_ordered po, orders o, customer c WHERE po.order_id=o.order_id AND o.customer_email=c.customer_email AND c.city = :cityInput AND po.status = :statIp", nativeQuery = true)
    List<ProductOrdered> findPOByCustomerCityAndStat(@Param("cityInput") String cityInput,
                                              @Param("statIp") String statIp);

    @Query(value = "SELECT po.* FROM product_ordered po, orders o, customer c WHERE po.order_id=o.order_id AND o.customer_email=c.customer_email AND c.city = :cityInput AND po.status = :statIp AND po.employee_email = :empEmail", nativeQuery = true)
    List<ProductOrdered> findByDestAndStatAndEmp(@Param("cityInput") String cityInput,
                                                 @Param("statIp") String statIp,
                                                 @Param("empEmail") String empEmail);
}
