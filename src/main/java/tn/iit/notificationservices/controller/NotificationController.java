package tn.iit.notificationservices.controller;

import org.springframework.web.bind.annotation.*;
import tn.iit.notificationservices.dto.PersonRequest;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @PostMapping
    public String receiveMessage(@RequestBody PersonRequest person) {

        // Affiche dans la console de Hana
        System.out.println("Message reçu de Yosr : Nom = "
                + person.getNom() + ", Prénom = " + person.getPrenom());

        // Réponse à Yosr
        return "Message bien test sur Hana : " + person.getNom() + " " + person.getPrenom();
    }
}
