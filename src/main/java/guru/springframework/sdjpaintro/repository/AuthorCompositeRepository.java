package guru.springframework.sdjpaintro.repository;

import guru.springframework.sdjpaintro.domain.composite.AuthorComposite;
import guru.springframework.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
