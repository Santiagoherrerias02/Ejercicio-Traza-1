package Entidades;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString(exclude = "pais")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Provincia {
    private int id;
    private String nombre;
    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();
    private Pais pais;
}
