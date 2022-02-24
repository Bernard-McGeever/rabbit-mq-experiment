package com.example.api.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest
{
    private final String message;

    public MessageRequest(@JsonProperty("message")String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        MessageRequest that = (MessageRequest) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(message);
    }

    @Override
    public String toString()
    {
        return "MessageRequest{" +
            "message='" + message + '\'' +
            '}';
    }
}
