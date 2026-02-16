package tn.iit.notificationservices.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String nom, String prenom) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Bienvenue sur notre plateforme 🎉");
        message.setText("Bonjour " + prenom + " " + nom +
                ",\n\nBienvenue sur notre application !");

        mailSender.send(message);
    }
}
