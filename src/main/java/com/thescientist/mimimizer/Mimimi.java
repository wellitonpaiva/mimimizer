package com.thescientist.mimimizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mimimi {
    private Integer id;
    private String message;
    private LocalDateTime timestamp;
    private String user;

    public Mimimi(int id, String message, String user) {
        this.id = id;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.user = user;
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writer().writeValueAsString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mimimi mimimi = (Mimimi) o;

        if (id != null ? !id.equals(mimimi.id) : mimimi.id != null) return false;
        if (message != null ? !message.equals(mimimi.message) : mimimi.message != null) return false;
        return user != null ? user.equals(mimimi.user) : mimimi.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
