package couchePresentation;
import coucheAccesDB.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class FenetrePrincipaleJava extends Application

{
    Stage Fenetre;
    /**
     * méthode start exécutée au démarrage : elle crée la fenêtre principale
     * @param fenetre : l'objet Stage représentant la fenêtre principale
     */
    @Override
    public void start(Stage fenetre)
    {
        Fenetre = fenetre;
// charger la vue dans la scene et associer le présenteur à cette vue
        try
        {
            FXMLLoader loader = new FXMLLoader(new URL("file:src/vues/FenetrePrincipaleVue.xml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 750, 500, Color.WHITE);
            scene.getStylesheets().add("vues/boostrap.css");
            fenetre.setScene(scene);
        }
        catch(Exception e)
        {
            System.out.println("Problème : " + e.getMessage());
            System.exit(0);
        }
// paramétrer et afficher la fenêtre principale
        fenetre.setTitle("Web Shop");
        fenetre.setResizable(false);
        fenetre.centerOnScreen();
        fenetre.show();
    }
    /**
     * Méthode exécutée au démarrage de l'application
     */

    public static void main(String[] args)
    {
        try
        {
            FabriqueDAO.getInstance().creerConnexion();
        }
        catch (Exception e) {
            GererErreur.erreurGen("FenetrePrincipale", "start()", e.getMessage());
            System.out.println("Problème pour se connecter à la base de données!");
            System.exit(0);
        }


        // charger la fenêtre principale
        launch(args);
    }
    /**
     * Méthodes exécutées lorsque l'utilisateur clique sur les rubriques des menus
     *                      BIERE
     */
    @FXML
    private void MListerBiere(ActionEvent event) {new ListerBiere(Fenetre); }
    @FXML
    private void MAjouterBiere(ActionEvent event){new AjouterBiere(Fenetre); }
    @FXML
    private void MModifierStockBiere(ActionEvent event) {new ModifierStock(Fenetre); }
    @FXML
    private void MArchiveBiere(ActionEvent event) {new (Fenetre); }


    /**
     * Méthodes exécutées lorsque l'utilisateur clique sur les rubriques des menus
     *                        ALCOOL
     */
    @FXML
    private void BAjouterAlcool(ActionEvent event) {new AjouterAlcool(Fenetre); }
    @FXML
    private void MModifierStockAlcool(ActionEvent event) {new ModifierStock(Fenetre); }
    @FXML
    private void ArchiveAlcool(ActionEvent event) {new (Fenetre); }
    @FXML
    private void MAjouterResultat(ActionEvent event)
    {
        new AjouterResultat(Fenetre);
    }
    @FXML
    private void MListerResultatsEleve(ActionEvent event)
    {
        new ListerResultatsEleve(Fenetre);
    }

    /**
     * Méthodes exécutées lorsque l'utilisateur clique sur les rubriques des menus
     *                        QUITTER
     */
    @FXML
    private void MQuitter(ActionEvent event)
    {
        System.exit(0);
    }
}