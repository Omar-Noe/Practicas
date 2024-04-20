import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class CerrarSesion extends UnicastRemoteObject implements GestionCuentas {
    private Map<String, Boolean> sesionesActivas = new HashMap<>();

    public CerrarSesion() throws RemoteException {
        super();
    }

    @Override
    public boolean cerrarSesion(String username) throws RemoteException {
        if (sesionesActivas.containsKey(username) && sesionesActivas.get(username)) {
            sesionesActivas.put(username, false);  // Cerrar sesión
            System.out.println("Sesión cerrada para el usuario: " + username);
            return true; // Cierre de sesión exitoso
        } else {
            System.out.println("No hay sesión activa para el usuario: " + username);
            return false; // No hay sesión activa para cerrar
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
    public boolean actualizarInformacion(String username, String nuevaInfo) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarInformacion'");
    }
}
