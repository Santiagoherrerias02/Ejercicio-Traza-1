import Entidades.*;
import Repositorio.InMemoryRepository;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> repositorio = new InMemoryRepository<>();

        LocalTime horaApertura = LocalTime.of(9, 0, 0);
        LocalTime horaCierre = LocalTime.of(21, 0, 0);

        // ========================== PaíSES ================================
        Pais argentina = Pais.builder()
                .id(1L)
                .nombre("Argentina")
                .build();

        // ========================== PROVINCIAS ================================
        Provincia bsas = Provincia.builder()
                .id(1L)
                .nombre("Buenos Aires")
                .pais(argentina)
                .build();

        Provincia cba = Provincia.builder()
                .id(2L)
                .nombre("Córdoba")
                .pais(argentina)
                .build();

        // ========================== LOCALIDADES ================================
        Localidad caba = Localidad.builder()
                .id(1L)
                .nombre("CABA")
                .provincia(bsas)
                .build();

        Localidad lp = Localidad.builder()
                .id(2L)
                .nombre("La Plata")
                .provincia(bsas)
                .build();

        Localidad ccp = Localidad.builder()
                .id(3L)
                .nombre("Córdoba Capital")
                .provincia(cba)
                .build();

        Localidad vcp = Localidad.builder()
                .id(4L)
                .nombre("Villa Carlos Paz")
                .provincia(cba)
                .build();

        // ========================== DOMICILIOS ================================
        Domicilio domcaba = Domicilio.builder()
                .id(1L)
                .calle("Pedrito Pedrero")
                .numero(3591)
                .cp(1000)
                .localidad(caba)
                .build();

        Domicilio domlp = Domicilio.builder()
                .id(2L)
                .calle("Cabrita Cabrero")
                .numero(1918)
                .cp(1900)
                .localidad(lp)
                .build();

        Domicilio domccp = Domicilio.builder()
                .id(3L)
                .calle("Loquito Loquero")
                .numero(49)
                .cp(5000)
                .localidad(ccp)
                .build();

        Domicilio domvcp = Domicilio.builder()
                .id(4L)
                .calle("Bolita Bolero")
                .numero(2121)
                .cp(5152)
                .localidad(vcp)
                .build();

        // ========================== SUCURSALES ================================
        Sucursal sucursal1 = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal CABA")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .es_Casa_Matriz(true)
                .domicilio(domcaba)
                .build();

        Sucursal sucursal2 = Sucursal.builder()
                .id(2L)
                .nombre("Sucursal La Plata")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domlp)
                .build();

        Sucursal sucursal3 = Sucursal.builder()
                .id(3L)
                .nombre("Sucursal Córdoba Capital")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domccp)
                .build();

        Sucursal sucursal4 = Sucursal.builder()
                .id(4L)
                .nombre("Sucursal Villa Carlos Paz")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domvcp)
                .build();

        // ========================== EMPRESAS ================================
        Empresa empresa1 = Empresa.builder()
                .nombre("Mc Donalds")
                .razonSocial("McDonals SRL")
                .cuit(234234345)
                .logo("McDonalds.png")
                .build();

        Empresa empresa2 = Empresa.builder()
                .nombre("Burger King")
                .razonSocial("BurgerKing SRL")
                .cuit(1231236123)
                .logo("BK.png")
                .build();

        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        repositorio.guardar(empresa1);
        repositorio.guardar(empresa2);

        // ========================== CONSOLA ================================

        System.out.println("\n================= MOSTRAR TODAS LAS EMPRESAS =================");
        List<Empresa> todas = repositorio.encontrarTodos();
        todas.forEach(e -> System.out.println("Empresa: " + e.getNombre() + " | CUIT: " + e.getCuit()));

        System.out.println("\n================= BUSCAR EMPRESA POR ID =================");
        System.out.println("Buscando empresa con ID = 2...");
        Optional<Empresa> porid = repositorio.encontrarxID(2L);
                porid.ifPresentOrElse(
                        e -> System.out.println("Resultado: Empresa encontrada -> " + e.getNombre()),
                        () -> System.out.println("Resultado: No se encontró la empresa con ID 2")
                );

        System.out.println("\n================= BUSCAR EMPRESA POR NOMBRE =================");
        String nombreBuscado = "Burger King";
        System.out.println("Buscando empresas con nombre = '" + nombreBuscado + "'...");
        List<Empresa> porNombre = repositorio.encontrarxCampo("nombre", nombreBuscado);
        if (porNombre.isEmpty()) {
            System.out.println("Resultado: No se encontró ninguna empresa con ese nombre.");
        } else {
            porNombre.forEach(e ->
                    System.out.println("Resultado: Empresa encontrada -> " + e.getNombre() + " (ID: " + e.getId() + ")"));
        }

        System.out.println("\n================= ACTUALIZAR EMPRESA =================");
        System.out.println("Actualizando empresa con ID = 2...");
        Empresa actu = Empresa.builder()
                .id(2L)
                .nombre("Burger King")
                .razonSocial("BurgerKing SRL")
                .cuit(192831973)
                .logo("BK.png")
                .build();

        repositorio.actualizacionEntidad(2L, actu);
        Optional<Empresa> verifi = repositorio.encontrarxID(2L);
                verifi.ifPresent(e -> System.out.println("Resultado: Empresa después de la actualización -> " + e));

        System.out.println("\n================= ELIMINAR EMPRESA =================");
        System.out.println("Eliminando empresa con ID = 2...");
        repositorio.borrarEntidad(2L);
        Optional<Empresa> eliminada = repositorio.encontrarxID(2L);
        if (eliminada.isEmpty()) {
            System.out.println("Resultado: La empresa con ID 2 ha sido eliminada correctamente.");
        }
    }
}

