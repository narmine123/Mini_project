import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.messaging.*;

public class MessagingClient {
    private final ManagedChannel channel; // Canal géré pour la communication avec le serveur
    private final MessagingServiceGrpc.MessagingServiceBlockingStub blockingStub; // Stub bloquant pour les appels RPC
                                                                                  // synchrones

    public MessagingClient(String host, int port) {
        // Création du canal géré en utilisant le constructeur de ManagedChannelBuilder
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        // Création du stub bloquant pour le service de messagerie en utilisant la
        // classe générée MessagingServiceGrpc
        blockingStub = MessagingServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        // Arrêt du canal géré
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void sendMessage(Message message) {
        // Envoi d'un message en utilisant le stub bloquant
        MessageResponse response = blockingStub.sendMessage(message);
        System.out.println("Response from server: " + response);
    }

    public void getMessages(String recipient) {
        // Récupération des messages pour un destinataire donné en utilisant le stub
        // bloquant
        UserRequest request = UserRequest.newBuilder().setUser(recipient).build();
        blockingStub.getMessages(request).forEachRemaining(response -> {
            System.out.println("Message from " + response.getSender() + ": " + response.getText());
        });
    }

    public static void main(String[] args) throws Exception {
        // Création d'une instance du client de messagerie
        MessagingClient client = new MessagingClient("localhost", 50051);
        try {
            // Utilisation des méthodes du client pour envoyer ou récupérer des messages
            Message message = Message.newBuilder()
                    .setSender("Alice")
                    .setRecipient("Bob")
                    .setText("Hello Bob!")
                    .build();
            client.sendMessage(message);

            client.getMessages("Bob");
        } finally {
            // Arrêt du client
            client.shutdown();
        }
    }
}
