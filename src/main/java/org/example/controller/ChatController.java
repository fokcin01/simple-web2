package org.example.controller;

import client.to.UserTO;
import client.to.chat.ChatTo;
import client.to.chat.MessageTo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Chat;
import org.example.model.User;
import org.example.secutiry.SecurityUtil;
import org.example.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api")
@RestController
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/chat/message")
    public void sendMessage(MessageTo message) {
        if (message == null) {
            throw new RuntimeException("message is null");
        }
        int chatId = message.getChatId();
        if (chatId == 0 || chatId == -1) {
            throw new RuntimeException("chat id with id " + chatId + " not found");
        }
    }

    @PostMapping("/chat")
    public ChatTo createChat(@RequestBody String chatTo) {
        if (chatTo == null) {
            throw new RuntimeException("chat is null");
        }
        try {
            Chat chat = objectMapper.readValue(chatTo, Chat.class);
            logger.info("chat: {}", chat);
            boolean group = chat.isGroup();
            Set<User> participants = chat.getParticipants();
            logger.info("isGroup: {}", group);
            logger.info("participants: {}", participants);
            Chat newChat = chatService.createNewChat(chat);
            return Chat.toDto(newChat);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("can't save new chat", e);
        }

    }

    @GetMapping("/chat")
    public List<ChatTo> getAllUserChats() {
        return chatService.getAllUserChats(SecurityUtil.getAuthUserId());
    }
}
