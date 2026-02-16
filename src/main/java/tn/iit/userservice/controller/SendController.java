package tn.iit.userservice.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.iit.userservice.DTO.SignupRequest;

@RestController
@RequestMapping("/users")
public class SendController {

    private final RestTemplate restTemplate;

    // ✅ CONSTRUCTEUR OBLIGATOIRE
    public SendController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {

        String response = restTemplate.postForObject(
                "http://172.20.10.9:8081/notifications/signup",
                request,
                String.class
        );

        return ResponseEntity.ok(
                "User créé. Notification : " + response
        );
    }
}