package com.backendtest.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CommentDTO {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    @JsonIgnore
    @Override
    public String toString() {
        return postId + " " + id + " " + name + " " + email + " " + body;
    }
}
