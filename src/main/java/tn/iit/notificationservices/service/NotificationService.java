package tn.iit.notificationservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tn.iit.notificationservices.dto.SignupRequest;
import tn.iit.notificationservices.entity.Notification;
import tn.iit.notificationservices.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private static final Logger logger =
            LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository repository;
    private final EmailService emailService;

    public NotificationService(NotificationRepository repository,
                               EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public String handleSignup(SignupRequest request) {

        logger.info("Signup received for {}", request.getEmail());

        String msg = "Bienvenue " +
                request.getPrenom() + " " +
                request.getNom();

        Notification notification = new Notification();
        notification.setNom(request.getNom());
        notification.setPrenom(request.getPrenom());
        notification.setEmail(request.getEmail());
        notification.setMessage(msg);
        notification.setCreatedAt(LocalDateTime.now());

        repository.save(notification);

        logger.info("Notification saved in database");

        emailService.sendEmail(
                request.getEmail(),
                request.getNom(),
                request.getPrenom()
        );

        logger.info("Email sent successfully");

        return msg;
    }
}
