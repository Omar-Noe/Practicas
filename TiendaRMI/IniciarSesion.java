import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class IniciarSesion extends UnicastRemoteObject implements GestionCuentas {
    private Map<String, String> cuentas = new HashMap<>();
    private Map<String, Boolean> sesionesActivas = new HashMap<>();

    public IniciarSesion() throws RemoteException {
        super();
    }

    @Override
    public boolean iniciarSesion(String username, String password) throws RemoteException {
        if (cuentas.containsKey(username)) {
            String storedPassword = cuentas.get(username);
            if (storedPassword.equals(password)) {
                sesionesActivas.put(username, true);  // Iniciar sesión
                System.out.println("Inicio de sesión exitoso: " + username);
                return true; // Inicio de sesión exitoso
            } else {
                System.out.println("Contraseña incorrecta para el usuario: " + username);
                return false; // Contraseña incorrecta
            }
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
    public boolean cerrarSesion(String username) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cerrarSesion'");
    }

    @Override
    public boolean actualizarInformacion(String username, String nuevaInfo) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarInformacion'");
    }
}
