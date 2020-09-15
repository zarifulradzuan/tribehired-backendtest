package com.backendtest.api.controller;

import com.backendtest.api.constants.ControllerConstants;
import com.backendtest.dto.model.CommentDTO;
import com.backendtest.dto.response.SearchResponseDTO;
import com.backendtest.serviceimpl.InternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class SearchController {

    @Autowired
    InternalServiceImpl internalServiceImpl;

    @GetMapping(path = ControllerConstants.SEARCH)
    public SearchResponseDTO searchComments(
            @RequestParam("post") int postId,
            @RequestParam("q") String keyword
    ) {
        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
        List<CommentDTO> comments;
        try {
            comments = internalServiceImpl.getComments(); //get comments from API
        } catch (Exception e) {
            searchResponseDTO.setResult(false);
            searchResponseDTO.setError(e.getMessage());
            return searchResponseDTO;
        }
        if (comments.size() > 0) {
            List<CommentDTO> commentsFiltered = comments.stream()
                    .filter(e -> e.getPostId() == postId)
                    .filter(e -> e.toString().contains(keyword))
                    .collect(Collectors.toList());
            searchResponseDTO.setResult(true);
            searchResponseDTO.setComments(commentsFiltered);
            return searchResponseDTO;
        }
        searchResponseDTO.setResult(false);
        searchResponseDTO.setError("No comments found for given post");
        return searchResponseDTO;
    }
}
