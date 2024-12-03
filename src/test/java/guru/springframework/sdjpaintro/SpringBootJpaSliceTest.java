package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.AuthorUuid;
import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.domain.BookUuid;
import guru.springframework.sdjpaintro.repository.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repository.BookRepository;
import guru.springframework.sdjpaintro.repository.BookUuidRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
public class SpringBootJpaSliceTest {
    @Autowired
    BookRepository repository;
    @Autowired
    BookUuidRepository bookUuidRepository;
    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Test
    @Order(10)
    @Commit
    void testJpaTestSplice() {
        long countBefore = repository.count();

        assertThat(countBefore).isEqualTo(2);

        repository.save(Book.builder()
                .title("My Book")
                .isbn("123")
                .publisher("Self")
                .build());
        long countAfter = repository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Test
    @Order(20)
    void testJpaTestSpliceTransaction() {
        long countBefore = repository.count();

        assertThat(countBefore).isEqualTo(3);
    }

    @Test
    void testBookUuidSave() {
        String title = "How can I help?";
        BookUuid saved = bookUuidRepository.save(BookUuid.builder()
                .title(title)
                .build());

        assertThat(bookUuidRepository.count()).isGreaterThan(1);

        assertThat(bookUuidRepository.findById(saved.getId()))
                .isNotNull()
                .satisfies(bookUuid -> {
                    assertThat(bookUuid.orElseThrow().getTitle()).isEqualTo(title);
                });
    }

    @Test
    void testAuthorUuidSave() {
        String firstName = "Alex";
        String lastName = "Green";
        AuthorUuid saved = authorUuidRepository.save(AuthorUuid.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build());

        assertThat(authorUuidRepository.count()).isGreaterThan(1);

        assertThat(authorUuidRepository.findById(saved.getId()))
                .isNotNull()
                .satisfies(authorUuid -> {
                    assertThat(authorUuid.orElseThrow().getFirstName()).isEqualTo(firstName);
                    assertThat(authorUuid.orElseThrow().getLastName()).isEqualTo(lastName);
                });
    }
}
