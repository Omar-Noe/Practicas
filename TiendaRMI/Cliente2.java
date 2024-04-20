import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente2 {
    public static void main(String[] args) {
        try {
            // Localizar el registro RMI en el servidor
            Registry registry = LocateRegistry.getRegistry("10.87.25.33");

            // Buscar los proveedores en el registro RMI
            TiendaOnline proveedorCompra = (TiendaOnline) registry.lookup("Compra");
            TiendaOnline proveedorCarrito = (TiendaOnline) registry.lookup("Carrito");
            TiendaOnline proveedorVerCarrito = (TiendaOnline) registry.lookup("VerCarrito");
            TiendaOnline proveedorPagar = (TiendaOnline) registry.lookup("Pagar");

            GestionCuentas iniciarSesion = (GestionCuentas) registry.lookup("Iniciar");
            GestionCuentas registrarCuenta = (GestionCuentas) registry.lookup("Registrar");
            GestionCuentas cerrarSesion = (GestionCuentas) registry.lookup("Cerrar");
            GestionCuentas actualizarInformacion = (GestionCuentas) registry.lookup("Actualizar");

            System.out.println("Estado de registro: "+registrarCuenta.registrarCuenta("HI", "4321"));
            System.out.println("Estado de iniciar sesion: "+iniciarSesion.iniciarSesion("HI", "4321"));
            System.out.println("Estado de actualizar informacion: "+actualizarInformacion.actualizarInformacion("HI", "BYE"));

            
            // Realizar operaciones con los proveedores
            System.out.println("Resultado del proveedor de compra: " + proveedorCompra.comprar("Producto1"));
            System.out.println("Resultado del proveedor de carrito: " + proveedorCarrito.agregarAlCarrito("Producto2"));
            System.out.println("Resultado del proveedor de ver carrito: " + proveedorVerCarrito.verCarrito());
            System.out.println("Resultado del proveedor de pagar: " + proveedorPagar.pagar());

            System.out.println("Estado de cerrarsesion;"+cerrarSesion.cerrarSesion("BYE"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

