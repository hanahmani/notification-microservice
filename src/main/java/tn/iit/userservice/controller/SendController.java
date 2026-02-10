package tn.iit.userservice.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.iit.userservice.DTO.PersonRequest;

@RestController
@RequestMapping("/send")
public class SendController {

    @PostMapping
    public String sendToHana(@RequestParam String nom, @RequestParam String prenom) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://192.168.1.33:8081/notifications"; // IP de PC Hana

        PersonRequest person = new PersonRequest(nom, prenom);

        String response = restTemplate.postForObject(url, person, String.class);

        return response;
    }
}