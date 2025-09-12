import lombok.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Sucursal {
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean es_Casa_Matriz;
    private Domicilio domicilio;
}