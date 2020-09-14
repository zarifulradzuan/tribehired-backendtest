package com.backendtest.dto.response;

import com.backendtest.dto.model.CommentDTO;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponseDTO extends ResponseDTO {
    private List<CommentDTO> comments;
}
