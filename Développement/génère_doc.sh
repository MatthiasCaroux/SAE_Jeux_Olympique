#!/bin/bash
mkdir doc
mkdir bin
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./bin $(find ./src/ -name "*.java")
javadoc --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./doc/ $(find ./src/ -name "*.java")
# javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./bin $(find ./src/ -name "*.java")
