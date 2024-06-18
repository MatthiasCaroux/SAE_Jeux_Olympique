# Compilation des sources principales
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d bin src/modele/comparator/*.java src/modele/exceptions/*.java src/modele/**/*.java src/controlleur/*.java src/vues/*.java src/basededonnee/*.java src/basededonnee/exception/*.java

# Exécution de la classe ApplicationJeuxOlympique
# java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:/usr/share/java/mariadb-java-client.jar:lib/*:src/ressources" src.vues.ApplicationJeuxOlympique
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:/usr/share/java/mariadb-java-client.jar:lib/*:src/ressources" src.vues.ApplicationJeuxOlympique 2>/dev/null


