# Compilation des sources principales
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d bin src/modele/comparator/*.java src/modele/exceptions/*.java src/modele/**/*.java src/controlleur/*.java

# Compilation des tests
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*" -d bin src/tests/*.java

# Génération de la Javadoc
javadoc --module-path ./lib --add-modules javafx.controls,javafx.fxml -d doc -sourcepath src -cp "lib/*" src/**/*.java

# Exécution des tests
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*" org.junit.runner.JUnitCore src.tests.TestJO

# Exécution de la classe Executable
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*" src.modele.jeuxOlympique.Executable