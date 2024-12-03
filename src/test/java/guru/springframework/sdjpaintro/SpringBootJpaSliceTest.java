package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repository.BookRepository;
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
}
