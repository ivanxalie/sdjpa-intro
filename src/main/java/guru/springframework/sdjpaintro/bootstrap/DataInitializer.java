package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.repository.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repository.BookRepository;
import guru.springframework.sdjpaintro.repository.BookUuidRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile({"local", "default"})
public class DataInitializer {
    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    @PostConstruct
    void init() {
        cleanTables();

        Book ddd = Book.builder()
                .title("Domain Driven Design")
                .isbn("123")
                .publisher("RandomHouse")
                .build();

        System.out.printf("Before save: %s%n", ddd.getId());
        Book savedDDD = bookRepository.save(ddd);
        System.out.printf("After save: %s%n", savedDDD.getId());

        Book springInAction = Book.builder()
                .title("Spring in Action")
                .isbn("456")
                .publisher("Manning")
                .build();

        System.out.printf("Before save: %s%n", springInAction.getId());
        Book savedSIA = bookRepository.save(springInAction);
        System.out.printf("After save: %s%n", savedSIA.getId());

        System.out.println("List of books: ");

        bookRepository.findAll().forEach(System.out::println);

        AuthorUuid author = authorUuidRepository.save(AuthorUuid.builder()
                        .firstName("Joe")
                        .lastName("Back")
                .build());
        System.out.printf("Saved Author UUID: %s%n", author.getId());

        BookUuid savedUuidBook = bookUuidRepository.save(BookUuid.builder()
                        .title("All about UUIDs")
                .build());
        System.out.printf("Saved Book UUID: %s%n", savedUuidBook.getId());
    }

    private void cleanTables() {
        bookRepository.deleteAll();
        authorUuidRepository.deleteAll();
        bookUuidRepository.deleteAll();
    }
}
