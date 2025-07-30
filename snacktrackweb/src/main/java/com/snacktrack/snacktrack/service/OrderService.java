package com.snacktrack.snacktrack.service;

import com.snacktrack.snacktrack.model.Orders;
import com.snacktrack.snacktrack.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService; // Inject EmailService

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public void updateOrderStatus(Long orderId, String orderStatus) {
        Orders order = getOrderById(orderId);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        // Send email notification if status is "Completed"
        if ("Completed".equalsIgnoreCase(orderStatus)) {
            String subject = "Your order #" + orderId + " has been completed!";
            String message = "Hi, \n\nYour order with ID " + orderId + " has been successfully completed. "
                           + "Thank you for ordering with SnackTrack. We look forward to serving you again!\n\nBest regards,\nSnackTrack Team";

            emailService.sendEmail(order.getUserId(), subject, message);
        }
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
