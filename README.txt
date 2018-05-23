Readme.txt
====================================================================
Description du projet
---------------------
La soci�t� fictive Proxibanque 

Pr�-requis de configuration
--------------------------
- JDK 9 install�e sur la machine cible (T�l�chargeable depuis : http://www.oracle.com/technetwork/java/javase/downloads/index.html).
- Serveur d'application web : tomcat 9.0 (T�l�chargeable depuis : https://tomcat.apache.org/download-90.cgil).
- Serveur WampServer 3.0.4 install� (avec serveur de base de donn�es : Mysql; (T�l�chargeable depuis: http://www.wampserver.com/).
- Cr�er un utilisateur sous PhPMyAdmin ayant pour login : 'root' et pour mot de passe 'root' et poss�dant tous les privil�ges en cochant la case 'tout cocher' dans 'privil�ges globaux'



Si execution sur Eclipse webServiceProxiBanque
--------------------------
- Param�trer la JDK et le serveur d'application web specifi�es pr�cedemmment
- Importer le projet Maven d�j� existant 'WebServiceProxibanque'
- Lancer le projet en mode 'Run as' 'Run on server'
"Cette action permettra de charger votre base de donnee"

Si execution sur Eclipse ClientProxiBanque
--------------------------
- Param�trer la JDK et le serveur d'application web specifi�es pr�cedemmment
- Importer le projet Maven d�j� existant 'ClientProxiBanque'
"Vous �tes desormais pret a naviguer sur l'application"



Execution sur Tomcat webServiceProxiBanque
-------------------------------------------
- Allumer le serveur d'applications en double-cliquant le fichier "startup.bat" du r�pertoire "bin" d'installation de votre serveur tomcat.
- D�marrer votre serveur de base de donn�es Wamp en double-cliquant sur le fichier "wampmanager.exe" du r�pertoire d'installation de votre serveur Wamp.
- Copier le fichier 'webServiceProxiBanque.war' dans le dossier 'webapps' du r�pertoire d'installation  du serveur tomcat.
- V�rifier que le fichier 'webServiceProxiBanque.war' se d�zipe dans un r�pertoire 'webapps'
- Ouvrir un Navigateur Web entrer localhost:8080/webServiceProxiBanque dans la barre d'adresse
"Ceci permetra de mettre en place votre base de donnee"

Execution sur Tomcat ClientProxiBanque
-------------------------------------------
- Allumer le serveur d'applications en double-cliquant le fichier "startup.bat" du r�pertoire "bin" d'installation de votre serveur tomcat.
- D�marrer votre serveur de base de donn�es Wamp en double-cliquant sur le fichier "wampmanager.exe" du r�pertoire d'installation de votre serveur Wamp.
- Copier le fichier 'ClientProxibanque.war' dans le dossier 'webapps' du r�pertoire d'installation  du serveur tomcat.
- V�rifier que le fichier 'ClientProxibanque.war' se d�zipe dans un r�pertoire 'webapps'
- Ouvrir un Navigateur Web entrer localhost:8080/ClientProxibanque dans la barre d'adresse
"Vous �tes desormais pret a naviguer sur l'application"


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
- Maven : management des d�pendances


Copyright and licensing information
-----------------------------------
- les droits de l'application web livr�e "ProxibanqueV2" sont d�tenus par :
		Alexandre DEMOLIS
		Nabil SERSOUB
		Benoit BLONDEL
		Stephane BOIVIN
		Mehdi COLBERT
- utilisation exclusivement r�serv�e � la soci�t� fictive Proxibanque.

Contact information for the distributor or programmer
-----------------------------------------------------
- contacter la soci�t� fictive GTM Etudiants pour des informations compl�mentaires.