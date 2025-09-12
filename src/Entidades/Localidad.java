package Entidades;

import lombok.*;

@Setter
@Getter
@ToString(exclude = "provincia")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Localidad {
    private Long id;
    private String nombre;
    private Provincia provincia;
}
