Readme.txt
====================================================================
Description du projet
---------------------
La société fictive Proxibanque 

Pré-requis de configuration
--------------------------
- JDK 9 installée sur la machine cible (Téléchargeable depuis : http://www.oracle.com/technetwork/java/javase/downloads/index.html).
- Serveur d'application web : tomcat 9.0 (Téléchargeable depuis : https://tomcat.apache.org/download-90.cgil).
- Serveur WampServer 3.0.4 installé (avec serveur de base de données : Mysql; (Téléchargeable depuis: http://www.wampserver.com/).
- Créer un utilisateur sous PhPMyAdmin ayant pour login : 'root' et pour mot de passe 'root' et possédant tous les privilèges en cochant la case 'tout cocher' dans 'privilèges globaux'



Si execution sur Eclipse webServiceProxiBanque
--------------------------
- Paramétrer la JDK et le serveur d'application web specifiées précedemmment
- Importer le projet Maven déjà existant 'WebServiceProxibanque'
- Lancer le projet en mode 'Run as' 'Run on server'
"Cette action permettra de charger votre base de donnee"

Si execution sur Eclipse ClientProxiBanque
--------------------------
- Paramétrer la JDK et le serveur d'application web specifiées précedemmment
- Importer le projet Maven déjà existant 'ClientProxiBanque'
"Vous êtes desormais pret a naviguer sur l'application"



Execution sur Tomcat webServiceProxiBanque
-------------------------------------------
- Allumer le serveur d'applications en double-cliquant le fichier "startup.bat" du répertoire "bin" d'installation de votre serveur tomcat.
- Démarrer votre serveur de base de données Wamp en double-cliquant sur le fichier "wampmanager.exe" du répertoire d'installation de votre serveur Wamp.
- Copier le fichier 'webServiceProxiBanque.war' dans le dossier 'webapps' du répertoire d'installation  du serveur tomcat.
- Vérifier que le fichier 'webServiceProxiBanque.war' se dézipe dans un répertoire 'webapps'
- Ouvrir un Navigateur Web entrer localhost:8080/webServiceProxiBanque dans la barre d'adresse
"Ceci permetra de mettre en place votre base de donnee"

Execution sur Tomcat ClientProxiBanque
-------------------------------------------
- Allumer le serveur d'applications en double-cliquant le fichier "startup.bat" du répertoire "bin" d'installation de votre serveur tomcat.
- Démarrer votre serveur de base de données Wamp en double-cliquant sur le fichier "wampmanager.exe" du répertoire d'installation de votre serveur Wamp.
- Copier le fichier 'ClientProxibanque.war' dans le dossier 'webapps' du répertoire d'installation  du serveur tomcat.
- Vérifier que le fichier 'ClientProxibanque.war' se dézipe dans un répertoire 'webapps'
- Ouvrir un Navigateur Web entrer localhost:8080/ClientProxibanque dans la barre d'adresse
"Vous êtes desormais pret a naviguer sur l'application"


------------------------------------------------
                    Mot de Passse Gerant
--------------------------------------------------
login : admin              mdp : admin
------------------------------------------------------

------------------------------------------------
                    Mot de Passse Conseiller
--------------------------------------------------
login: c1                  mdp: c1
login: c2                  mdp: c2
login: c3                  mdp: c3
login: c4                  mdp: c4
login: c5                  mdp: c5

------------------------------------------------------------
       Remarques en lien avec la livraisson
---------------------------------------------------------------

----------
Build with
----------
- Maven : management des dépendances


Copyright and licensing information
-----------------------------------
- les droits de l'application web livrée "ProxibanqueV2" sont détenus par :
		Alexandre DEMOLIS
		Nabil SERSOUB
		Benoit BLONDEL
		Stephane BOIVIN
		Mehdi COLBERT
- utilisation exclusivement réservée à la société fictive Proxibanque.

Contact information for the distributor or programmer
-----------------------------------------------------
- contacter la société fictive GTM Etudiants pour des informations complémentaires.