import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class MessagingServer {
    private final int port; // Port sur lequel le serveur écoutera les connexions
    private final Server server; // Serveur gRPC

    public MessagingServer(int port) {
        // Initialisation du port
        this.port = port;
        // Création du serveur gRPC
        this.server = ServerBuilder.forPort(port)
                .addService(new MessagingServiceImpl())
                .build();
    }

    public void start() throws Exception {
        // Démarrage du serveur
        server.start();
        System.out.println("Server started, listening on " + port);
        // Ajout d'un hook de fermeture pour arrêter le serveur lorsque le programme se
        // termine
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down gRPC server since JVM is shutting down");
            MessagingServer.this.stop();
            System.err.println("*** Server shut down");
        }));
    }

    public void stop() {
        // Arrêt du serveur
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        // Bloque jusqu'à ce que le serveur soit arrêté
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        // Création d'une instance du serveur de messagerie
        MessagingServer server = new MessagingServer(50051);
        // Démarrage du serveur
        server.start();
        // Bloque jusqu'à ce que le serveur soit arrêté
        server.blockUntilShutdown();
    }

    static class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {
        @Override
        public void sendMessage(Message request, StreamObserver<MessageResponse> responseObserver) {
            // Logique pour envoyer le message
        }

        @Override
        public void getMessages(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
            // Logique pour récupérer les messages pour un utilisateur donné
        }
    }
}
