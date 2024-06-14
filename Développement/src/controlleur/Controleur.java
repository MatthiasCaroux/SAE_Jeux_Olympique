package src.controlleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import src.vues.ApplicationJeuxOlympique;

public class Controleur implements EventHandler<ActionEvent>{

    private ApplicationJeuxOlympique applicationJeuxOlympique;

    public Controleur(ApplicationJeuxOlympique applicationJeuxOlympique) {
        this.applicationJeuxOlympique = applicationJeuxOlympique;
    }

    public void handle(ActionEvent event) {
        System.out.println("Action sur le bouton"); 
        System.out.println(applicationJeuxOlympique);
    }
    
}
