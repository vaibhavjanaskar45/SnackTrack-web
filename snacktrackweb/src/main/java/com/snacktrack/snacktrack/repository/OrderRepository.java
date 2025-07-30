package com.snacktrack.snacktrack.repository;

import com.snacktrack.snacktrack.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o JOIN FETCH o.orderItems WHERE o.userId = :userId")
    List<Orders> findByUserIdWithItems(@Param("userId") Long userId);

    List<Orders> findByUserId(Long userId);

    void deleteById(Long id);
}
