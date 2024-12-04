package guru.springframework.sdjpaintro.domain.composite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@IdClass(NameId.class)
public class AuthorComposite {
    @Id
    private String firstName;
    @Id
    private String lastName;
    private String country;
}
