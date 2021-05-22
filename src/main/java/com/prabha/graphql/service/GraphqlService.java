package com.prabha.graphql.service;

import com.prabha.graphql.service.datafetcher.AllBooksDataFetcher;
import com.prabha.graphql.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphqlService {

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQl;
    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;

    @Autowired
    private BookDataFetcher bookDataFetcher;

    @PostConstruct
    public void loadSchema( ) throws IOException{
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQl = GraphQL.newGraphQL(schema).build();

    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typewiring->
                    typewiring
                            .dataFetcher("allBooks",allBooksDataFetcher)
                            .dataFetcher("book",bookDataFetcher))
                .build();
    }

    public GraphQL getGraphQl(){
        return graphQl;
    }
}
