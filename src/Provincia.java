import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Provincia {
    private String nombre;
    private Pais pais;
}
