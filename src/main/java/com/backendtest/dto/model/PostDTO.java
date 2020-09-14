package com.backendtest.dto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PostDTO {
    private int userId;
    @JsonAlias("id")
    private int post_id;
    @JsonAlias("title")
    private String post_title;
    @JsonAlias("body")
    private String post_body;
    private int total_number_of_comments;
}
