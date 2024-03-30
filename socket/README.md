Objectif: développer des serveurs et des clients en utilisant Sockets pour un Service de chat qui consiste à  
envoyer de messages texte à un salon de discussion commun et récupérer des messages envoyés par d'autres utilisateurs.

# côté serveur 
- Définition du port sur lequel le serveur écoutera les connexions.
- Définition d'une liste pour stocker les flux de sortie des clients.
- Création du socket serveur.
- Accepter une nouvelle connexion client.
- Créer un flux de sortie pour envoyer des messages au client par la méthode PrintWriter().
- Ajouter le flux de sortie du client à la liste des clients connectés.
- Créer un thread pour gérer les messages du client.
- Lire les messages du client et les diffuser à tous les autres clients.
- Diffuser le message à tous les clients sauf à celui qui l'a envoyé.
- Supprimer le flux de sortie du client de la liste lorsque la connexion se termine.
-  Démarrer le thread.
--> Donc ,le serveur écoute en permanence sur un port spécifique en attendant les connexions des clients.
Une fois qu'un client se connecte, le serveur l'ajoute à une liste de clients connectés.

# côté client
- Définition de l'adresse IP du serveur.
- Définition du port sur lequel le client se connectera au serveur.
- Connexion au serveur en utilisant l'adresse et le port spécifiés.
- Création d'un flux de sortie pour envoyer des messages au serveur.
- Création d'un flux d'entrée pour recevoir les messages du serveur.
- Lecture des messages du serveur en boucle.
- Boucle pour permettre au client d'envoyer des messages au serveur.
- Lecture de l'entrée utilisateur depuis la console.
- Envoi du message au serveur.


** Le salon de communication n'est pas défini comme une entité distincte. Il est plutôt implémenté par le biais de la liste de clients connectés gérée par le serveur.
Voici comment cela fonctionne:
 + Liste des clients:
    Le serveur conserve une liste de tous les clients actuellement connectés au service de chat.
    Cette liste est une structure de données qui stocke les informations de chaque client, comme son adresse IP et son nom d'utilisateur.
 + Diffusion des messages:
    Lorsqu'un client envoie un message, le serveur le diffuse à tous les autres clients de la liste.
    Le serveur utilise les informations de la liste pour envoyer le message à chaque client individuellement.
 + Réception des messages:
    Chaque client reçoit les messages diffusés par le serveur et les affiche sur sa console.
    Le client peut identifier l'expéditeur du message grâce aux informations incluses dans le message.

# Exécution du programme :
- compiler le fichier serveur: javac ChatServer.java
- Démarrer le serveur en exécutant: java ChatServer
- compiler le client: javac ChatClient.java
- Démarrer plusieurs instances du client pour simuler plusieurs utilisateurs : java ChatClient