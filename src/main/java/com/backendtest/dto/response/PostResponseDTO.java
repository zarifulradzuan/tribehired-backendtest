package com.backendtest.dto.response;

import com.backendtest.dto.model.PostDTO;
import lombok.Data;

import java.util.List;

@Data
public class PostResponseDTO extends ResponseDTO{
    private List<PostDTO> posts;
}
