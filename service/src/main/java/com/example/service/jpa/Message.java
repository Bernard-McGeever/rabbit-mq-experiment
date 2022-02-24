package com.example.service.jpa;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message
{
    @Id
    @GeneratedValue
    @Column(name = "message_id", unique = true)
    private Long id;

    @Column(name = "message")
    private String massage;

    public Message()
    {
    }

    public Message(String massage)
    {
        this.massage = massage;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMassage()
    {
        return massage;
    }

    public void setMassage(String massage)
    {
        this.massage = massage;
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
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(massage, message.massage);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, massage);
    }

    @Override
    public String toString()
    {
        return "Message{" +
            "id=" + id +
            ", massage='" + massage + '\'' +
            '}';
    }
}
