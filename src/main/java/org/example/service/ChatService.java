package org.example.service;

import client.to.chat.ChatTo;
import org.example.model.Chat;
import org.example.repository.ChatRepository;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    ChatRepository repository;
    @Autowired
    UserRepository userRepository;

    public Chat createNewChat(Chat chat) {
        return repository.save(chat);
    }

    public List<ChatTo> getAllUserChats(Integer userId) {
        List<Chat> chats = repository.getChatsByParticipantsContainsOrderByLastMessageDateDesc(userRepository.getReferenceById(userId));
        logger.info("chats from repo: " + chats);
        return chats.stream().map(Chat::toDto).collect(Collectors.toList());
    }
}
