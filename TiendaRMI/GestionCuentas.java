import java.rmi.Remote;
import java.rmi.RemoteException;

interface GestionCuentas extends Remote {
    boolean registrarCuenta(String username, String password) throws RemoteException;
    boolean iniciarSesion(String username, String password) throws RemoteException;
    boolean cerrarSesion(String username) throws RemoteException;
    boolean actualizarInformacion(String username, String nuevaInfo) throws RemoteException;
}
