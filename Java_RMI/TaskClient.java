// Importation des classes nécessaires pour travailler avec RMI
import java.rmi.Naming;
import java.util.List;

// Classe pour lancer le client RMI
public class TaskClient {
    public static void main(String[] args) {
        try {
            // Récupération de l'instance du service à partir du registre RMI
            TaskService taskService = (TaskService) Naming.lookup("rmi://localhost/TaskService");

            // Ajout d'une nouvelle tâche
            taskService.addTask("Faire les courses");

            // Récupération de toutes les tâches et affichage
            List<String> tasks = taskService.getAllTasks();
            System.out.println("Liste des tâches :");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }

            // Suppression d'une tâche (par exemple, la première)
            taskService.removeTask(0);
        } catch (Exception e) {
            // Gestion des exceptions
            e.printStackTrace();
        }
    }
}
