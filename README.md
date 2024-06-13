## Compilation des fichiers Java
Ouvrez un terminal dans le répertoire notation et compilez les fichiers :
 
code :
javac Etudiant.java CSVReader.java DatabaseNotation.java Main.java


## Création du fichier JAR
Créez un fichier JAR exécutable :

code : 
jar cfe student-grades.jar Main *.class

##Exécution du fichier JAR
Exécutez le fichier JAR avec le chemin du fichier CSV comme argument :

code :
java -jar student-grades.jar /path/to/students.csv
