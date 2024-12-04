package guru.springframework.sdjpaintro.domain.composite;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class NameId implements Serializable {
    private String firstName;
    private String lastName;
}
