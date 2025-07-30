package com.snacktrack.snacktrack.repository;

import com.snacktrack.snacktrack.model.CartItem;
import com.snacktrack.snacktrack.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	 @Query("SELECT c FROM CartItem c WHERE c.product = :product AND c.userId = :userId")
	 CartItem findByProductAndUserId(@Param("product") Product product, @Param("userId") Long userId);
	
	
    List<CartItem> findByUserId(Long userId);
    
    // Add this method to find by product and userId
 
}
