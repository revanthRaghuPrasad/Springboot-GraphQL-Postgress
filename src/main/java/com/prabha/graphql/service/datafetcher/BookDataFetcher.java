package com.prabha.graphql.service.datafetcher;

import com.prabha.graphql.exception.BookNotFoundException;
import com.prabha.graphql.model.Book;
import com.prabha.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    BookRepository bookRepository;

    @SneakyThrows
    @Override
    public Book get(DataFetchingEnvironment environment) {
        String isn = environment.getArgument("id");
        return bookRepository.findById(isn).orElseThrow(BookNotFoundException::new);
    }
}
