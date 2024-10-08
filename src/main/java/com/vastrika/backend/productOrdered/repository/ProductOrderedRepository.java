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

    @Query(value = "SELECT po.* FROM product_ordered po JOIN product p ON p.product_id=po.product_id where p.owner_email = :ownerEmail and po.status = :status", nativeQuery = true)
    List<ProductOrdered> findAllByBusinessAndStatus(@Param("ownerEmail") String ownerEmail,
                                                    @Param("status") String status);

    @Query(value= "SELECT * FROM product_ordered where product_id = :productId and order_id = :orderId", nativeQuery = true)
    ProductOrdered findByProductAndOrder(@Param("productId") int productId, @Param("orderId") int orderId);

    @Query(value= "SELECT * FROM product_ordered where status = :status", nativeQuery=true)
    List<ProductOrdered> findByStatus(@Param("status") String status);
}
