import java.io.*;
import java.net.*;

public class ChatClient {

    // Définition de l'adresse IP du serveur
    private static final String HOST = "localhost";
    
    // Définition du port sur lequel le client se connectera au serveur
    private static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        // Connexion au serveur en utilisant l'adresse et le port spécifiés
        Socket clientSocket = new Socket(HOST, PORT);
        
        // Création d'un flux de sortie pour envoyer des messages au serveur
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        // Création d'un flux d'entrée pour recevoir les messages du serveur
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Lecture des messages du serveur en boucle
        String message;
        while ((message = in.readLine()) != null) {
            System.out.println(message);
        }

        // Boucle pour permettre au client d'envoyer des messages au serveur
        while (true) {
            // Lecture de l'entrée utilisateur depuis la console
            message = System.console().readLine();
            
            // Envoi du message au serveur
            out.println(message);
        }
    }
}
