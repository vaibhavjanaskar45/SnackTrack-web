package com.snacktrack.snacktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.snacktrack.snacktrack.model.User;
import com.snacktrack.snacktrack.repository.UserRepository;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Long userId, String subject, String body) {
        // Fetch the user's email using userId (mocking email retrieval)
        String userEmail = getUserEmailById(userId); 

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Autowired
    private UserRepository userRepository;

    private String getUserEmailById(Long userId) {
        return userRepository.findById(userId)
                .map(User::getEmail)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

}
