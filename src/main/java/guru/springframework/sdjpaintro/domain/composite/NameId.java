package guru.springframework.sdjpaintro.domain.composite;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Embeddable
public class NameId implements Serializable {
    private String firstName;
    private String lastName;
}
