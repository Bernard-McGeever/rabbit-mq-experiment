package com.example.web.web;

import com.example.api.model.MessageRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class WebController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

    private final MessageChannel messageChannel;

    public WebController(@Qualifier("messageRequest") MessageChannel messageChannel)
    {
        this.messageChannel = messageChannel;
    }

    @GetMapping
    public String index(@ModelAttribute("messageRequest") MessageRequest messageRequest, Model model)
    {
        return "index";
    }

    @PostMapping
    public String submit(@ModelAttribute("messageRequest") @Valid MessageRequest messageRequest,
                         BindingResult result, ModelMap model)
    {
        if (result.hasErrors()) {
            LOGGER.error("Validation failed: {}", result);
            return "index";
        }

        LOGGER.info("Sending message to service: {}", messageRequest);
        Message<MessageRequest> message = MessageBuilder.withPayload(messageRequest).build();

        messageChannel.send(message);

        return "index";
    }
}
