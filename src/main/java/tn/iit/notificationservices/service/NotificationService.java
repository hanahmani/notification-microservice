package tn.iit.notificationservices.service;



import org.springframework.stereotype.Service;
import tn.iit.notificationservices.dto.SignupRequest;
import tn.iit.notificationservices.entity.Notification;
import tn.iit.notificationservices.repository.NotificationRepository;

import java.time.LocalDateTime;
@Service
public class NotificationService {

    private final NotificationRepository repository;
    private final EmailService emailService;

    public NotificationService(NotificationRepository repository,
                               EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public String handleSignup(SignupRequest request) {

        String msg = "Bienvenue " +
                request.getPrenom() + " " +
                request.getNom();

        Notification notification = new Notification();
        notification.setNom(request.getNom());
        notification.setPrenom(request.getPrenom());
        notification.setEmail(request.getEmail());
        notification.setMessage(msg);
        notification.setCreatedAt(java.time.LocalDateTime.now());

        repository.save(notification);

        // ✅ ENVOI EMAIL RÉEL
        emailService.sendEmail(
                request.getEmail(),
                request.getNom(),
                request.getPrenom()
        );

        return msg;
    }
}
