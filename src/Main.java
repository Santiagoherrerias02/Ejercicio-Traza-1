import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        LocalTime horaApertura = LocalTime.of(9, 00, 00);
        LocalTime horaCierre = LocalTime.of(21,00,00);

        //==========================Países===============================

        Pais argentina = Pais.builder()
                .nombre("Argentina")
                .build();

        //==========================Provincias===============================

        Provincia bsas = Provincia.builder()
                .nombre("Buenos Aires")
                .pais(argentina).
                build();

        Provincia cba = Provincia.builder()
                .nombre("Córdoba")
                .pais(argentina)
                .build();

        //==========================Localidades===============================

        Localidad caba = Localidad.builder()
                .nombre("Caba")
                .provincia(bsas)
                .build();

        Localidad lp = Localidad.builder()
                .nombre("La Plata")
                .provincia(bsas).build();

        Localidad ccp = Localidad.builder()
                .nombre("Córdoba Capital")
                .provincia(cba)
                .build();

        Localidad vcp = Localidad.builder()
                .nombre("Villa Carlos Paz")
                .provincia(cba)
                .build();

        //==========================Domicilios===============================

        Domicilio domcaba = Domicilio.builder()
                .calle("Pedrito Pedrero")
                .numero(3591)
                .cp(1000)
                .localidad(caba)
                .build();

        Domicilio domlp = Domicilio.builder()
                .calle("Cabrita Cabrero")
                .numero(1918).cp(1900)
                .localidad(lp)
                .build();

        Domicilio domccp = Domicilio.builder()
                .calle("Loquito Loquero")
                .numero(49)
                .cp(5000)
                .localidad(ccp)
                .build();

        Domicilio domvcp = Domicilio.builder()
                .calle("Bolita Bolero")
                .numero(2121)
                .cp(5152)
                .localidad(vcp)
                .build();

        //==========================Sucursales===============================

        Sucursal sucursal1 = Sucursal.builder()
                .nombre("Sucursal CABA")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .es_Casa_Matriz(true)
                .domicilio(domcaba)
                .build();

        Sucursal sucursal2 = Sucursal.builder()
                .nombre("Sucursal La Plata")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domlp)
                .build();

        Sucursal sucursal3 = Sucursal.builder()
                .nombre("Sucursal Córdoba Capital")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domccp)
                .build();

        Sucursal sucursal4 = Sucursal.builder()
                .nombre("Sucursal Villa Carlos Paz")
                .horarioApertura(horaApertura)
                .horarioCierre(horaCierre)
                .domicilio(domvcp)
                .build();

        //==========================Empresas===============================

        Empresa empresa1 = Empresa.builder()
                .nombre("Mc Donalds")
                .razonSocial("McDonals SRL")
                .cuit(234234345)
                .logo("McDonalds.png")
                .sucursales(List.of(sucursal1,sucursal2))
                .build();

        Empresa empresa2 = Empresa.builder()
                .nombre("Burger King")
                .razonSocial("BurgerKing SRL")
                .cuit(1231236123)
                .logo("BK.png")
                .sucursales(List.of(sucursal3,sucursal4))
                .build();

        //==========================Consola===============================
        
    }
}