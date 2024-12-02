package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.repository.AuthorUuidRepository;
import guru.springframework.sdjpaintro.repository.BookRepository;
import guru.springframework.sdjpaintro.repository.BookUuidRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@ActiveProfiles("local")
public class MySQLIntegrationTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookUuidRepository bookUuidRepository;
    @Autowired
    private AuthorUuidRepository authorUuidRepository;

    @Test
    void testMySQL() {
        assertThat(bookRepository.count()).isEqualTo(2);
        assertThat(bookUuidRepository.count()).isEqualTo(1);
        assertThat(authorUuidRepository.count()).isEqualTo(1);
    }
}
