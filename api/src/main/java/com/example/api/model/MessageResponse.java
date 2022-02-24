package com.example.api.model;

import java.util.Objects;

public class MessageResponse
{
    private final Long id;

    private final String message;

    public MessageResponse(Long id, String message)
    {
        this.id = id;
        this.message = message;
    }

    public Long getId()
    {
        return id;
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
        MessageResponse response = (MessageResponse) o;
        return Objects.equals(id, response.id) && Objects.equals(message, response.message);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, message);
    }

    @Override
    public String toString()
    {
        return "MessageResponse{" +
            "id=" + id +
            ", message='" + message + '\'' +
            '}';
    }
}
