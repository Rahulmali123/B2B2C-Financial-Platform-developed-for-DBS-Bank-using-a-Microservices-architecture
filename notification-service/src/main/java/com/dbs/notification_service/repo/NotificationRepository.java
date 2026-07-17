package com.dbs.notification_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.notification_service.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> 
{

    List<Notification> findByRecipient(String recipient);

    List<Notification> findByMobileNumber(String mobileNumber);

    List<Notification> findByType(String type);

    List<Notification> findByStatus(String status);

}