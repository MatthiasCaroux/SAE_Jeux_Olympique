# Compilation des sources principales
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d bin src/modele/comparator/*.java src/modele/exceptions/*.java src/modele/**/*.java src/controlleur/*.java src/vues/*.java src/basededonnee/*.java src/basededonnee/exception/*.java

# Compilation des tests
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*" -d bin src/tests/*.java

# Génération de la Javadoc
javadoc --module-path ./lib --add-modules javafx.controls,javafx.fxml -d doc -sourcepath src -cp "lib/*" src/modele/comparator/*.java src/modele/exceptions/*.java src/modele/**/*.java src/controlleur/*.java src/vues/*.java src/basededonnee/*.java src/basededonnee/exception/*.java #src/**/*.java src/*.java

# Instrumentation des classes avec Jacoco
java -javaagent:lib/jacocoagent.jar=destfile=jacoco.exec -jar lib/jacococli.jar instrument bin --dest bin-instrumented

# Exécution des tests avec Jacoco
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin-instrumented:lib/*" org.junit.runner.JUnitCore src.tests.TestJO

# Génération du rapport de couverture Jacoco
java -jar lib/jacococli.jar report jacoco.exec --classfiles bin --sourcefiles src --html report

# Exécution de la classe Executable
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin-instrumented:lib/*" src.modele.jeuxOlympique.Executable


