// Importation des classes nécessaires pour travailler avec RMI
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

// Classe pour lancer le serveur RMI
public class TaskServer {
    public static void main(String[] args) throws RemoteException {
        try {
            // Création de l'instance du service
            TaskService taskService = new TaskServiceImpl();
            
            // Création d'un registre RMI sur le port par défaut (1099)
            LocateRegistry.createRegistry(1099);
            // Enregistrement du service dans le registre sous le nom "TaskService"
            Naming.rebind("TaskService", taskService);
            // Affichage d'un message indiquant que le serveur est en cours d'exécution
            System.out.println("Server is running...");
        } catch (Exception e) {
            // Gestion des exceptions
            e.printStackTrace();
        }
    }
}
