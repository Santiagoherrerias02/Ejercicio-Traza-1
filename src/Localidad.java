import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Localidad {
    private String nombre;
    private Provincia provincia;
}
