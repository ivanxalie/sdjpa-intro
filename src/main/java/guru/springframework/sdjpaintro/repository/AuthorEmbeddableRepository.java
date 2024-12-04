package guru.springframework.sdjpaintro.repository;

import guru.springframework.sdjpaintro.domain.composite.AuthorEmbedded;
import guru.springframework.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddableRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
