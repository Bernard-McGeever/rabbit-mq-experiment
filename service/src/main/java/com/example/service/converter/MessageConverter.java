package com.example.service.converter;

import com.example.api.model.MessageResponse;
import com.example.api.model.MessageRequest;
import com.example.service.jpa.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConverter.class);

    public Message convertRequestToEntity(MessageRequest request)
    {
        Message message = new Message();
        message.setMassage(request.getMessage());

        LOGGER.info("Converting request to entity: {}", message);
        return message;
    }

    public MessageResponse convertEntityToResponse(Message message)
    {
        LOGGER.info("Converting entity to responce:: {}", message);
        return new MessageResponse(message.getId(), message.getMassage());
    }
}
