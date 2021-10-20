package com.sda.spring.data.jpa.optionals;

import com.sda.spring.data.jpa.repositories.crud.Person;
import com.sda.spring.data.jpa.repositories.crud.PersonCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class OptionalsConfig {

    private static final Logger log = LoggerFactory.getLogger(OptionalsConfig.class);

    @Autowired
    BookRepository bookRepository;

    @Bean
    public CommandLineRunner optionalsData() {
        return (strings) -> {
            Book book = createBook();
            Book savedBook = bookRepository.save(book);

            findBookUsingOptionalGet(savedBook.getId());
            findBookUsingOptionalOrElseThrow(savedBook.getId());
        };
    }

    private void findBookUsingOptionalOrElseThrow(Long id) {
        Book foundBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("book with id " + id + " not found"));
        log.info("--- saved book: {}", foundBook);
    }

    private void findBookUsingOptionalGet(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        Book foundBook = null;
        if (optionalBook.isPresent()) {
            foundBook = optionalBook.get();
        }
        log.info("--- saved book: {}", foundBook);
    }

    private Book createBook() {
        Book book = new Book();
        book.setTitle("Game of thrones");
        book.setAuthor("George Martin");
        book.setPublished(LocalDate.of(2015, 5, 30));
        return book;
    }

}
