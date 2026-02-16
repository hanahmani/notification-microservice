package tn.iit.notificationservices.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.iit.notificationservices.dto.SignupRequest;
import tn.iit.notificationservices.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    // CONSTRUCTEUR MANUEL (au lieu de Lombok)
    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {

        String response = service.handleSignup(request);

        return ResponseEntity.ok(response);
    }
}
