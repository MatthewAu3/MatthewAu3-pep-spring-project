package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService {

    public MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createNewMessage(Message message) {
        if (message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255 && messageRepository.findById(message.getPosted_by()).isPresent())
            return messageRepository.save(message);
        return null;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(Integer id) {
        if (messageRepository.findById(id).isPresent())
            return messageRepository.findById(id).get();
        return null;
    }

    public Message deleteMessageByMessageId(Integer id) {
        if (messageRepository.findById(id).isPresent()) {
            Message message = messageRepository.findById(id).get();
            messageRepository.deleteById(id);
            return message;
        }
        return null;
    }

    public Message updateMessageByMessageId(Integer id, Message message) {
        if (messageRepository.findById(id).isPresent() && message.getMessage_text().length() > 0 && message.getMessage_text().length() < 255) {
            Message newMessage = messageRepository.findById(id).get();
            newMessage.setMessage_text(message.getMessage_text());
            return messageRepository.save(newMessage);
        }
        return null;
    }

    public List<Message> getAllMessagesByAccountId(Integer id) {
        List<Message> messages = new ArrayList<>();
        if (messageRepository.findById(id).isPresent()) {
            messages.add(messageRepository.findById(id).get());
            return messages;
        }
        return messages;
    }
    
}
