package src.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.controlleur.*;
import src.basededonnee.*;
import src.modele.jeuxOlympique.*;
import src.vues.ApplicationJeuxOlympique;

public class testApplicationJeuxOlympique {

    @Test
    public void testInit() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertNotNull(app.getModele());
            assertNotNull(app.getRequete());
        } catch (Exception e) {
            fail("Exception during init: " + e.getMessage());
        }
    }

    @Test
    public void testStart() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            Stage stage = new Stage();
            app.start(stage);
            assertNotNull(stage.getScene());
        } catch (Exception e) {
            fail("Exception during start: " + e.getMessage());
        }
    }

    @Test
    public void testChangerFenetre() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            Stage stage = new Stage();
            app.start(stage);
            Scene scene = new Scene(new VBox(), 800, 600);
            app.changerFenetre(scene, "Test Scene");
            assertEquals(scene, stage.getScene());
            assertEquals("Test Scene", stage.getTitle());
        } catch (Exception e) {
            fail("Exception during changerFenetre: " + e.getMessage());
        }
    }

    @Test
    public void testGetScenes() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertNotNull(app.getSceneFenetreAccueil());
            assertNotNull(app.getSceneConnexion());
            assertNotNull(app.getSceneInscription());
            assertNotNull(app.getSceneClassement());
            assertNotNull(app.getSceneAccueilAdmin());
            assertNotNull(app.getSceneAccueilOrganisateur());
            assertNotNull(app.getSceneAjoutDonnees());
            assertNotNull(app.getSceneModificationDonnée());
            assertNotNull(app.getSceneJournalisteAccueil());
            assertNotNull(app.getSceneAjouterUnAthlete());
            assertNotNull(app.getSceneModifierAthlete());
            assertNotNull(app.getSceneAthletes());
            assertNotNull(app.getSceneAjouterUneEquipe());
            assertNotNull(app.getSceneAjouterUneEpreuve());
            assertNotNull(app.getSceneEquipe());
            assertNotNull(app.getSceneGestionEpreuve());
            assertNotNull(app.getSceneGestionUtilisateur());
        } catch (Exception e) {
            fail("Exception during getScenes: " + e.getMessage());
        }
    }

    @Test
    public void testGetIdentifiantConnexion() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertEquals("", app.getIdentifiantConnexion());
        } catch (Exception e) {
            fail("Exception during getIdentifiantConnexion: " + e.getMessage());
        }
    }

    @Test
    public void testGetMotDePasseConnexion() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertEquals("", app.getMotDePasseConnexion());
        } catch (Exception e) {
            fail("Exception during getMotDePasseConnexion: " + e.getMessage());
        }
    }

    @Test
    public void testGetIdentifiantInscription() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertEquals("", app.getIdentifiantInscription());
        } catch (Exception e) {
            fail("Exception during getIdentifiantInscription: " + e.getMessage());
        }
    }

    @Test
    public void testGetEmailInscription() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertEquals("", app.getEmailInscription());
        } catch (Exception e) {
            fail("Exception during getEmailInscription: " + e.getMessage());
        }
    }

    @Test
    public void testGetMotDePasseInscription() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertEquals("", app.getMotDePasseInscription());
        } catch (Exception e) {
            fail("Exception during getMotDePasseInscription: " + e.getMessage());
        }
    }

    @Test
    public void testGetMotDePasseConfirmationInscription() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            assertEquals("", app.getMotDePasseConfirmationInscription());
        } catch (Exception e) {
            fail("Exception during getMotDePasseConfirmationInscription: " + e.getMessage());
        }
    }

    @Test
    public void testAjouterPaysALaBase() {
        try {
            ApplicationJeuxOlympique app = new ApplicationJeuxOlympique();
            app.init();
            app.ajouterPaysALaBase();
            // Assurez-vous que les pays ont été ajoutés correctement
            assertTrue(app.getRequete().dansPays("France"));
        } catch (Exception e) {
            fail("Exception during ajouterPaysALaBase: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("src.vues.testApplicationJeuxOlympique");
    }
}
