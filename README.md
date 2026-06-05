📚 Library Management System
Application de gestion de bibliothèque développée en Java EE (Servlets/JSP) pour gérer efficacement les livres, les membres et les emprunts au sein d'une bibliothèque.

🚀 Fonctionnalités
Gestion des Livres : Ajout, modification, suppression et recherche de livres.

Gestion des Membres : Inscription et suivi des abonnés.

Gestion des Emprunts : Enregistrement des prêts et retours de livres.

Authentification : Système de connexion sécurisé pour les administrateurs.

🛠️ Technologies utilisées
Langage : Java 17

Framework/Architecture : Java EE (Servlets/JSP), Maven (Gestionnaire de dépendances)

Base de données : MySQL

Serveur : Apache Tomcat 10.x

IDE : IntelliJ IDEA

📂 Architecture du projet
Le projet est structuré sous forme de projets Maven multi-modules pour une meilleure séparation des responsabilités :

primary-source : Contient la logique métier (DAOs, Modèles, Services).

servlet : Gère les contrôleurs web (Servlets) et les vues (JSP/HTML).

logging : Module dédié à la gestion des traces et logs.

⚙️ Installation et exécution
Cloner le dépôt :

Bash
git clone https://github.com/votre-utilisateur/library-management.git

Configuration Base de données :

Importez le script SQL (fourni dans le dossier /database) dans votre serveur MySQL.

Mettez à jour les paramètres de connexion dans DBConnection.java.

Lancer avec IntelliJ :

Ouvrez le projet avec IntelliJ IDEA.

Assurez-vous que le projet est chargé en tant que projet Maven.

Configurez SmartTomcat en ciblant le module servlet et lancez le serveur sur le port 8085.

Accès :

Ouvrez votre navigateur sur http://localhost:8085/servlet/login.html.

Note : N'oublie pas de remplacer votre-utilisateur dans l'URL par ton vrai nom d'utilisateur GitHub !


⚠️ Notes importantes
Protection par filtre : Le projet est conçu pour bloquer l'accès à toutes les pages tant que l'utilisateur n'est pas connecté. 
Note de développement : Actuellement, cette fonctionnalité est **mise en commentaire** à la première ligne du fichier `AuthFilter.java` pour faciliter les tests. Pensez à la décommenter pour activer la sécurité réelle.
