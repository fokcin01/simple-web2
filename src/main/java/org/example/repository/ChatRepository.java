package org.example.repository;

import org.example.model.Chat;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> getChatsByParticipantsContainsOrderByLastMessageDateDesc(User user);
}
