package tn.iit.notificationservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.iit.notificationservices.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
