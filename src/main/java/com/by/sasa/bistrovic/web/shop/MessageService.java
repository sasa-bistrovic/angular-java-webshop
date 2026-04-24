package com.by.sasa.bistrovic.web.shop;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public List<Message> getAll() {
        return repo.findAll();
    }

    public Message save(Message msg) {
        return repo.save(msg);
    }

    public List<Message> getConversation(String userId) {
        return repo.findByFromUserIdOrToUserId(userId, userId);
    }
}