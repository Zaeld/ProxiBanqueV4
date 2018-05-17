# Readme du projet ProxiBanque_V4

## Le groupe et ses responsabilités

+ Mehdi :

+ Alexandre :

+ Stéphane :

+ Nabil :

+ Benoit :


## Configuration d'Eclipse + project

source : https://crunchify.com/how-to-solve-dynamic-web-module-3-1-requires-java-1-7-or-newer-in-eclipse/

+ Step 1 :  Verifier que eclipse possède la bonne version du JDK : Project / Preferences / Java / 
+ Step 1 bis : Vérifier l'encoding des fichiers Web (UTF-8) : Project / Preferences / Web

+ Step 2 : Verifier que le projet est configurer en 1.9.  MonProject > Properties > Java Compiler définir “Compiler compliance level” to 1.9.

+ Step 3 : Verifier la "JRE System Library". MonProjet / Java Build Path. Click “Edit“. Utiliser laJRE par défaut du workSpace (Java SE 9)

+ Step 4 : Utiliser le bon header pour vos fichier web.xml. (supprimer la ligne DOctype version = 2.3 qui force la version du dyn amic web module)



	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee" 
        	xmlns:web="http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         	version="3.1" xmlns="http://xmlns.jcp.org/xml/ns/javaee">
	</web-app>

Il faut utiliser la dépendance Maven javax servlet 3.1 ou supérieur :

	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>javax.servlet-api</artifactId>
		<version>3.1.0</version>
	    <scope>provided</scope>
	</dependency>


Complement (configuration eclypse) :  
https://crunchify.com/how-to-fix-cannot-change-version-of-project-facet-dynamic-web-module-to-3-0-error-in-eclipse/  

https://stackoverflow.com/questions/19661135/dynamic-web-module-3-0-3-1  

+ Step 5 : Vérifier que la version java du projet est la bonne MonProjet / Préférence / Project Facets > Java.

_Ne pas définir la version du Dynamic Web Module dans Project Facets_


+ Step 6 : Changer la version du Maven Compiler dans le pom.xml

<build>
    <plugins>
        <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
            <source>1.8</source>
            <target>1.8</target>
       	 </configuration>
    	</plugin>
    </plugins>
</build>


## GitHub et recommandation

+ Traviller toujours sur une branche, pas sur le Master. 
+ Avant de merge cette branche sur votre master local, faire un git pull du remote master : https://github.com/MehdiColbert/ProxiBanque_V4


## Java Doc

Pour harmoniser la Java doc suivre ce schéma :

	/**
	 * Résumé de la fonctionnalité de la méthode
	 *
	 * @param nomParam1 : description du paramètre "nomParam1" de la méthode
	 * @param nomParam2 : description du paramètre "nomParam2" de la méthode
	 * @return TypeVariableRetournay :  decription de la variable retourné par la méthode.
	 */





