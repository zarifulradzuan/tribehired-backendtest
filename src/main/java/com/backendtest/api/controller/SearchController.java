package com.backendtest.api.controller;

import com.backendtest.api.constants.ControllerConstants;
import com.backendtest.dto.model.CommentDTO;
import com.backendtest.dto.response.SearchResponseDTO;
import com.backendtest.serviceimpl.InternalServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SearchController {

    @Autowired
    InternalServiceImpl internalServiceImpl;

    @GetMapping(path = ControllerConstants.SEARCH, produces = {"application/json"})
    public SearchResponseDTO searchComments(
            @RequestParam("post") int postId,
            @RequestParam("q") String keyword
    ) throws JSONException, JsonProcessingException {
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
            List<CommentDTO> commentsFiltered = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            JSONArray fieldNames = new JSONObject(objectMapper.writeValueAsString(comments.get(0))).names(); //convert comment dto into json to get its field names

        for (CommentDTO commentDTO : comments) {
            if (commentDTO.getPostId() == postId) {
                JSONObject commentJsn = new JSONObject(objectMapper.writeValueAsString(commentDTO)); //convert comment into json to enable using strings to fetch values

                for (int i = 0; i < fieldNames.length(); ++i) { //iterate through fields
                    if (commentJsn.getString(fieldNames.getString(i)).contains(keyword)){ //check values for keyword
                        commentsFiltered.add(commentDTO);
                        break;
                    }
                }
            }
        }
            searchResponseDTO.setResult(true);
            searchResponseDTO.setComments(commentsFiltered);
            return searchResponseDTO;
        }
        searchResponseDTO.setResult(false);
        searchResponseDTO.setError("No comments found for given post");
        return searchResponseDTO;
    }
}
