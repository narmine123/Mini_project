// Importation des classes nécessaires pour travailler avec RMI
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Définition de l'interface du service RMI
public interface TaskService extends Remote {
    // Méthode pour ajouter une tâche à la liste
    void addTask(String task) throws RemoteException;
    // Méthode pour supprimer une tâche de la liste
    void removeTask(int taskId) throws RemoteException;
    // Méthode pour récupérer toutes les tâches de la liste
    List<String> getAllTasks() throws RemoteException;
}
