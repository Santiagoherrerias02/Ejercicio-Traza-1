import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Domicilio {
    private String calle;
    private Integer numero;
    private Integer cp;
    private Localidad localidad;

}
