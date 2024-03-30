Objectif : développer des serveurs et des clients en utilisant Java RMI pour la gestion d'une liste de tâches:ajouter une nouvelle tâche à la liste, supprimer une tâche existante de la liste et récupérer la liste complète des tâches.

La première étape dans toute les fichiers consiste à importer des classes nécessaires pour travailler avec RMI.

# TaskService
L'interface TaskService consiste à faire  une définition de l'interface du service RMI telle qu'une déclaration de méthode pour ajouter une tâche ,une pour supprimer une tâche  et une  pour récupérer toutes les tâches de la liste.

# TaskServiceImpl
La classe TaskServiceImpl consiste à faire une implémentation de l'interface du service RMI.
- On a fait une déclaration de Liste (tasks) pour stocker les tâches.
- Le Constructeur de la classe consiste à faire une initialisation de la liste des tâches.
- Puis une Implémentation de la méthode pour ajouter une tâche à la liste.
- une Implémentation de la méthode pour supprimer une tâche de la liste.
- Et Implémentation de la méthode pour récupérer toutes les tâches de la liste.

// La déclaration "public synchronized" signifie que la méthode qui suit peut être appelée à partir de différents threads en même temps et qu'elle est synchronisée, ce qui garantit qu'un seul thread peut exécuter cette méthode à la fois. Cela est souvent utilisé pour assurer la cohérence des données partagées entre plusieurs threads. En Java, les méthodes synchronisées utilisent un verrou pour empêcher l'accès concurrentiel aux données partagées, assurant ainsi l'intégrité des opérations effectuées à l'intérieur de la méthode.

# côté server
Cette Classe a pour but de  lancer le serveur RMI.
- Création de l'instance du service.
- Création d'un registre RMI sur le port par défaut (1099).
- Enregistrement du service dans le registre sous le nom "TaskService"à l'aide du méthode Naming.rebind 
- Affichage d'un message indiquant que le serveur est en cours d'exécution.
- Gestion des exceptions: Affichage de la trace de la pile d'exécution en cas d'erreur par la méthode printStackTrace().

//Naming.rebind est une méthode fournie par Java RMI  permettant d'associer un nom à un objet distant dans le registre RMI. Ce registre  est un service de nommage qui permet aux clients de rechercher des objets distants en fonction de leur nom. Lorsqu'un objet distant est lié à un nom à l'aide de Naming.rebind, il devient accessible pour les clients via ce nom. Si un objet distant est déjà lié à ce nom, il est remplacé par le nouvel objet.

# côté client
Cette Classe a pour but de lancer le client RMI.
- Récupération de l'instance du service à partir du registre RMI à l'aide de la méthode  Naming.lookup 
- Ajout d'une nouvelle tâche.
- Récupération de toutes les tâches et affichage.
- Suppression d'une tâche (par exemple, la première).
- Gestion des exceptions: Affichage de la trace de la pile d'exécution en cas d'erreur par la méthode printStackTrace().

// Naming.lookup est utilisé par les clients pour récupérer une référence vers un objet distant enregistré dans le registre RMI, en fonction de son nom.

# Exécution du programme :
- Compilation des fichiers Java: 
  javac TaskServer.java TaskClient.java TaskService.java
Cela va compiler vos fichiers Java et générer des fichiers .class
- Démarrage du registre RMI: rmiregistry
Dans le même répertoire,juste en ouvrant une nouvelle fenêtre de terminal (ou invite de commande) et exécutant la commande rmiregistry.
- Exécution du serveur: java TaskServer
- Exécution du client: java TaskClient


Pour que les connexions fonctionnent correctement ,il faut que que le registre RMI, le serveur et le client sont tous exécutés dans le même répertoire
On peut  maintenant voir des sorties dans les terminaux où le serveur et le client sont exécutés, confirmant que le serveur est prêt et affichant les tâches ajoutées et supprimées par le client. 
