# Compilation des sources principales
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d bin src/modele/comparator/*.java src/modele/exceptions/*.java src/modele/**/*.java src/controlleur/*.java src/vues/*.java

# Exécution de la classe ApplicationJeuxOlympique
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*:src/ressources" src.vues.ApplicationJeuxOlympique