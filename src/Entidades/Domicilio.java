package Entidades;

import lombok.*;

@Setter
@Getter
@ToString( exclude = "localidad")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Domicilio {
    private int id;
    private String calle;
    private Integer numero;
    private Integer cp;
    private Localidad localidad;

}
