import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    // Définition du port sur lequel le serveur écoutera les connexions
    private static final int PORT = 5000;

    // Liste pour stocker les flux de sortie des clients
    private static List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Création du socket serveur
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            // Accepter une nouvelle connexion client
            Socket clientSocket = serverSocket.accept();

            // Créer un flux de sortie pour envoyer des messages au client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Envoyer un message de bienvenue au client
            out.println("Bienvenue sur le service de chat !");
            // Ajouter le flux de sortie du client à la liste des clients connectés
            clients.add(out);

            // Créer un thread pour gérer les messages du client
            Thread thread = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    String message;

                    // Lire les messages du client et les diffuser à tous les autres clients
                    while ((message = in.readLine()) != null) {
                        for (PrintWriter p : clients) {
                            // Diffuser le message à tous les clients sauf à celui qui l'a envoyé
                            if (p != out) {
                                p.println(message);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // Supprimer le flux de sortie du client de la liste lorsque la connexion se
                    // termine
                    clients.remove(out);
                }
            });
            // Démarrer le thread
            thread.start();
        }
    }
}
