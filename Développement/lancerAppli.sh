#!/bin/bash
mkdir doc
mkdir bin
javac --module-path ./lib --add-modules javafx.controls,javafx.fxml -cp ./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar -d ./bin $(find ./src/ -name "*.java")
javadoc --module-path ./lib --add-modules javafx.controls,javafx.fxml -d ./doc $(find ./src/ -name "*.java")
java --module-path ./lib --add-modules javafx.controls,javafx.fxml -Djava.library.path=./lib -Dprism.order=sw -cp ./bin src.appliJO.vues.ApplicationJeuxOlympique