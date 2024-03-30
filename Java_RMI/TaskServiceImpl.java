
// Importation des classes nécessaires pour travailler avec RMI
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

// Implémentation de l'interface du service RMI
public class TaskServiceImpl extends UnicastRemoteObject implements TaskService {
    // Liste pour stocker les tâches
    private List<String> tasks;

    // Constructeur de la classe
    public TaskServiceImpl() throws RemoteException {
        // Initialisation de la liste des tâches
        tasks = new ArrayList<>();
    }

    // Implémentation de la méthode pour ajouter une tâche à la liste
    @Override
    public synchronized void addTask(String task) throws RemoteException {
        tasks.add(task);
    }

    // Implémentation de la méthode pour supprimer une tâche de la liste
    @Override
    public synchronized void removeTask(int taskId) throws RemoteException {
        if (taskId >= 0 && taskId < tasks.size()) {
            tasks.remove(taskId);
        }
    }

    // Implémentation de la méthode pour récupérer toutes les tâches de la liste
    @Override
    public synchronized List<String> getAllTasks() throws RemoteException {
        return new ArrayList<>(tasks);
    }
}
