package com.prabha.graphql.resource;

import com.prabha.graphql.service.GraphqlService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("rest/books")
@RestController
public class BookResource {


    @Autowired
    GraphqlService graphqlService;

    @PostMapping
    public ResponseEntity<Object> getAllBooks(@RequestBody String query){
       ExecutionResult executionResult = graphqlService.getGraphQl().execute(query);
       return new ResponseEntity<Object>(executionResult, HttpStatus.OK);
    }
}
