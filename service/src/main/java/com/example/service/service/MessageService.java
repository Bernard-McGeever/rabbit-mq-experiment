package com.example.service.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.api.model.MessageResponse;
import com.example.api.model.MessageRequest;
import com.example.service.converter.MessageConverter;
import com.example.service.jpa.Message;
import com.example.service.jpa.MessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MessageService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository repository;

    private final MessageConverter converter;

    public MessageService(MessageRepository repository, MessageConverter converter)
    {
        this.repository = repository;
        this.converter = converter;
    }

    public List<MessageResponse> getAll()
    {
        List<Message> messages = repository.findAll();
        LOGGER.info("Messages retrieved form Database: {}", messages);
        return messages.stream().map(converter::convertEntityToResponse).collect(Collectors.toList());
    }

    @ServiceActivator(inputChannel = "messageRequest")
    public void save(@Payload MessageRequest request)
    {
        Message message = converter.convertRequestToEntity(request);

        LOGGER.info("Saving message to database: {}", message);
        repository.save(message);
    }
}
