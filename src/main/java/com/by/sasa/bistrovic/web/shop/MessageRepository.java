package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {

    List<Message> findByFromUserIdOrToUserId(String fromUserId, String toUserId);
}
