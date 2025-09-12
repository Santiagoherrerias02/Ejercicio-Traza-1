import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Empresa {
    private String nombre;
    private String razonSocial;
    private Integer cuit;
    private String logo;
    @Builder.Default
    private List<Sucursal>sucursales = new ArrayList<>();

}
