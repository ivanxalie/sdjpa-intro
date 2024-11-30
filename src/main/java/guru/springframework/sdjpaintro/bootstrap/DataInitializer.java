package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile({"local", "default"})
public class DataInitializer {
    private final BookRepository bookRepository;

    @PostConstruct
    void init() {
        bookRepository.deleteAll();
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
    }
}
