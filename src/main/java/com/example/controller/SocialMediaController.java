package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
 @RestController
public class SocialMediaController {

    public AccountService accountService;
    public MessageService messageService;
    
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        if (accountService.registerUser(account) != null)
            return ResponseEntity.status(200).body(accountService.registerUser(account));
        return ResponseEntity.status(409).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account) {
        if (accountService.loginUser(account) != null)
            return ResponseEntity.status(200).body(accountService.loginUser(account));
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createNewMessage(@RequestBody Message message) {
        if (messageService.createNewMessage(message) != null) 
            return ResponseEntity.status(200).body(messageService.createNewMessage(message));
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable Integer message_id) {
        if (messageService.getMessageByMessageId(message_id) != null)
            return ResponseEntity.status(200).body(messageService.getMessageByMessageId(message_id));
        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/messages/{message_id}")
    public Integer deleteMessageByMessageId(@PathVariable Integer message_id) {
        if (messageService.deleteMessageByMessageId(message_id) != null)
            return 1;
        return 0;
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageByMessageId(@PathVariable Integer message_id, @RequestBody Message message) {
        if (messageService.updateMessageByMessageId(message_id, message) != null)
            return ResponseEntity.status(200).body(1);
        return ResponseEntity.status(400).body(0);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable Integer account_id) {
        return ResponseEntity.status(200).body(messageService.getAllMessagesByAccountId(account_id));
    }
}
