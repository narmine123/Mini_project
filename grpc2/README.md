Objectif: développer des serveurs et des clients en utilisant gRPC pour Service de messagerie qui consiste à 
envoyer de messages texte à un destinataire spécifié et récupérer des messages reçus pour un utilisateur donné.

La première étape dans toute les fichiers consiste à importer des classes nécessaires du package gRPC.

# Fichier de protocole (.proto)
Chaque message est défini avec ses champs, et deux services sont définis : SendMessage pour envoyer un message et GetMessages pour récupérer les messages reçus pour un utilisateur donné.

# Implémentation du serveur :
Ce code configure et démarre un serveur gRPC. Il définit également l'implémentation des méthodes de service.
- Déclaration d'un port sur lequel le serveur écoutera les connexions.
- Déclaration d'un Serveur gRPC.
- Initialisation du port.
- Création d'un constructeur de serveur gRPC pour le port spécifié à l'aide de ServerBuilder.forPort(port).
- Ajoute le service d'implémentation de messagerie au serveur par la méthode  .addService(new MessagingServiceImpl()).
-  Construit le serveur par la méthode build.

// ServerBuilder.forPort(port) : forPort(port) indique à ServerBuilder sur quel port le serveur gRPC doit écouter les connexions entrantes. Une fois que le serveur est créé à l'aide de ce constructeur, il sera configuré pour écouter les requêtes sur le port spécifié, permettant ainsi aux clients de se connecter et d'interagir avec le serveur gRPC.

* Les méthodes qui sont utilisées pour la gestion du cycle de vie du serveur gRPC:
    +start() : [- Démarre le serveur.
              - Affiche un message indiquant que le serveur a démarré. 
              - Ajoute un hook de fermeture pour arrêter le serveur lorsque le programme se termine.
              - Arrête le serveur.]
    +stop() :Arrête proprement le serveur. 
    +blockUntilShutdown()  : Bloque jusqu'à ce que le serveur soit arrêté.
 ces méthodes sont essentielles pour démarrer, arrêter et attendre que le serveur gRPC termine son exécution de manière appropriée, assurant ainsi un fonctionnement stable et fiable de l'application.

- Crée une instance du serveur de messagerie sur le port 50051.
-  Démarre le serveur.
- Bloque jusqu'à ce que le serveur soit arrêté.

# Implémentation du client:
-Définition de la classe du client de messagerie: 
  * Déclaration des variables membres: 
       + Canal géré pour la communication avec le serveur.
       +  Stub bloquant pour les appels RPC synchrones.
  * Définition d'un Constructeur de la classe.
       +  Création du canal géré en utilisant le constructeur de ManagedChannelBuilder: Utilisation de la communication non cryptée (pour des raisons de simplicité, en production, il est recommandé d'utiliser TLS/SSL)à l'aide de la méthode usePlaintext().
       + Création du stub bloquant pour le service de messagerie en utilisant la classe générée MessagingServiceGrpc.
  * Définition d'une méthode pour arrêter le client:  Arrêt du canal par shutdown() et attente de terminaison pendant 5 secondes maximum par awaitTermination().
  * Définition d'une méthode pour envoyer un message.
  * Définition d'une méthode pour récupérer les messages pour un utilisateur donné
  * Définition de la méthode principale : main
    + Création d'une instance de MessagingClient en spécifiant l'hôte et le port du serveur
    +  Utilisation des méthodes du client pour envoyer ou récupérer des messages.
    + Arrêt du client une fois que toutes les opérations sont terminées


# Exécution du programme:
- Compiler les fichiers Java : javac *.java
- Démarrer le serveur : java MessagingServer
- Démarrer le client : java MessagingClient
Cela lancera le client, qui enverra un message et récupérera les messages pour l'utilisateur spécifié.Mais, il faut que le serveur est en cours d'exécution lors de  l'exécution du client. 