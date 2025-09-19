package Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Pais {
    private Long id;
    private String nombre;
}
