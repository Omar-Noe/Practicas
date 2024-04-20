import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ActualizarInformacion extends UnicastRemoteObject implements GestionCuentas {
    private Map<String, String> cuentas = new HashMap<>();

    public ActualizarInformacion() throws RemoteException {
        super();
    }

    @Override
    public boolean actualizarInformacion(String username, String nuevaInfo) throws RemoteException {
        if (cuentas.containsKey(username)) {
            // Obtener la información actual del usuario
            String infoActual = cuentas.get(username);
            
            // Actualizar la información del usuario con la nueva información
            String infoActualizada = infoActual + "\n" + nuevaInfo; // Por ejemplo, concatenar la nueva información
            
            // Actualizar la información en el mapa de cuentas
            cuentas.put(username, infoActualizada);
            
            // Mostrar mensaje de éxito
            System.out.println("Información actualizada para el usuario: " + username);
            return true; // Actualización exitosa
        } else {
            System.out.println("Usuario no encontrado: " + username);
            return false; // Usuario no encontrado
        }
    }

    @Override
    public boolean registrarCuenta(String username, String password) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarCuenta'");
    }

    @Override
    public boolean iniciarSesion(String username, String password) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
    }

    @Override
    public boolean cerrarSesion(String username) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cerrarSesion'");
    }
}
