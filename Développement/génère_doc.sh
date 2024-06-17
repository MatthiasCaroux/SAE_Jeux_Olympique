#!/bin/bash
mkdir doc
mkdir bin
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./bin/ ./src/*.java
javadoc --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./doc/ ./src/*.java
# java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -cp ./bin Executable
