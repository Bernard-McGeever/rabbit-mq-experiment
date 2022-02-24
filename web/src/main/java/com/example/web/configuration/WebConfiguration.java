package com.example.web.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.outbound.AmqpOutboundEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.endpoint.EventDrivenConsumer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;

@Configuration
public class WebConfiguration
{
    @Bean
    public MessageChannel messageRequest()
    {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "messageRequest", outputChannel = "toRabbit")
    public ObjectToJsonTransformer objectToJsonTransformer()
    {
        return new ObjectToJsonTransformer();
    }

    @Bean
    public SubscribableChannel toRabbit()
    {
        return new DirectChannel();
    }

    @Bean
    public EventDrivenConsumer rabbitConsumer(@Qualifier("toRabbit") SubscribableChannel subscribableChannel,
                                              @Qualifier("rabbitOutboundEndpoint") MessageHandler messageHandler)
    {
        return new EventDrivenConsumer(subscribableChannel, messageHandler);
    }

    @Bean
    public AmqpOutboundEndpoint rabbitOutboundEndpoint(AmqpTemplate amqpTemplate)
    {
        AmqpOutboundEndpoint adapter = new AmqpOutboundEndpoint(amqpTemplate);
        adapter.setRoutingKey("rabbitMqExperiment.messageRequest");
        return adapter;
    }
}
