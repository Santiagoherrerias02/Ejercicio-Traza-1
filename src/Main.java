import Entidades.*;
import Repositorio.InMemoryRepository;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();

        LocalTime horaApertura = LocalTime.of(9, 0, 0);
        LocalTime horaCierre = LocalTime.of(21, 0, 0);

        // ========================== PaíSES ================================
        Pais argentina = Pais.builder()
                .id(1)
                .nombre("Argentina")
                .build();

        // ========================== PROVINCIAS ================================
        Provincia bsas = Provincia.builder()
                .id(1)
                .nombre("Buenos Aires")
                .pais(argentina)
                .build();

        Provincia cba = Provincia.builder()
                .id(2)
                .nombre("Córdoba")
                .pais(argentina)
                .build();

        // ========================== LOCALIDADES ================================
        Localidad caba = Localidad.builder()
                .id(1)
                .nombre("CABA")
                .provincia(bsas)
                .build();

        Localidad lp = Localidad.builder()
                .id(2)
                .nombre("La Plata")
                .provincia(bsas)
                .build();

        Localidad ccp = Localidad.builder()
                .id(3)
                .nombre("Córdoba Capital")
                .provincia(cba)
                .build();

        Localidad vcp = Localidad.builder()
                .id(4)
                .nombre("Villa Carlos Paz")
                .provincia(cba)
                .build();

        // ========================== DOMICILIOS ================================
        Domicilio domcaba = Domicilio.builder()
                .id(1)
                .calle("Pedrito Pedrero")
                .numero(3591)
                .cp(1000)
                .localidad(caba)
                .build();

        Domicilio domlp = Domicilio.builder()
                .id(2)
                .calle("Cabrita Cabrero")
                .numero(1918)
                .cp(1900)
                .localidad(lp)
                .build();

        Domicilio domccp = Domicilio.builder()
                .id(3)
                .calle("Loquito Loquero")
                .numero(49)
                .cp(5000)
                .localidad(ccp)
                .build();

        Domicilio domvcp = Domicilio.builder()
                .id(4)
                .calle("Bolita Bolero")
                .numero(2121)
                .cp(5152)
                .localidad(vcp)
                .build();

        // ========================== SUCURSALES ================================
        Sucursal sucursal1 = Sucursal.builder()
                .id(1)
                .nombre("Sucursal CABA")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .es_Casa_Matriz(true)
                .domicilio(domcaba)
                .build();

        Sucursal sucursal2 = Sucursal.builder()
                .id(2)
                .nombre("Sucursal La Plata")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domlp)
                .build();

        Sucursal sucursal3 = Sucursal.builder()
                .id(3)
                .nombre("Sucursal Córdoba Capital")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domccp)
                .build();

        Sucursal sucursal4 = Sucursal.builder()
                .id(4)
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
                .sucursales(Set.of(sucursal1, sucursal2))
                .build();

        Empresa empresa2 = Empresa.builder()
                .nombre("Burger King")
                .razonSocial("BurgerKing SRL")
                .cuit(1231236123)
                .logo("BK.png")
                .sucursales(Set.of(sucursal3, sucursal4))
                .build();

        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        // ========================== CONSOLA ================================

        System.out.println("\n================= MOSTRAR TODAS LAS EMPRESAS =================");
        List<Empresa> todas = empresaRepository.findAll();
        todas.forEach(e -> System.out.println("Empresa: " + e.getNombre() + " | CUIT: " + e.getCuit()));

        System.out.println("\n================= BUSCAR EMPRESA POR ID =================");
        System.out.println("Buscando empresa con ID = 2...");
        Optional<Empresa> porid = empresaRepository.findById(2L);
                porid.ifPresentOrElse(
                        e -> System.out.println("Resultado: Empresa encontrada -> " + e.getNombre()),
                        () -> System.out.println("Resultado: No se encontró la empresa con ID 2")
                );

        System.out.println("\n================= BUSCAR EMPRESA POR NOMBRE =================");
        String nombreBuscado = "Burger King";
        System.out.println("Buscando empresas con nombre = '" + nombreBuscado + "'...");
        List<Empresa> porNombre = empresaRepository.genericFindByField("nombre", nombreBuscado);
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
                .sucursales(empresa2.getSucursales())
                .build();

        empresaRepository.genericUpdate(2L, actu);
        Optional<Empresa> verifi = empresaRepository.findById(2L);
                verifi.ifPresent(e -> System.out.println("Resultado: Empresa después de la actualización -> " + e));

        System.out.println("\n================= ELIMINAR EMPRESA =================");
        System.out.println("Eliminando empresa con ID = 2...");
        empresaRepository.genericDelete(2L);
        Optional<Empresa> eliminada = empresaRepository.findById(2L);
        if (eliminada.isEmpty()) {
            System.out.println("Resultado: La empresa con ID 2 ha sido eliminada correctamente.");
        }
    }
}

