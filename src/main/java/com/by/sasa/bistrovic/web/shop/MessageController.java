package com.by.sasa.bistrovic.web.shop;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080", "https://angular-java-webshop-6f4416307d68.herokuapp.com"})
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Message> getMessages() {
        return service.getAll();
    }

    @GetMapping("/conversation/{userId}")
    public List<Message> getConversation(@PathVariable String userId) {
        return service.getConversation(userId);
    }

    @PostMapping
    public Message sendMessage(@RequestBody Message msg) {
        return service.save(msg);
    }
}
