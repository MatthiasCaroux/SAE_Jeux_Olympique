# Compilation des sources principales
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d bin src/modele/comparator/*.java src/modele/exceptions/*.java src/modele/**/*.java src/controlleur/*.java src/vues/*.java src/basededonnee/*.java

# Exécution de la classe ApplicationJeuxOlympique
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:/usr/share/java/mariadb-java-client.jar:lib/*:src/ressources" src.vues.ApplicationJeuxOlympique
# java -cp ./bin:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls DevoirJDBC

# java --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*:src/ressources:lib/mariadb-java-client.jar" src.vues.ApplicationJeuxOlympique
