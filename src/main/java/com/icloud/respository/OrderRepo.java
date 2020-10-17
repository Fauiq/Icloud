package com.icloud.respository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icloud.entity.Item;
import com.icloud.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	@Query("select f from Item f join fetch f.Order s where s.orderId=:orderId")
	ArrayList<Order> findByOrderId(@Param("orderId") Long orderId);
}
