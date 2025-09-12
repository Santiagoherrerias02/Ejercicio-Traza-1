package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString(exclude = "provincias")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Pais {
    private int id;
    private String nombre;
    @Builder.Default
    private Set<Provincia>provincias = new HashSet<>();
}
