package org.example.model;

import client.to.chat.ChatTo;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "chats")
public class Chat extends AbstractEntity{
    private boolean isGroup;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<User> participants;
    private Date lastMessageDate;

    public Chat() {
    }

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(Date lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public static Chat toEntity(ChatTo dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Chat.class);
    }

    public static ChatTo toDto(Chat entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ChatTo.class);
    }
}
